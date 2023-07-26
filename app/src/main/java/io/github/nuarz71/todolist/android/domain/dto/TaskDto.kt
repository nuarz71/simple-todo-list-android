package io.github.nuarz71.todolist.android.domain.dto

import java.time.LocalDateTime

data class TaskDto(
    val id: Long,
    val title: String,
    val description: String?,
    val dueDate: LocalDateTime
)
