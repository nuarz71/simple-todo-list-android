package io.github.nuarz71.todolist.android.data.repository

import io.github.nuarz71.todolist.android.data.entity.TaskEntity
import io.github.nuarz71.todolist.android.data.local.LocalDataSource
import io.github.nuarz71.todolist.android.data.repository.parameter.AddEditTaskParameter
import kotlinx.coroutines.flow.Flow

internal class DefaultToDoRepository(
    private val local: LocalDataSource
) : ToDoRepository {
    override suspend fun addOrEditTask(parameter: AddEditTaskParameter): Result<Boolean> =
        local.addTask(
            TaskEntity(
                id = parameter.id ?: 0,
                title = parameter.title,
                description = parameter.description,
                dueDate = parameter.dueDate
            )
        )
    
    override suspend fun deleteTask(id: Long): Result<Unit> = local.deleteTask(id = id)
    
    override fun taskList(): Flow<List<TaskEntity>> = local.getListTaskFlow()
    
    override suspend fun getTask(id: Long): Result<TaskEntity?> = local.getTask(id = id)
}