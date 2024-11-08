package com.uvg.ejercicioslabs.ejercicios.firebase.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.ejercicioslabs.ejercicios.firebase.data.repository.FirestoreTaskRepository
import com.uvg.ejercicioslabs.ejercicios.firebase.domain.model.TaskStatus
import com.uvg.ejercicioslabs.ejercicios.firebase.domain.repository.TaskRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModel(
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState
                .map { it.selectedFilter } // Obtenemos solo el filtro seleccionado
                .distinctUntilChanged() // Le decimos al flow que solo siga a la siguiente linea si cambia el filtro
                /**
                 * Usamos flatMapLatest porque:
                 * 1. Cada vez que cambia el filtro, se crea un nuevo listener de Firestore
                 * 2. flatMapLatest cancela automáticamente el listener anterior
                 * 3. Garantiza que solo tenemos un listener activo a la vez
                 * 4. Evita memory leaks y subscripciones múltiples
                 */
                .flatMapLatest { filter ->
                    taskRepository.getTasks(filter)
                }
                .catch { throwable ->
                    _uiState.update { it.copy(error = throwable.message) }
                }
                .collect { tasks ->
                    _uiState.update { it.copy(
                        tasks = tasks,
                        error = null
                    )}
                }
        }
    }

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.TitleChanged -> {
                _uiState.update { it.copy(newTaskTitle = event.title) }
            }

            is HomeScreenEvent.AddTaskClicked -> {
                viewModelScope.launch {
                    _uiState.update { it.copy(isLoading = true) }

                    taskRepository.addTask(_uiState.value.newTaskTitle)
                        .onSuccess {
                            _uiState.update { it.copy(
                                newTaskTitle = "",
                                isLoading = false,
                                error = null
                            )}
                        }
                        .onFailure { throwable ->
                            _uiState.update { it.copy(
                                isLoading = false,
                                error = throwable.message
                            )}
                        }
                }
            }

            is HomeScreenEvent.TaskStatusChanged -> {
                viewModelScope.launch {
                    taskRepository.updateTaskStatus(event.taskId, event.newStatus)
                        .onFailure { throwable ->
                            _uiState.update { it.copy(
                                error = throwable.message
                            )}
                        }
                }
            }

            is HomeScreenEvent.DeleteTaskClicked -> {
                viewModelScope.launch {
                    taskRepository.deleteTask(event.taskId)
                        .onFailure { throwable ->
                            _uiState.update { it.copy(
                                error = throwable.message
                            )}
                        }
                }
            }

            is HomeScreenEvent.FilterChanged -> {
                _uiState.update { it.copy(selectedFilter = event.filter)}
            }

            HomeScreenEvent.ErrorShown -> {
                _uiState.update { it.copy(error = null)}
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val taskRepository = FirestoreTaskRepository()
                HomeViewModel(taskRepository)
            }
        }
    }
}