package io.github.nuarz71.todolist.android.ui.screen.listtask

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.nuarz71.todolist.android.R
import io.github.nuarz71.todolist.android.domain.dto.TaskDto
import io.github.nuarz71.todolist.android.domain.usecase.DeleteTaskUseCase
import io.github.nuarz71.todolist.android.domain.usecase.GenerateTasksUseCase
import io.github.nuarz71.todolist.android.domain.usecase.ListTaskUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class ListTaskViewModel @Inject constructor(
    application: Application,
    private val listTaskUseCase: ListTaskUseCase,
    private val generateTasksUseCase: GenerateTasksUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
) : AndroidViewModel(application) {
    
    var snackbarMessage by mutableStateOf("")
        private set
    
    val tasks = listTaskUseCase()
        .flowOn(Dispatchers.IO)
        .catch {
            snackbarMessage = it.message.orEmpty()
        }
        .stateIn(
            scope = viewModelScope,
            initialValue = emptyList(),
            started = SharingStarted.WhileSubscribed(3.seconds)
        )
    
    fun generateTasks() = viewModelScope.launch(Dispatchers.IO) {
        generateTasksUseCase()
    }
    
    fun deleteTask(task: TaskDto) = viewModelScope.launch(Dispatchers.IO) {
        deleteTaskUseCase(id = task.id).onFailure {
            snackbarMessage = getApplication<Application>().getString(
                R.string.delete_task_failed_message,
                task.title
            )
        }
    }
}