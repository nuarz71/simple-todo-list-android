package io.github.nuarz71.todolist.android.domain.usecase

import io.github.nuarz71.todolist.android.data.repository.ToDoRepository
import io.github.nuarz71.todolist.android.domain.dto.TaskDto
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ListTaskUseCase @Inject constructor(
    private val repository: ToDoRepository
) {
    operator fun invoke() = repository.taskList().map { items ->
        items.map { entity ->
            TaskDto(
                id = entity.id,
                title = entity.title,
                description = entity.description,
                dueDate = entity.dueDate
            )
        }
    }
}
