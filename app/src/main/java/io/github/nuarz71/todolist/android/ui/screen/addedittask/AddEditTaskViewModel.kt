package io.github.nuarz71.todolist.android.ui.screen.addedittask

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.nuarz71.todolist.android.domain.dto.TaskDto
import io.github.nuarz71.todolist.android.domain.usecase.AddTaskUseCase
import io.github.nuarz71.todolist.android.domain.usecase.DeleteTaskUseCase
import io.github.nuarz71.todolist.android.domain.usecase.EditTaskUseCase
import io.github.nuarz71.todolist.android.domain.usecase.GetTaskUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    application: Application,
    private val savedStateHandle: SavedStateHandle,
    private val addTaskUseCase: AddTaskUseCase,
    private val editTaskUseCase: EditTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val getTaskUseCase: GetTaskUseCase,
) : AndroidViewModel(application) {
    
    val id: Long
        get() = savedStateHandle.get<Long>("id") ?: 0
    
    private val _uiState = MutableStateFlow(AddEditTaskUiState())
    val uiState: StateFlow<AddEditTaskUiState> = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5.seconds),
        initialValue = AddEditTaskUiState()
    )
    
    
    init {
        if (id > 0) loadTask()
    }
    
    private fun setLoadingUiState() = _uiState.update {
        it.copy(
            isLoading = true,
            snackBarMessage = null
        )
    }
    
    fun loadTask() {
        viewModelScope.launch(Dispatchers.IO) {
            setLoadingUiState()
            getTaskUseCase(
                id = id
            ).onSuccess { task ->
                if (task != null) {
                    _uiState.update { state ->
                        state.copy(
                            title = task.title,
                            description = task.description.orEmpty(),
                            dueDate = task.dueDate,
                            isLoading = false
                        )
                    }
                } else {
                    _uiState.update { state ->
                        state.copy(
                            snackBarMessage = "Task Not Found",
                            isLoading = false
                        )
                    }
                }
            }.onFailure { error ->
                _uiState.update { state ->
                    state.copy(
                        snackBarMessage = error.message,
                        isLoading = false
                    )
                }
            }
        }
    }
    
    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            setLoadingUiState()
            if (id > 0) {
                editTaskUseCase(
                    TaskDto(
                        id = id,
                        title = uiState.value.title,
                        description = uiState.value.description,
                        dueDate = uiState.value.dueDate!!
                    )
                ).onSuccess { saved ->
                    _uiState.update {
                        it.copy(
                            saved = saved,
                            isLoading = false
                        )
                    }
                }.onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            snackBarMessage = error.message
                        )
                    }
                }
            } else {
                addTaskUseCase(
                    title = uiState.value.title,
                    description = uiState.value.description,
                    dueDate = uiState.value.dueDate!!
                ).onSuccess { saved ->
                    _uiState.update {
                        it.copy(
                            saved = saved,
                            isLoading = false
                        )
                    }
                }.onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            snackBarMessage = error.message
                        )
                    }
                }
            }
        }
    }
    
    fun setTitle(title: String) = _uiState.update {
        it.copy(title = title)
    }
    
    fun setDescription(description: String) = _uiState.update {
        it.copy(description = description)
    }
    
    fun setDueDate(timestamp: Long?) = _uiState.update {
        it.copy(
            dueDate = timestamp?.let { millis ->
                LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(millis),
                    ZoneId.systemDefault()
                )
            }
        )
    }
    
    fun deleteTask() {
        viewModelScope.launch(Dispatchers.IO) {
            setLoadingUiState()
            deleteTaskUseCase(
                id = id
            ).onSuccess {
                _uiState.update {
                    it.copy(
                        saved = true,
                        isLoading = false
                    )
                }
            }.onFailure { error ->
                _uiState.update {
                    it.copy(
                        snackBarMessage = error.message,
                        isLoading = false
                    )
                }
            }
        }
    }
}

data class AddEditTaskUiState(
    val title: String = "",
    val description: String = "",
    val dueDate: LocalDateTime? = null,
    val isLoading: Boolean = false,
    val snackBarMessage: String? = null,
    val saved: Boolean = false
)