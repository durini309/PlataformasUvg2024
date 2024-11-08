package com.uvg.ejercicioslabs.ejercicios.firebase.presentation.home

import com.uvg.ejercicioslabs.ejercicios.firebase.domain.model.TaskFilter
import com.uvg.ejercicioslabs.ejercicios.firebase.domain.model.TaskStatus

sealed interface HomeScreenEvent {
    data class TitleChanged(val title: String) : HomeScreenEvent
    data object AddTaskClicked : HomeScreenEvent
    data class TaskStatusChanged(val taskId: String, val newStatus: TaskStatus) : HomeScreenEvent
    data class DeleteTaskClicked(val taskId: String) : HomeScreenEvent
    data class FilterChanged(val filter: TaskFilter) : HomeScreenEvent
    data object ErrorShown: HomeScreenEvent
}