package com.uvg.ejercicioslabs.ejercicios.architecture

sealed interface AuthScreenEvent {
    data class EmailChange(val email: String): AuthScreenEvent
    data class PasswordChange(val password: String): AuthScreenEvent
    data object LoginClick: AuthScreenEvent
}