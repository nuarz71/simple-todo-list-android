package io.github.nuarz71.todolist.android.data.repository

import io.github.nuarz71.todolist.android.data.entity.ToDoEntity
import io.github.nuarz71.todolist.android.data.repository.parameter.AddEditToDoParameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

internal class DefaultToDoRepository : ToDoRepository {
    override suspend fun addOrEditToDo(parameter: AddEditToDoParameter): Result<Boolean> {
        //TODO: add implementation
        return Result.success(true)
    }
    
    override suspend fun deleteToDo(id: Long): Result<Boolean> {
        //TODO: add implementation
        return Result.success(true)
    }
    
    override fun todoList(): Flow<List<ToDoEntity>> {
        //TODO: add implementation
        return emptyFlow()
    }
}