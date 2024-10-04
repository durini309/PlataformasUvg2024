package com.uvg.ejercicioslabs.ejercicios.architecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.log

class AuthViewModel(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _state = MutableStateFlow(AuthScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: AuthScreenEvent) {
        when (event) {
            AuthScreenEvent.LoginClick -> onLogIn()
            is AuthScreenEvent.EmailChange -> onEmailChange(event.email)
            is AuthScreenEvent.PasswordChange -> onPasswordChange(event.password)
        }
    }

    private fun onEmailChange(email: String) {
        _state.update { it.copy(
            email = email,
            hasError = false,
            isSuccess = false
        )}
    }

    private fun onPasswordChange(password: String) {
        _state.update { it.copy(
            password = password,
            hasError = false,
            isSuccess = false
        )}
    }

    private fun onLogIn() {
        viewModelScope.launch {
            val screenState = _state.value
            if (screenState.password.isEmpty()) {
                _state.update { it.copy(hasError = true) }
                return@launch
            }

            _state.update { it.copy(
                isLoading = true,
                isSuccess = false
            ) }

            val loggedStatus = authRepository.login(screenState.email, screenState.password)
            if (loggedStatus) {
                _state.update { it.copy(
                    isLoading = false,
                    hasError = false,
                    isSuccess = true,
                    email = "",
                    password = ""
                ) }
            } else {
                _state.update { it.copy(
                    isLoading = false,
                    hasError = true,
                ) }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AuthViewModel(
                    authRepository = LocalAuthRepository()
                )
            }
        }
    }
}