package io.github.nuarz71.todolist.android.data.repository

import io.github.nuarz71.todolist.android.data.entity.TaskEntity
import io.github.nuarz71.todolist.android.data.repository.parameter.AddEditTaskParameter
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
    
    fun taskList(): Flow<List<TaskEntity>>
    suspend fun addOrEditTask(parameter: AddEditTaskParameter): Result<Boolean>
    suspend fun deleteTask(id: Long): Result<Unit>
    suspend fun getTask(id: Long): Result<TaskEntity?>
    suspend fun generateTasks() : Result<Unit>
}