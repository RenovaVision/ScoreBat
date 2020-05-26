# ScoreBat

## About
It simply loads data from API. Data will be always loaded from local database. Remote data (from API) and Local data is always synchronized.
* Use real [ScoreBat](https://www.scorebat.com/) api.<br>
* Use Kotlin Gradle DSL.<br>
* Use several Gradle modules.<br>

## Built With 🛠
[Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.<br>
[Kotlin Gradle DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - Provides an alternative syntax to the traditional Groovy DSL for Gradle build system. <br>
[Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..<br>
[Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.<br>
[StateFlow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-state-flow/index.html) - Replacement of LiveData for managing UI updates.<br>
[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.<br>
[Jetpack Navigation](https://developer.android.com/guide/navigation) - Component helps you implement navigation.<br>
[ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.<br>
[Koin](https://insert-koin.io/) - Dependency Injection Framework<br>
[Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.<br>
[Moshi](https://github.com/square/moshi) - A modern JSON library for Kotlin and Java.<br>
[Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.<br>
## Architecture
This app uses [MVVM](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.
![Image of MVVM](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)
