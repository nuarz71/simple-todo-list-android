package io.github.nuarz71.todolist.android.domain.usecase

import io.github.nuarz71.todolist.android.data.repository.ToDoRepository
import io.github.nuarz71.todolist.android.domain.dto.TaskDto
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(
    private val repository: ToDoRepository
) {
    suspend operator fun invoke(id: Long): Result<TaskDto> =
        repository.getTask(id = id).map { entity ->
            TaskDto(
                id = entity.id,
                title = entity.title,
                description = entity.description,
                dueDate = entity.dueDate
            )
        }
}