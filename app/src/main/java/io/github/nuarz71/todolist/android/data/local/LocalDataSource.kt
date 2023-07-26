package io.github.nuarz71.todolist.android.data.local

import io.github.nuarz71.todolist.android.data.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun addTask(task: TaskEntity): Result<Boolean>
    suspend fun getTask(id: Long): Result<TaskEntity?>
    fun getListTaskFlow(): Flow<List<TaskEntity>>
    suspend fun deleteTask(id: Long): Result<Unit>
    suspend fun addAllTask(tasks: List<TaskEntity>) : Result<Unit>
}