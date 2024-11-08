package com.uvg.ejercicioslabs.ejercicios.firebase.domain.model

import com.uvg.ejercicioslabs.ejercicios.firebase.data.remote.dto.TaskDto

data class Task(
    val id: String,
    val title: String,
    val status: TaskStatus = TaskStatus.PENDING
)

fun Task.mapToDto(): TaskDto = TaskDto(
    title = title,
    status = status.name
)
