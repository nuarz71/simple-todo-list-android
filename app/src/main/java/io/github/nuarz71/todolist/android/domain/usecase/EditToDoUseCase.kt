package io.github.nuarz71.todolist.android.domain.usecase

import io.github.nuarz71.todolist.android.data.repository.ToDoRepository
import io.github.nuarz71.todolist.android.data.repository.parameter.AddEditToDoParameter
import io.github.nuarz71.todolist.android.domain.dto.ToDoDto
import javax.inject.Inject

class EditToDoUseCase @Inject constructor(
    private val repository: ToDoRepository
) {
    
    suspend operator fun invoke(todo: ToDoDto): Result<Boolean> {
        return repository.addToDo(
            AddEditToDoParameter(
                id = todo.id,
                title = todo.title,
                description = todo.description,
                dueDate = todo.dueDate
            )
        )
    }
}