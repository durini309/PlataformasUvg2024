package com.uvg.ejercicioslabs.ejercicios.datastore.presentation.authdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.ejercicioslabs.dataStore
import com.uvg.ejercicioslabs.ejercicios.datastore.data.DataStoreUserPrefs
import com.uvg.ejercicioslabs.ejercicios.datastore.domain.UserPreferences
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DSAuthViewModel(
    private val preferences: UserPreferences
) : ViewModel() {

    val authStatus = preferences.authStatus()
        .onStart {
            delay(2000)
        }
        .map { isLogged ->
            if (isLogged) {
                DSAuthStatus.Authenticated
            } else {
                DSAuthStatus.NonAuthenticated
            }
        }
        .catch { error ->
            println(error)
            DSAuthStatus.NonAuthenticated
        }
        .onEach { item -> println(item) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            DSAuthStatus.Loading
        )

    fun login() {
        viewModelScope.launch {
            preferences.logIn()
        }
    }

    fun logout() {
        viewModelScope.launch {
            preferences.logOut()
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                DSAuthViewModel(
                    preferences = DataStoreUserPrefs(application.dataStore)
                )
            }
        }
    }
}