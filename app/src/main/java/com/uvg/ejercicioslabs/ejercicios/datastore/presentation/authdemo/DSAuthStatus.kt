package com.uvg.ejercicioslabs.ejercicios.datastore.presentation.authdemo

sealed interface DSAuthStatus {
    data object Loading: DSAuthStatus
    data object Authenticated: DSAuthStatus
    data object NonAuthenticated: DSAuthStatus
}