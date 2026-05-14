# 🎬 CinemaHome – домашний онлайн/офлайн кинотеатр

## 1. Общее описание

**CinemaHome** – это учебное веб‑приложение на Spring Boot, которое позволяет:

- просматривать каталог фильмов и сериалов;
- смотреть детальную страницу фильма/сериала;
- воспроизводить видеофайлы через встроенный HTML5‑плеер;
- работать либо с реальной БД **Firebird**, либо с хранилищем в **JSON‑файлах** (переключается пользователем);
- регистрироваться и входить в систему, использовать роли **ADMIN** и **moderator** для управления контентом.

Основные моменты:

- Приложение запускается на `http://localhost:8080`.
- При старте автоматически создаются пользователи:
    - `admin` / `admin` (роль `ADMIN`)
    - `moderator` / `moderator` (роль `moderator`)
- Для хранения данных поддерживаются два режима:
    - **DB** – Firebird + JPA/Hibernate;
    - **JSON** – чтение/запись в файлы `*.json` в каталоге `/home/student/cinema-json/`.
- Язык — **Java 21**, сборщик — **Gradle 8.5**, фреймворк — **Spring Boot 3.4**.
- UI построен на **Thymeleaf + HTML + CSS** (минималистичная собственная верстка).

---

## 2. Технологический стек

| Категория          | Технология                                              | Назначение                                   |
|--------------------|---------------------------------------------------------|----------------------------------------------|
| Язык               | Java 21                                                | Современный LTS, новая платформа             |
| Сборка             | Gradle, Spring Boot plugin, Dependency Management      | Сборка и управление зависимостями            |
| Web‑фреймворк      | Spring Boot 3.4, Spring MVC                           | Контроллеры, маршруты, обработка запросов    |
| Шаблоны            | Thymeleaf                                              | Серверный рендеринг HTML‑страниц             |
| БД                 | Firebird, драйвер Jaybird                             | Основное реляционное хранилище               |
| ORM/JPA            | Spring Data JPA, Hibernate                            | Работа с сущностями и репозиториями          |
| Альтернативное хранилище | JSON‑файлы + Jackson Databind                       | Работа без установленной БД                  |
| Безопасность       | Spring Security, BCryptPasswordEncoder                | Регистрация, логин, роли и авторизация       |
| Документация API   | springdoc‑openapi‑starter‑webmvc‑ui                   | Swagger UI по пути `/swagger-ui.html`        |
| Утилиты            | Lombok (частично), Random‑бизнес‑логика (MoodAnalyzer) | Сокращение шаблонного кода, «настроение»     |
| CI                 | GitHub Actions (Gradle workflow)                      | Автоматический билд проекта                  |

---

## 3. Функциональность

### Пользователи и безопасность

- Регистрация нового пользователя: форма `/register`, обработчик `POST /register`.
- Вход в систему: страница `/login` с формой Spring Security.
- Роли:
    - `ADMIN` — создаётся автоматически;
    - `moderator` — создаётся автоматически;
    - `user` — назначается по умолчанию при регистрации.
- Доступ:
    - Публично: `/`, `/login`, `/register`, просмотр списков фильмов/сериалов, плеер `/player/**`.
    - Управление контентом: `/admin/**`, часть запросов `/movies/**` и `/api/admin/**` — только для `MODERATOR` и `ADMIN`.

### Контент и навигация

- Главная страница `/`:
    - показывает «фильм дня», случайно выбранный из каталога (`CurationService`);
    - если фильмов нет — выводит приглашение зайти в панель админа;
    - блок выбора, что добавить: фильм или сериал (переходы в `/admin/movies` или `/admin/series`).

- Фильмы:
    - Список: `GET /movies` — шаблон `movie/list.html`;
    - Детальная страница: `GET /movies/{id}` — шаблон `movie/detail.html`;
    - Плеер: `GET /player/movie/{id}` — шаблон `player/movie-player.html`, HTML5 `<video>` с путём `/media/...`.

- Сериалы:
    - Список: `GET /series` — шаблон `series/list.html`;
    - Список серий первого сезона: `GET /series/{id}/episodes` — шаблон `series/episodes.html`;
    - Плеер эпизода: `GET /player/series/{episodeId}` — шаблон `player/series-player.html`, с кнопкой «Следующая серия».

- Справочники:
    - Жанры: `GET /genres` — список жанров;
    - Актёры: `GET /actors` — список актёров.

### Админ‑панель

Доступна пользователям с ролью `MODERATOR` или `ADMIN`.

- Фильмы:
    - `GET /admin/movies` — форма добавления фильма + список;
    - `POST /admin/movies` — создание фильма (title, description, moodTag, filePath).

- Сериалы:
    - `GET /admin/series` — форма добавления сериала;
    - `POST /admin/series` — создаёт сериал, сезон и заданное количество эпизодов (по шаблону имени файлов).

### Переключение режима хранения (DB / JSON)

- Страница выбора: `GET /mode` — шаблон `mode/select.html`.
- Установка режима: `POST /mode` с параметром `mode=DB` или `mode=JSON`.
- Выбранный режим сохраняется в `HttpSession` через `DataModeService` и влияет на то,
  какой адаптер используется в сервисах (`db*Port` или `json*Port`).

---

## 4. Архитектура проекта

Проект построен по слоистой архитектуре с разделением на контроллеры, сервисы, порты/адаптеры и конфигурацию.

- **Controller** – слой презентации, принимает HTTP‑запросы и подготавливает модель для шаблонов Thymeleaf  
  (`HomeController`, `MovieController`, `SeriesController`, `AdminController`, `AuthController`, и др.).

- **Service** – слой бизнес‑логики, агрегирует доступ к данным через порты, использует вспомогательные компоненты  
  (например, `CurationService`, `MovieService`, `SeriesService`, `UserService`, `ReleaseNotifierService`).

- **Port / Adapter** – абстракция доступа к данным и её реализации:
    - Порты: `MoviePort`, `SeriesPort`, `ActorPort`, `GenrePort`, `EpisodePort`, `SeasonPort`, `UserPort`.
    - Адаптеры для БД (JPA): классы `Db*Adapter` работают через Spring Data JPA (`MovieRepository`, `UserRepository` и т.д.).
    - Адаптеры для JSON: классы `Json*Adapter` читают/пишут данные в файлы `*.json` с помощью Jackson.
    - Выбор режима (`DB` или `JSON`) делается в `*Service` через `DataModeService`.

- **Repository** – Spring Data JPA‑репозитории, инкапсулирующие работу с БД  
  (`MovieRepository`, `SeriesRepository`, `UserRepository`, и др.).

- **Config** – конфигурационные классы Spring:
    - `SecurityConfig` (Spring Security),
    - `DbConfig` (DataSource для Firebird),
    - `WebConfig` (раздача `/media/**`),
    - `OpenApiConfig` (Swagger/OpenAPI),
    - `AdminInitializer` (создание admin/moderator),
    - `DataModeService` (режим хранения DB/JSON).

---

## 5. Паттерны проектирования в проекте

| Паттерн                    | Роль/идея                                                                 | Интерфейсы / ключевые классы                                                                                                      | Конкретные реализации / детали                                                                                                                                           |
|----------------------------|---------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Port–Adapter (Hexagonal)** | Отделить бизнес‑логику от деталей хранения и дать возможность легко переключать БД/JSON | Порты: `MoviePort`, `SeriesPort`, `ActorPort`, `GenrePort`, `EpisodePort`, `SeasonPort`, `UserPort`                              | **DB‑адаптеры:** `DbMovieAdapter`, `DbSeriesAdapter`, `DbActorAdapter`, `DbGenreAdapter`, `DbEpisodeAdapter`, `DbSeasonAdapter`, `DbUserAdapter`;<br>**JSON‑адаптеры:** `JsonMovieAdapter`, `JsonSeriesAdapter`, `JsonActorAdapter`, `JsonGenreAdapter`, `JsonEpisodeAdapter`, `JsonSeasonAdapter`, `JsonUserAdapter` |
| **Strategy** (выбор источника данных) | Переключение между разными «стратегиями» хранения: БД vs JSON                        | `DataMode` (enum: `DB`, `JSON`), `DataModeService`                                                                                | В сервисах (`MovieService`, `SeriesService`, `ActorService`, `GenreService`, `EpisodeService`, `UserService`) метод `currentPort()` выбирает либо `db*Port`, либо `json*Port` в зависимости от `DataMode`                                           |
| **Observer**               | Несколько независимых подписчиков реагируют на событие (новый релиз и т.п.) | Интерфейс `ReleaseNotifier`, сервис `ReleaseNotifierService`                                                                      | Наблюдатели: `EmailNotifier` (основной, `@Primary`), `InAppNotifier`, `MoodNotifier` (использует `MoodAnalyzer`); `ReleaseNotifierService` обходит список `ReleaseNotifier` и вызывает `sendNotification()` у каждого                               |
| **Dependency Injection / IoC** | Ослабление связности, внедрение зависимостей через контейнер Spring           | Аннотации Spring: `@Autowired`, `@Qualifier`, `@Component`, `@Service`, `@Repository`, `@Configuration`                            | Все контроллеры, сервисы, адаптеры и конфиги получают зависимости из контекста Spring, нет ручного создания объектов `new` в бизнес‑коде                                                                     |
| **Layered Architecture**   | Разделение приложения на логические слои                                  | Слои: Controller → Service → Port/Adapter → Repository / Storage                                                                  | **Controller:** web‑слой (обрабатывает HTTP и подготавливает модель для Thymeleaf); **Service:** бизнес‑логика; **Port/Adapter:** доступ к данным; **Repository:** JPA‑репозитории и/или JSON‑хранилище                                            |
| **Template Method (через Spring Data)** | Общий шаблон CRUD‑операций в базе с возможностью дописывать специфичные методы | `JpaRepository<T, ID>` и наследники: `MovieRepository`, `SeriesRepository`, `SeasonRepository`, `EpisodeRepository`, `ActorRepository`, `GenreRepository`, `UserRepository` | Spring Data JPA реализует стандартные методы (`save`, `findAll`, `findById`, и т.д.), а конкретные репозитории описывают только интерфейс и при необходимости добавляют свои query‑методы                                                       |
---

## 6. Подготовка окружения

### 6.1 Требования

| Требование          | Версия/описание                                    |
|---------------------|----------------------------------------------------|
| Java                | JDK 21 (или выше)                                  |
| Gradle              | 8.5 (Gradle Wrapper уже включён в репозиторий)    |
| Firebird (опционально) | 3.0+ (для режима хранения в БД)                    |
| ОС                  | Linux/Unix (в коде используются пути `/home/student/...`) |

Firebird и Docker не являются обязательными: можно работать в режиме JSON‑файлов, выбрав его на странице `/mode`.