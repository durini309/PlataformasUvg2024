package com.uvg.ejercicioslabs.ejercicios.firebase.data.remote.dto

import com.uvg.ejercicioslabs.ejercicios.firebase.domain.model.Task
import com.uvg.ejercicioslabs.ejercicios.firebase.domain.model.TaskStatus

data class TaskDto(
    val title: String = "",
    val status: String = "PENDING"
)

fun TaskDto.mapToModel(id: String): Task = Task(
    id = id,
    title = title,
    status = TaskStatus.valueOf(status)
)
