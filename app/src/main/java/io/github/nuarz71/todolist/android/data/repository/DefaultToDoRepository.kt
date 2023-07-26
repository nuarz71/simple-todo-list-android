package io.github.nuarz71.todolist.android.data.repository

import io.github.nuarz71.todolist.android.data.entity.TaskEntity
import io.github.nuarz71.todolist.android.data.local.ToDoDatabase
import io.github.nuarz71.todolist.android.data.repository.parameter.AddEditTaskParameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

internal class DefaultToDoRepository(
    private val db: ToDoDatabase
) : ToDoRepository {
    override suspend fun addOrEditTask(parameter: AddEditTaskParameter): Result<Boolean> {
        //TODO: add implementation
        return Result.success(true)
    }
    
    override suspend fun deleteTask(id: Long): Result<Boolean> {
        //TODO: add implementation
        return Result.success(true)
    }
    
    override fun taskList(): Flow<List<TaskEntity>> {
        //TODO: add implementation
        return emptyFlow()
    }
    
    override suspend fun getTask(id: Long): Result<TaskEntity> {
        //TODO: add implementation
        return Result.failure(Exception("Something wrong"))
    }
}