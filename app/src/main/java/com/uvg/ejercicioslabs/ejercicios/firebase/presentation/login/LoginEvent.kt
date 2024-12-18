package com.uvg.ejercicioslabs.ejercicios.firebase.presentation.login

sealed interface LoginScreenEvent {
    data class EmailChanged(val email: String) : LoginScreenEvent
    data class PasswordChanged(val password: String) : LoginScreenEvent
    data object LoginClicked : LoginScreenEvent
    data object LogoutClicked : LoginScreenEvent
    data object CreateAccountClicked : LoginScreenEvent
    data object CheckAuthStatusClicked : LoginScreenEvent
}

