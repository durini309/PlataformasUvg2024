package com.uvg.ejercicioslabs.ejercicios.architecture

data class AuthScreenState(
    val email: String = "",
    val password: String = "",
    val hasError: Boolean = false,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
)
