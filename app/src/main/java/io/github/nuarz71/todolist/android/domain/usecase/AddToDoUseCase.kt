package io.github.nuarz71.todolist.android.domain.usecase

import io.github.nuarz71.todolist.android.data.repository.ToDoRepository
import io.github.nuarz71.todolist.android.data.repository.parameter.AddEditToDoParameter
import java.time.LocalDateTime
import javax.inject.Inject

class AddToDoUseCase @Inject constructor(
    private val repository: ToDoRepository
) {
    suspend operator fun invoke(
        title: String,
        description: String?,
        dueDate: LocalDateTime
    ): Result<Boolean> {
        return repository.addOrEditToDo(
            AddEditToDoParameter(
                title = title,
                description = description,
                dueDate = dueDate
            )
        )
    }
}