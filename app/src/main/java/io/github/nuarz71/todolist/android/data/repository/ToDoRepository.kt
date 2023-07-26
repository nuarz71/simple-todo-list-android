package io.github.nuarz71.todolist.android.data.repository

import io.github.nuarz71.todolist.android.data.entity.ToDoEntity
import io.github.nuarz71.todolist.android.data.repository.parameter.AddEditToDoParameter
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
    
    fun todoList() : Flow<List<ToDoEntity>>
    suspend fun addOrEditToDo(parameter: AddEditToDoParameter) : Result<Boolean>
    suspend fun deleteToDo(id: Long) : Result<Boolean>
}