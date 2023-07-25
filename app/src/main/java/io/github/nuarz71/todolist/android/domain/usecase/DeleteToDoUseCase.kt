package io.github.nuarz71.todolist.android.domain.usecase

import io.github.nuarz71.todolist.android.data.repository.ToDoRepository
import javax.inject.Inject

class DeleteToDoUseCase @Inject constructor(
    private val repository: ToDoRepository
) {
    
    suspend operator fun invoke(id: Long): Result<Boolean> = repository.deleteToDo(id = id)
}