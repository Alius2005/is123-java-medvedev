#!/bin/bash
echo "🔨 Сборка проекта (Gradle)..."
./gradlew clean bootJar

if [ $? -eq 0 ]; then
    echo "✅ Сборка успешна! Запуск приложения..."
    # Запуск собранного JAR файла
    java -jar build/libs/is123-java-medvedev-0.0.1-SNAPSHOT.jar
else
    echo "❌ Ошибка сборки."
fi
