package io.github.nuarz71.todolist.android.domain.usecase

import io.github.nuarz71.todolist.android.data.repository.ToDoRepository
import io.github.nuarz71.todolist.android.domain.dto.ToDoDto
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ListToDoUseCase @Inject constructor(
    private val repository: ToDoRepository
) {
    operator fun invoke() = repository.todoList().map { items ->
        items.map { entity ->
            ToDoDto(
                id = entity.id,
                title = entity.title,
                description = entity.description,
                dueDate = entity.dueDate
            )
        }
    }
}
