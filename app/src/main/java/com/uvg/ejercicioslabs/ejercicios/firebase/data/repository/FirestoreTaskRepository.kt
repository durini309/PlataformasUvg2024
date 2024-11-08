package com.uvg.ejercicioslabs.ejercicios.firebase.data.repository

import com.google.firebase.Firebase
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.uvg.ejercicioslabs.ejercicios.firebase.data.remote.dto.TaskDto
import com.uvg.ejercicioslabs.ejercicios.firebase.data.remote.dto.mapToModel
import com.uvg.ejercicioslabs.ejercicios.firebase.domain.model.Task
import com.uvg.ejercicioslabs.ejercicios.firebase.domain.model.TaskFilter
import com.uvg.ejercicioslabs.ejercicios.firebase.domain.model.TaskStatus
import com.uvg.ejercicioslabs.ejercicios.firebase.domain.repository.TaskRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FirestoreTaskRepository(
    firestore: FirebaseFirestore = Firebase.firestore,
    private val crashlytics: FirebaseCrashlytics = FirebaseCrashlytics.getInstance()
): TaskRepository {
    private val tasksCollection = firestore.collection("tasks")

    /**
     * Podemos "suscribirnos" a una collection (filtrada o no filtrada) para que, en tiempo real
     * obtengamos sus cambios. Dado a que la librería lo retorna como un SnapshotListener, nosotros
     * lo convertimos a Flow para que sea una estructura concurrente de la cual ya sabemos cómo
     * manejar.
     */
    override fun getTasks(filter: TaskFilter): Flow<List<Task>> = callbackFlow {
        var query: Query = tasksCollection

        // Aplicamos el filtro si no es ALL
        if (filter != TaskFilter.ALL) {
            query = query.whereEqualTo("status", filter.name)
        }

        val subscription = query.addSnapshotListener { snapshot, error ->
            if (error != null) {
                // En caso de error, cerramos el flow
                crashlytics.recordException(error)
                cancel("Error fetching tasks: ${error.message}", error)
                return@addSnapshotListener
            }

            val tasks = snapshot?.documents?.map { doc ->
                val taskDto = doc.toObject(TaskDto::class.java) ?: TaskDto()
                taskDto.mapToModel(doc.id)
            } ?: emptyList()

            trySend(tasks)
        }

        // Cleanup cuando el flow se cancela
        awaitClose {
            println("Cancelando flow")
            subscription.remove()
        }
    }

    override suspend fun addTask(title: String): Result<Unit> = try {
        val task = TaskDto(
            title = title,
            status = TaskStatus.PENDING.name
        )
        tasksCollection.add(task).await()
        Result.success(Unit)
    } catch (e: Exception) {
        crashlytics.recordException(e)
        Result.failure(e)
    }

    override suspend fun updateTaskStatus(taskId: String, newStatus: TaskStatus): Result<Unit> = try {
        println("Updating: $taskId to ${newStatus.name}")
        tasksCollection.document(taskId)
            .update("status", newStatus.name)
            .await()
        Result.success(Unit)
    } catch (e: Exception) {
        println(e)
        crashlytics.recordException(e)
        Result.failure(e)
    }

    override suspend fun deleteTask(taskId: String): Result<Unit> = try {
        tasksCollection.document(taskId)
            .delete()
            .await()
        Result.success(Unit)
    } catch (e: Exception) {
        crashlytics.recordException(e)
        Result.failure(e)
    }
}