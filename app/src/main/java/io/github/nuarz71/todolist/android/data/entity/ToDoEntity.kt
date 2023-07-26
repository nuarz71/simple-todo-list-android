package io.github.nuarz71.todolist.android.data.entity

import java.time.LocalDateTime

data class ToDoEntity(
    val id: Long = 0,
    val title: String,
    val description: String?,
    val dueDate: LocalDateTime
)
