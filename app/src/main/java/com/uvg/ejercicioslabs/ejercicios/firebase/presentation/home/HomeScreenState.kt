package com.uvg.ejercicioslabs.ejercicios.firebase.presentation.home

import com.uvg.ejercicioslabs.ejercicios.firebase.domain.model.Task
import com.uvg.ejercicioslabs.ejercicios.firebase.domain.model.TaskFilter
import com.uvg.ejercicioslabs.ejercicios.firebase.domain.model.TaskStatus

data class HomeScreenState(
    val newTaskTitle: String = "",
    val selectedFilter: TaskFilter = TaskFilter.ALL,
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)