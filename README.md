# UTN Movies Library Backend

Backend API RESTful para una plataforma de catalogación y reseñado de películas, desarrollado con Spring Boot como
proyecto de la Universidad Tecnológica Nacional (UTN).

## Tabla de Contenidos

- [Stack Tecnológico](#stack-tecnológico)
- [Arquitectura del Proyecto](#arquitectura-del-proyecto)
- [Modelo de Datos](#modelo-de-datos)
- [Autenticación y Seguridad](#autenticación-y-seguridad)
- [API Endpoints](#api-endpoints)
- [Features](#features)

---

## Stack Tecnológico

| Capa                       | Tecnología                                 |
|----------------------------|--------------------------------------------|
| **Lenguaje**               | Java 25                                    |
| **Framework**              | Spring Boot 4.0.6                          |
| **Build Tool**             | Maven (con Maven Wrapper)                  |
| **Base de Datos**          | PostgreSQL                                 |
| **ORM**                    | Spring Data JPA (Hibernate)                |
| **Autenticación**          | JWT (Json Web Tokens) via cookies HttpOnly |
| **Seguridad**              | Spring Security                            |
| **Hashing de contraseñas** | BCrypt                                     |
| **Validación**             | Jakarta Bean Validation                    |
| **Subida de archivos**     | Multipart File Upload                      |

---

## Arquitectura del Proyecto

El proyecto sigue una arquitectura **layered (por capas)** con el patrón de **Single Responsibility Services**, donde
cada operación CRUD tiene su propio servicio dedicado.

```
src/main/java/com/utntp/utnmovieslibrarybackend/
├── UtnMoviesLibraryBackendApplication.java   ← Entry point
│
├── config/                                    ← Configuraciones generales (pendiente)
│
├── controller/                                ← Capa de presentación (Endpoints REST)
│   ├── auth/                                  ← Autenticación (login, register, logout, me)
│   ├── genre/                                 ← CRUD de géneros
│   ├── movie/                                 ← CRUD de películas
│   ├── review/                                ← CRUD de reseñas
│   ├── savedmovie/                            ← Gestión de películas guardadas
│   └── user/                                  ← Gestión de usuarios
│
├── dto/                                       ← Data Transfer Objects
│   ├── request/                               ← DTOs de entrada (validados con @Valid)
│   │   ├── auth/
│   │   ├── genre/
│   │   ├── movie/
│   │   ├── review/
│   │   └── savedmovie/
│   └── response/                              ← DTOs de salida
│       ├── auth/
│       ├── exception/
│       ├── genre/
│       ├── movie/
│       ├── review/
│       └── user/
│
├── enums/                                     ← Enumeraciones
│   └── UserRoleEnum.java                      ← USER, ADMIN
│
├── exception/                                 ← Excepciones personalizadas
│   ├── DuplicateResourceException.java        ← 409 Conflict
│   ├── FileManagerException.java              ← Errores de archivos
│   ├── ResourceNotFoundException.java         ← 404 Not Found
│   ├── WrongPasswordException.java            ← 401 Unauthorized
│   └── handler/
│       └── GlobalExceptionHandler.java        ← Manejo global de excepciones
│
├── mapper/                                    ← Conversión Entity ↔ DTO
│   ├── genre/
│   ├── movie/
│   ├── review/
│   ├── savedmovie/
│   └── user/
│
├── model/                                     ← Entidades JPA
│   ├── genre/Genre.java
│   ├── movie/Movie.java
│   ├── review/Review.java
│   ├── savedMovie/SavedMovie.java
│   └── user/User.java
│
├── repository/                                ← Capa de acceso a datos (Spring Data JPA)
│   ├── genre/JpaGenreRepository.java
│   ├── movie/JpaMovieRepository.java
│   ├── review/JpaReviewRepository.java
│   ├── savedMovie/JpaSavedMovieRepository.java
│   └── user/JpaUserRepository.java
│
├── security/                                  ← Seguridad y autenticación
│   ├── CorsConfig.java                        ← Configuración CORS (localhost:5173)
│   ├── JwtFilter.java                         ← Filtro JWT OncePerRequestFilter
│   ├── JwtService.java                        ← Generación, parsing y validación de JWT
│   ├── PasswordConfig.java                    ← Bean BCryptPasswordEncoder
│   ├── SecurityConfig.java                    ← SecurityFilterChain
│   ├── UserPrincipal.java                     ← Implementación de UserDetails
│   └── WebMvcConfig.java                      ← Servido de archivos estáticos (uploads)
│
├── service/                                   ← Capa de lógica de negocio
│   ├── auth/                                  ← Login y Register
│   ├── file/FileManagerService.java           ← Gestión de archivos (subida)
│   ├── genre/                                 ← Creator, Finder, Searcher, Updater, Deleter
│   ├── movie/                                 ← Creator, Finder, Searcher, Updater, Deleter
│   ├── review/                                ← Creator, Finder, Searcher, Updater, Deleter, AverageFinder
│   ├── savedMovie/                            ← Creator, Finder, Searcher, Deleter
│   └── user/                                  ← Finder, Searcher, Updater, Deleter
│
└── utils/                                     ← Utilidades
    └── BlankToNullUtility.java                ← Convierte strings vacíos a null
```

### Patrón de Servicios (Single Responsibility)

Cada entidad tiene múltiples servicios especializados en una sola responsabilidad:

| Servicio           | Responsabilidad                           |
|--------------------|-------------------------------------------|
| `*CreatorService`  | Crear una nueva entidad                   |
| `*FinderService`   | Buscar una entidad por ID                 |
| `*SearcherService` | Listar entidades con filtros y paginación |
| `*UpdaterService`  | Actualizar una entidad existente          |
| `*DeleterService`  | Eliminar una entidad                      |

---

## Modelo de Datos

### Diagrama ER

```
┌──────────────┐       ┌──────────────────┐       ┌──────────────┐
│    genres     │       │      movies      │       │    users     │
├──────────────┤       ├──────────────────┤       ├──────────────┤
│ id (PK)      │◄──┐   │ id (PK)          │   ┌──►│ id (PK)      │
│ name (UQ)    │   └───│ genre_id (FK)    │   │   │ name         │
└──────────────┘       │ title            │   │   │ email (UQ)   │
                       │ synopsis         │   │   │ password     │
                       │ director         │   │   │ role         │
                       │ release_year     │   │   │ pfp_url      │
                       │ poster_url       │   │   └──────────────┘
                       │ average_rating   │   │          ▲
                       │ duration         │   │          │
                       │ trailer_url      │   │          │
                       └──────────────────┘   │          │
                              ▲               │          │
                              │               │          │
                       ┌──────┴───────────────┴──┐       │
                       │        reviews          │       │
                       ├─────────────────────────┤       │
                       │ id (PK)                 │       │
                       │ rating (1-5)            │       │
                       │ comment                 │       │
                       │ user_id (FK) ───────────┼───────┘
                       │ movie_id (FK) ──────────┘
                       │ created_at              │
                       └─────────────────────────┘

                       ┌─────────────────────────┐
                       │   user_saved_movies     │
                       ├─────────────────────────┤
                       │ id (PK)                 │
                       │ user_id (FK) ───────────┼──► users.id
                       │ movie_id (FK) ──────────┼──► movies.id
                       │ saved_at                │
                       └─────────────────────────┘
```

### Entidades

#### User

| Campo      | Tipo         | Restricciones                |
|------------|--------------|------------------------------|
| `id`       | Long         | PK, auto-incremental         |
| `name`     | String       | NotNull                      |
| `email`    | String       | NotNull, Unique              |
| `password` | String       | NotNull, hasheado con BCrypt |
| `role`     | UserRoleEnum | NotNull, default: USER       |
| `pfpUrl`   | String       | Nullable (foto de perfil)    |

#### Movie

| Campo           | Tipo    | Restricciones                |
|-----------------|---------|------------------------------|
| `id`            | Long    | PK, auto-incremental         |
| `title`         | String  | NotNull                      |
| `synopsis`      | String  | NotNull, max 2000 chars      |
| `director`      | String  | NotNull                      |
| `releaseYear`   | Integer | NotNull, Min(1888)           |
| `posterUrl`     | String  | Nullable                     |
| `duration`      | Double  | Nullable                     |
| `trailerUrl`    | String  | Nullable                     |
| `averageRating` | Double  | Calculado via `@Formula` SQL |
| `genre`         | Genre   | FK, ManyToOne (LAZY)         |

#### Genre

| Campo  | Tipo   | Restricciones        |
|--------|--------|----------------------|
| `id`   | Long   | PK, auto-incremental |
| `name` | String | NotNull, Unique      |

#### Review

| Campo       | Tipo          | Restricciones                  |
|-------------|---------------|--------------------------------|
| `id`        | Long          | PK, auto-incremental           |
| `rating`    | Double        | NotNull, Min(1), Max(5)        |
| `comment`   | String        | NotNull, max 1000 chars        |
| `user`      | User          | FK, ManyToOne (LAZY)           |
| `movie`     | Movie         | FK, ManyToOne (LAZY)           |
| `createdAt` | LocalDateTime | Auto-generado en `@PrePersist` |

#### SavedMovie

| Campo     | Tipo          | Restricciones                  |
|-----------|---------------|--------------------------------|
| `id`      | Long          | PK, auto-incremental           |
| `user`    | User          | FK, ManyToOne (LAZY)           |
| `movie`   | Movie         | FK, ManyToOne (LAZY)           |
| `savedAt` | LocalDateTime | Auto-generado en `@PrePersist` |

---

## Autenticación y Seguridad

### Flujo de Autenticación

```
┌─────────┐    POST /api/auth/login     ┌────────────┐
│  Client  │ ──────────────────────────► │   Server   │
│          │    {email, password}        │            │
│          │ ◄────────────────────────── │            │
│          │    Set-Cookie: token=JWT    │            │
└─────────┘                              └────────────┘

┌─────────┐    GET /api/movies           ┌────────────┐
│  Client  │ ──────────────────────────► │   Server   │
│          │    Cookie: token=JWT        │            │
│          │ ◄────────────────────────── │            │
│          │    200 OK + JSON data       │            │
└─────────┘                              └────────────┘
```

### Mecanismos de Seguridad

| Mecanismo              | Detalle                                                                                     |
|------------------------|---------------------------------------------------------------------------------------------|
| **JWT en cookies**     | El token se almacena en una cookie HttpOnly (no expuesta a JavaScript)                      |
| **JwtFilter**          | Filtro `OncePerRequestFilter` que intercepta todas las peticiones                           |
| **Endpoints públicos** | Login, Register, GET movies/genres/reviews no requieren token                               |
| **BCrypt**             | Contraseñas hasheadas con `BCryptPasswordEncoder`                                           |
| **CORS**               | Configurado para permitir origen `http://localhost:5173`                                    |
| **@PreAuthorize**      | Control de acceso a nivel de método en controllers                                          |
| **Roles**              | `USER` y `ADMIN` — los ADMIN tienen acceso total, los USER solo gestionan sus propios datos |

### Endpoints Públicos (sin JWT)

- `POST /api/auth/login`
- `POST /api/auth/register`
- `GET /api/movies/**`
- `GET /api/genres/**`
- `GET /api/reviews/**`
- `GET /uploads/**`

### Control de Acción por Rol

| Recurso                       | USER                 | ADMIN |
|-------------------------------|----------------------|-------|
| Ver películas/géneros/reseñas | ✅                    | ✅     |
| Crear reseña                  | ✅ (autenticado)      | ✅     |
| Editar/Eliminar reseña propia | ✅                    | ✅     |
| Editar/Eliminar reseña ajena  | ❌                    | ✅     |
| CRUD películas                | ❌                    | ✅     |
| CRUD géneros                  | ❌                    | ✅     |
| Gestionar películas guardadas | ✅ (solo las propias) | ✅     |
| CRUD usuarios                 | ❌                    | ✅     |
| Ver perfil propio             | ✅                    | ✅     |
| Ver/editar perfil ajeno       | ❌                    | ✅     |

---

## API Endpoints

### Autenticación

| Método | Ruta                 | Autenticación | Descripción                                                    |
|--------|----------------------|---------------|----------------------------------------------------------------|
| `POST` | `/api/auth/login`    | Pública       | Iniciar sesión. Retorna `UserResponse` + cookie JWT            |
| `POST` | `/api/auth/register` | Pública       | Registrar usuario nuevo (acepta multipart para foto de perfil) |
| `POST` | `/api/auth/logout`   | Pública       | Cerrar sesión (elimina cookie JWT)                             |
| `GET`  | `/api/auth/me`       | Cualquier rol | Retorna el usuario autenticado actual                          |
| `GET`  | `/api/auth/admin`    | Solo ADMIN    | Verifica si el usuario tiene rol ADMIN                         |

### Géneros

| Método   | Ruta               | Autenticación | Descripción                         |
|----------|--------------------|---------------|-------------------------------------|
| `GET`    | `/api/genres`      | Pública       | Listar todos los géneros (paginado) |
| `GET`    | `/api/genres/{id}` | Pública       | Obtener género por ID               |
| `POST`   | `/api/genres`      | Solo ADMIN    | Crear género nuevo                  |
| `PUT`    | `/api/genres/{id}` | Solo ADMIN    | Actualizar género                   |
| `DELETE` | `/api/genres/{id}` | Solo ADMIN    | Eliminar género                     |

### Películas

| Método   | Ruta               | Autenticación | Descripción                                                       |
|----------|--------------------|---------------|-------------------------------------------------------------------|
| `GET`    | `/api/movies`      | Pública       | Listar películas (paginado, filtrable por `genre` y `searchText`) |
| `GET`    | `/api/movies/{id}` | Pública       | Obtener película por ID                                           |
| `POST`   | `/api/movies`      | Solo ADMIN    | Crear película (multipart/form-data para poster)                  |
| `PUT`    | `/api/movies/{id}` | Solo ADMIN    | Actualizar película                                               |
| `DELETE` | `/api/movies/{id}` | Solo ADMIN    | Eliminar película                                                 |

**Parámetros de consulta para `GET /api/movies`:**

- `page` (default: 0) — Número de página
- `size` (default: 10) — Elementos por página
- `genre` — Filtrar por nombre de género
- `searchText` — Buscar por título o director (case-insensitive)

### Reseñas

| Método   | Ruta                | Autenticación               | Descripción                                                        |
|----------|---------------------|-----------------------------|--------------------------------------------------------------------|
| `GET`    | `/api/reviews`      | Pública                     | Listar todas las reseñas (paginado, filtro opcional por `movieId`) |
| `GET`    | `/api/reviews/{id}` | Pública                     | Obtener reseña por ID                                              |
| `POST`   | `/api/reviews`      | Autenticado (cualquier rol) | Crear reseña (se asigna al usuario actual)                         |
| `PUT`    | `/api/reviews/{id}` | ADMIN o dueño               | Actualizar reseña                                                  |
| `DELETE` | `/api/reviews/{id}` | ADMIN o dueño               | Eliminar reseña                                                    |

### Películas Guardadas (Favoritos)

| Método   | Ruta                    | Autenticación | Descripción                                              |
|----------|-------------------------|---------------|----------------------------------------------------------|
| `GET`    | `/api/savedmovies`      | Autenticado   | Listar películas guardadas del usuario actual (paginado) |
| `GET`    | `/api/savedmovies/{id}` | Pública       | Verificar si existe una película guardada                |
| `POST`   | `/api/savedmovies`      | Autenticado   | Guardar una película como favorita                       |
| `DELETE` | `/api/savedmovies/{id}` | ADMIN o dueño | Eliminar película guardada                               |

### Usuarios

| Método   | Ruta              | Autenticación | Descripción                                                |
|----------|-------------------|---------------|------------------------------------------------------------|
| `GET`    | `/api/users`      | Solo ADMIN    | Listar todos los usuarios (paginado)                       |
| `GET`    | `/api/users/{id}` | ADMIN o dueño | Obtener usuario por ID                                     |
| `PUT`    | `/api/users/{id}` | ADMIN o dueño | Actualizar usuario (soporta multipart para foto de perfil) |
| `DELETE` | `/api/users/{id}` | Solo ADMIN    | Eliminar usuario                                           |

---

## Features

### 1. Autenticación JWT con Cookies HttpOnly

Los tokens JWT se almacenan en cookies HttpOnly, proporcionando mayor seguridad que el header Authorization
tradicional (protección contra XSS).

### 2. Sistema de Roles (USER / ADMIN)

Control de acceso granular basado en roles:

- **ADMIN**: Acceso total a todas las operaciones CRUD
- **USER**: Puede crear reseñas, gestionar sus películas guardadas y perfil propio

### 3. Paginación y Ordenamiento

Todos los listados soportan paginación con parámetros `page` y `size`, utilizando Spring Data `Pageable`.

### 4. Filtrado y Búsqueda

- **Películas**: Filtrado por género y búsqueda por título/director (case-insensitive)
- **Reseñas**: Filtrado por movieId

### 5. Subida de Archivos (Posters y Fotos de Perfil)

- Subida de imágenes para posters de películas y fotos de perfil
- Almacenamiento en directorio local `uploads/`
- Archivos servidos como recursos estáticos con caché de 7 días
- Tamaño máximo: 10MB
- Generación de nombres únicos con UUID

### 6. Rating Promedio Calculado

El campo `averageRating` de cada película se calcula automáticamente mediante una subquery SQL (`@Formula`), evitando
costosas agregaciones en tiempo de ejecución.

### 7. Gestión de Excepciones Global

`GlobalExceptionHandler` centraliza el manejo de errores con respuestas JSON consistentes:

| Excepción                         | Código HTTP | Descripción                |
|-----------------------------------|-------------|----------------------------|
| `ResourceNotFoundException`       | 404         | Recurso no encontrado      |
| `DuplicateResourceException`      | 409         | Recurso duplicado          |
| `WrongPasswordException`          | 401         | Contraseña incorrecta      |
| `MethodArgumentNotValidException` | 400         | Error de validación        |
| `AccessDeniedException`           | 403         | Acceso denegado            |
| `Exception` (genérica)            | 500         | Error interno del servidor |

### 8. Validación de DTOs

Todos los request DTOs utilizan anotaciones Jakarta Bean Validation (`@NotBlank`, `@Email`, `@Size`, `@Min`, `@Max`,
etc.) para validar la entrada antes de procesarla.

### 9. CORS Configurado

Permite solicitudes desde el frontend en `http://localhost:5173` (Vite/Vue/React) con credenciales (cookies).

### 10. Auto-creación de Géneros

Al crear una película, si el género especificado no existe, se crea automáticamente.

---

## Licencia

Proyecto académico — Universidad Tecnológica Nacional (UTN).
