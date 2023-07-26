package io.github.nuarz71.todolist.android.data.local

import io.github.nuarz71.todolist.android.data.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

internal class RoomLocalDataSource(
    private val taskDao: TaskDao
) : LocalDataSource {
    override suspend fun addTask(task: TaskEntity): Result<Boolean> = runCatching {
        val rowId = taskDao.insert(task = task)
        rowId > 0
    }
    
    override suspend fun getTask(id: Long): Result<TaskEntity?> = runCatching {
        taskDao.getTaskById(id = id)
    }
    
    override fun getListTaskFlow(): Flow<List<TaskEntity>> = taskDao.getAllToDo()
    
    override suspend fun deleteTask(id: Long): Result<Unit> = runCatching {
        taskDao.deleteTaskById(id = id)
    }
    
    override suspend fun addAllTask(tasks: List<TaskEntity>) = runCatching {
        taskDao.insertAll(tasks = tasks)
    }
}