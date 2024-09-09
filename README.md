# Main Architecture
A classic clean architecture model was used, divided into three layers: UI, DOMAIN, and DATA.
A multi-modular solution wasn't deemed necessary for this POC, but we still have a heavily decoupled architecture, allowing for more cohesive code and interchangeable components.
The design pattern used was MVI (Model-View-Intent) due to its compatibility with Compose and the advantage of having a single source of truth.

# Main Flow
As the user enters the app, a launch effect fetches the task list through the ViewModel, TaskRepository, and the RetrofitService. The task list is saved locally. 
This way, if at any point the user accesses the app without an internet connection, they can still view their task lists.
If a task list already exists, only the new tasks will be updated. (Currently, since a task's resolved status and comments are only saved locally, local tasks are given priority).
The task list view state is updated, and the view is updated accordingly. When a user clicks on a task, its information will be fetched from the local database first if available, 
reducing unnecessary network calls.(Remember that if any task was updated remotely, its details would have been updated when the app was launched.)

# The general flow can be summarized as:
screen -> viewmodel -> interface -> remote or local repository -> service or database.


# MAIN TOOLS
- `Dagger-Hilt` for dependency injection
- `Retrofit` for HTTP requests
- `Room`for the local database
- `Junit 5` for unit tests
