# 🎬 MovieSpool

MovieSpool is a modern Android application built with Jetpack Compose and structured around MVVM and Clean Architecture principles. The app allows users to browse and search movies, add favorites to bookmarks, and securely register or log in using Firebase Authentication. It delivers a smooth and personalized movie experience tailored to each user.

---

## 🖼️ Screenshots

| Login Screen                            | Register Screen                         |
|-----------------------------------------|-----------------------------------------|
| ![Login](assets/login_screen.png)       | ![Register](assets/register_screen.png) |
| Home Screen                             | Search Screen                           |
| ![Home](assets/home_screen.png)         | ![Search](assets/search_screen.png)     |
| Detail Screen                           | Bookmark Screen                         |
| ![Detail](assets/detail_screen.png)     | ![Bookmark](assets/bookmark_screen.png) |
| Settings Screen                         |
| ![Settings](assets/settings_screen.png) |
---

## 📚 Features

- 🔥 **Popular & Top Rated Movies** — Browse trending and highly rated films via TMDB API.
- 🔍 **Search** — Search for specific movies by title.
- 🎬 **Movie Details** — View movie description, genres, release date, and rating.
- 🧑‍💼 **Firebase Authentication** — Sign up and log in securely.
- 🌙 **Theme Mode** — Toggle between dark and light theme.
- 🔄 **Swipe to Refresh** — Pull down to refresh movie listings.
- 🎨 **Palette API** — Dynamic UI colors based on movie posters.
- ⚙️ **Paging & Caching** — Efficient data loading and offline support with Room and Paging.

## 🛠️ Tech Stack

- **Kotlin**
- **Jetpack Compose** – Modern declarative UI
- **MVVM & Clean Architecture** – Separation of concerns, testability
- **Coroutines & Flow** – Async data handling
- **Hilt (Dagger)** – Dependency Injection
- **Retrofit + Gson + OkHttp** – API communication
- **Firebase Authentication**
- **Room Database + Paging 3** – Local caching and pagination
- **Jetpack Navigation Compose** – Navigation between screens
- **DataStore** – Persistent preferences (theme mode)
- **Coil** – Image loading
- **Palette API** – UI color extraction
- **SwipeRefresh (Accompanist)** – Pull-to-refresh

## 🧠 State Management
### 📦 Response State Wrapper

To represent the result of network or data operations consistently across the app:
```
sealed class ResponseState<out T> {
    object Loading : ResponseState<Nothing>()
    data class Error(val message: String) : ResponseState<Nothing>()
    data class Success<T>(val data: T) : ResponseState<T>()
}
```

### 🧩 UI States (per screen)
Each screen defines its own sealed interface to represent its UI state clearly. Here's an example from the DetailScreen:
```
sealed interface DetailScreenUiState {
    data object Idle : DetailScreenUiState
    data object Loading : DetailScreenUiState
    data class Success(val data: MovieDetail) : DetailScreenUiState
    data class Error(val message: String) : DetailScreenUiState
}
```
This interface is exposed as a StateFlow from the ViewModel:
```
private val _uiState = MutableStateFlow<DetailScreenUiState>(DetailScreenUiState.Idle)
val uiState: StateFlow<DetailScreenUiState> = _uiState.asStateFlow()
```

And then observed in the UI layer using collectAsStateWithLifecycle():

```
val uiState = viewModel.uiState.collectAsStateWithLifecycle()

when (val state = uiState.value) {
    is DetailScreenUiState.Error -> ErrorComponent()
    DetailScreenUiState.Loading -> Loading()
    is DetailScreenUiState.Success -> DetailScreenSuccessComponent(state.data, viewModel)
    else -> Unit
}
```
This pattern improves testability, decouples UI from business logic, and aligns well with the Unidirectional Data Flow (UDF) principle.

## 📂 Project Structure
Below is the overview of the project directory structure, reflecting Clean Architecture principles and modular UI components.
```
├── data
│ ├── local
│ │ ├── mapper
│ │ └── preferences
│ ├── remote
│ │ ├── mapper
│ │ ├── model
│ │ └── paging
│ └── repository
├── di
├── domain
│ ├── model
│ ├── repository
│ └── usecase
│ ├── auth
│ ├── movies
│ ├── bookmark
│ └── settings
├── navigation
├── presentation
│ ├── authorization
│ |  └── components
│ |  ├── login
│ |  │ └── components
│ |  ├── register
│ |  │ └── components
│ ├── bookmark
│ │ └── components
│ ├── detail
│ │ └── components
│ ├── home
│ │ └── components
│ ├── search
│ │ └── components
│ ├── settings
│ │ └── components
│ ├── component
│ └── theme
├── util
└── BootcampfinalApp.kt
```