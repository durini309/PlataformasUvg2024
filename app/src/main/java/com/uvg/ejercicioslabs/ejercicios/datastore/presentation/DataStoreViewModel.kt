package com.uvg.ejercicioslabs.ejercicios.datastore.presentation

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.ejercicioslabs.dataStore
import com.uvg.ejercicioslabs.ejercicios.datastore.data.DataStoreUserPrefs
import com.uvg.ejercicioslabs.ejercicios.datastore.domain.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DataStoreViewModel(
    private val userPreferences: UserPreferences
): ViewModel() {

    private val _state = MutableStateFlow(DataStoreScreenState())
    val state = _state.asStateFlow()

    /**
     * Exponemos el flow que tiene el nombre y correo juntos. Si quisieramos agregar esto a nuestro
     * [_state], tendriamos que utilizar el operador Merge, el cual une flows pero esto lo haría
     * más complicado.
     *
     * La razón por la que usamos stateIn es porque estamos convirtiendo el flow en un StateFlow,
     * le decimos que usará el mismo scope (ciclo de vida) que el viewModel, luego le decimos con
     * WhileSubscribed que, mientras alguien esté suscrito al StateFLow, este se mantendrá activo.
     * El 5000 quiere decir que, después de que el último suscriptor se vaya, esperará 5 segundos
     * para dejar de emitir. Esto nos ayuda a que, si sufrimos un cambio de configuracion como rotar
     * o cambiar el tema, tenemos 5 segundos "de gracia" para que el StateFlow no se desconecte (
     * cosa que no pasará porque estos cambios de configuración tardan menos de 1 seg).
     */
    val nameAndEmailState = userPreferences
        .getNameAndEmail()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null
        )

    fun onEvent(event: DataStoreScreenEvent) {
        when (event) {
            is DataStoreScreenEvent.EmailChange -> {
                _state.update { it.copy(
                    email = event.email
                )}
            }
            DataStoreScreenEvent.GetValueClick -> { getValueFromKey() }
            is DataStoreScreenEvent.KeyChange -> {
                _state.update { it.copy(
                    key = event.key
                )}
            }
            is DataStoreScreenEvent.NameChange -> {
                _state.update { it.copy(
                    name = event.name
                )}
            }
            DataStoreScreenEvent.SaveEmail -> saveEmail()
            DataStoreScreenEvent.SaveName -> saveName()
        }
    }

    private fun getValueFromKey() {
        viewModelScope.launch {
            _state.update { it.copy(
                value = userPreferences.getValue(_state.value.key)
            )}
        }
    }

    private fun saveEmail() {
        viewModelScope.launch {
            userPreferences.setEmail(_state.value.email)
        }
    }

    private fun saveName() {
        viewModelScope.launch {
            userPreferences.setName(_state.value.name)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // DataStore necesita un Context para ser accedido, por eso usamos application
                val application = checkNotNull(this[APPLICATION_KEY])
                DataStoreViewModel(
                    userPreferences = DataStoreUserPrefs(application.dataStore)
                )
            }
        }
    }

}