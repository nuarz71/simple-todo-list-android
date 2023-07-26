package io.github.nuarz71.todolist.android.domain.usecase

import io.github.nuarz71.todolist.android.data.repository.ToDoRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val repository: ToDoRepository
) {
    
    suspend operator fun invoke(id: Long): Result<Unit> = repository.deleteTask(id = id)
}