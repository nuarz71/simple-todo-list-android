package io.github.nuarz71.todolist.android.data.repository

import io.github.nuarz71.todolist.android.data.repository.parameter.AddEditToDoParameter

internal class DefaultToDoRepository : ToDoRepository {
    override suspend fun addOrEditToDo(parameter: AddEditToDoParameter): Result<Boolean> {
        //TODO: add implementation
        return Result.success(true)
    }
}