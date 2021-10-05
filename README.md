**Application Architecture**

- MVVM(Model-View-ViewModel) architecture with applying separation of concern by layers.

- Data Layer - includes network process and local and remote data repositories. 
- Domain Layer - includes UsaCase classes and Mapper classes.
- UI Layer - includes Views(activity, fragment), ViewModels, ViewStates.

**Tech Stack**

- Retrofit - Turns your HTTP API into an interface.
- Moshi - A converter for serialization to and from JSON.
- Kotlin Coroutines - Used for async long-running tasks.
- Hilt - A dependency injection library that reduces the boilerplate of doing manual dependency injection.
- LiveData - lifecycle-aware observable.
- DataBinding - To bind UI components in layouts to data sources.
- Glide - Image loading framework.
- Unit Test - written for viewModel and viewState classes.

To persist ui configuration, need to use shared preferences. 
