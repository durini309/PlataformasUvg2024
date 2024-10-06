package com.uvg.ejercicioslabs.ejercicios.datastore.presentation

sealed interface DataStoreScreenEvent {
    data class EmailChange(val email: String): DataStoreScreenEvent
    data class NameChange(val name: String): DataStoreScreenEvent
    data class KeyChange(val key: String): DataStoreScreenEvent
    data object SaveEmail: DataStoreScreenEvent
    data object SaveName: DataStoreScreenEvent
    data object GetValueClick: DataStoreScreenEvent
}