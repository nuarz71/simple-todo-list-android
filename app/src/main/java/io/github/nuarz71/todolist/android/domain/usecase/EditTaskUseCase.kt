package io.github.nuarz71.todolist.android.domain.usecase

import io.github.nuarz71.todolist.android.data.repository.ToDoRepository
import io.github.nuarz71.todolist.android.data.repository.parameter.AddEditTaskParameter
import io.github.nuarz71.todolist.android.domain.dto.TaskDto
import javax.inject.Inject

class EditTaskUseCase @Inject constructor(
    private val repository: ToDoRepository
) {
    
    suspend operator fun invoke(todo: TaskDto) = repository.addOrEditTask(
        AddEditTaskParameter(
            id = todo.id,
            title = todo.title,
            description = todo.description,
            dueDate = todo.dueDate
        )
    )
}