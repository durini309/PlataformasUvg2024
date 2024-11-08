package com.uvg.ejercicioslabs.ejercicios.firebase.domain.repository

import com.uvg.ejercicioslabs.ejercicios.firebase.domain.model.Task
import com.uvg.ejercicioslabs.ejercicios.firebase.domain.model.TaskFilter
import com.uvg.ejercicioslabs.ejercicios.firebase.domain.model.TaskStatus
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTasks(filter: TaskFilter = TaskFilter.ALL): Flow<List<Task>>
    suspend fun addTask(title: String): Result<Unit>
    suspend fun updateTaskStatus(taskId: String, newStatus: TaskStatus): Result<Unit>
    suspend fun deleteTask(taskId: String): Result<Unit>
}