package io.github.nuarz71.todolist.android.data.repository.parameter

import java.time.LocalDateTime

data class AddEditToDoParameter(
    val id: Long? = null,
    val title: String,
    val description: String? = null,
    val dueDate: LocalDateTime
)
