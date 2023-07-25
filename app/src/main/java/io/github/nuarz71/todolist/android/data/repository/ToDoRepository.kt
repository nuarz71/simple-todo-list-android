package io.github.nuarz71.todolist.android.data.repository

import io.github.nuarz71.todolist.android.data.repository.parameter.AddEditToDoParameter

interface ToDoRepository {
    suspend fun addToDo(parameter: AddEditToDoParameter) : Result<Boolean>
}