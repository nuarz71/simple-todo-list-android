package io.github.nuarz71.todolist.android.domain.usecase

import io.github.nuarz71.todolist.android.data.repository.ToDoRepository
import io.github.nuarz71.todolist.android.data.repository.parameter.AddEditTaskParameter
import java.time.LocalDateTime
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val repository: ToDoRepository
) {
    suspend operator fun invoke(
        title: String,
        description: String?,
        dueDate: LocalDateTime
    ) = repository.addOrEditTask(
        AddEditTaskParameter(
            title = title,
            description = description,
            dueDate = dueDate
        )
    )
}