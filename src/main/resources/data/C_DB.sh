#!/usr/bin/env bash
set -euo pipefail

# ------------------------------- НАСТРОЙКИ -------------------------------
IMAGE="jacobalberty/firebird:4.0"
HOST_DATA_DIR="${HOME}/fb-data"
CONTAINER_NAME="tmp-fb-$(date +%s)"
FB_USER="SYSDBA"
FB_PASSWORD="masterkey"
DB_NAME="test_db.fdb"
DB_PATH="/firebird/data/${DB_NAME}"
HOST_PORT=3051               # любой свободный порт на хосте
FB_PORT=3050                 # внутри контейнера
ISQL="/usr/local/firebird/bin/isql"
FBGUARD="/usr/local/firebird/bin/fbguard"
SCHEMA_FILE="schema.sql"

# ------------------------------ ПОДГОТОВКА -------------------------------
mkdir -p "${HOST_DATA_DIR}"
chmod 777 "${HOST_DATA_DIR}"
# ---------- 1️Запуск «пустого» контейнера (чтобы он не стартовал сам) ----------
docker run -d \
    --name "${CONTAINER_NAME}" \
    -e FIREBIRD_USER="${FB_USER}" \
    -e FIREBIRD_PASSWORD="${FB_PASSWORD}" \
    -p "${HOST_PORT}:${FB_PORT}" \
    -v "${HOST_DATA_DIR}:/firebird/data" \
    "${IMAGE}" \
    tail -f /dev/null   # «запрещаем» автоматический старт fbguard

# -------------------------- 2️Запуск сервера ---------------------------
docker exec -u firebird "${CONTAINER_NAME}" "${FBGUARD}" -daemon -forever &

# ----------------------------- 3️Ожидание -----------------------------
echo " Ожидаем, пока Firebird займёт порт ${HOST_PORT}..."
for i in {1..30}; do
    if ss -ltnp | grep -q ":${HOST_PORT} "; then
        echo " Порт открыт → сервер готов"
        break
    fi
    sleep 1
done

if ! ss -ltnp | grep -q ":${HOST_PORT} "; then
    echo " Сервер не стартовал в течение 30 сек."
    docker logs "${CONTAINER_NAME}" | tail -n 20
    docker rm -f "${CONTAINER_NAME}" >/dev/null
    exit 1
fi

# ----------------- 4️Создание БД (удаляем старую, затем создаём) ---------------
echo " Удаляем старую базу, если существует..."
docker exec -u firebird "${CONTAINER_NAME}" rm -f "${DB_PATH}" || true

echo " Создаём новую базу данных..."
docker exec -i -u firebird "${CONTAINER_NAME}" "${ISQL}" \
  -user "${FB_USER}" -password "${FB_PASSWORD}" <<SQL
CREATE DATABASE '${DB_PATH}' USER '${FB_USER}' PASSWORD '${FB_PASSWORD}' PAGE_SIZE 4096;
COMMIT;
QUIT;
SQL

# -------------------- 5️Исправляем права и создаём роль/пользователя --------------------
echo " Исправляем права на системную базу безопасности..."
docker exec -u root "${CONTAINER_NAME}" mkdir -p /firebird/system
docker exec -u root "${CONTAINER_NAME}" chown -R firebird:firebird /firebird/system
docker exec -u root "${CONTAINER_NAME}" chmod 755 /firebird/system
docker exec -u root "${CONTAINER_NAME}" touch /firebird/system/security4.fdb
docker exec -u root "${CONTAINER_NAME}" chown firebird:firebird /firebird/system/security4.fdb
docker exec -u root "${CONTAINER_NAME}" chmod 644 /firebird/system/security4.fdb

echo " Создаём роль и пользователя..."
docker exec -i -u firebird "${CONTAINER_NAME}" "${ISQL}" \
  -user "${FB_USER}" -password "${FB_PASSWORD}" "${DB_PATH}" <<'SQL'
CREATE ROLE my_role;
CREATE USER alice PASSWORD 'alicepwd';
GRANT my_role TO alice;
GRANT RDB$ADMIN TO alice;
COMMIT;
QUIT;
SQL

# ----------------- 4️Добавляем Firebird-utils в PATH -----------------
echo "Добавляем Firebird-utils в PATH…"
docker exec -u root "${CONTAINER_NAME}" bash -c \
    'touch /firebird/etc/.bashrc && chown firebird:firebird /firebird/etc/.bashrc'
docker exec -u firebird "${CONTAINER_NAME}" bash -c \
    'echo "export PATH=$PATH:/usr/local/firebird/bin" >> /firebird/etc/.bashrc'

# ----------------------- 5️Подключаемся к базе и создаём таблицы ------------------------
docker cp "${SCHEMA_FILE}" "${CONTAINER_NAME}:/tmp/schema.sql"
# применение содержимого файла schema.sql
docker exec -i -u firebird "${CONTAINER_NAME}" "${ISQL}" \
  -user "${FB_USER}" -password "${FB_PASSWORD}" "${DB_PATH}" <<EOF
$(cat "${SCHEMA_FILE}")
EOF

# ----------------------- 6️Остановка и удаление ------------------------
echo " Останавливаем и удаляем контейнер…"
docker stop "${CONTAINER_NAME}" >/dev/null
docker rm "${CONTAINER_NAME}" >/dev/null

# -------------------------- 7️Финальный вывод -------------------------
echo
echo "Всё готово! Файлы базы находятся в:"
echo "${HOST_DATA_DIR}/${DB_NAME}"
echo "Для проверки создания таблицы нужно использовать:"
echo "docker exec -it "${CONTAINER_NAME}" bash"
echo "/usr/local/firebird/bin/isql -user SYSDBA -password masterkey /firebird/data/test_db.fdb"
echo "SHOW TABLES"
echo "или"
echo "SELECT RDB\$RELATION_NAME FROM RDB\$RELATIONS WHERE RDB\$SYSTEM_FLAG = 0;"