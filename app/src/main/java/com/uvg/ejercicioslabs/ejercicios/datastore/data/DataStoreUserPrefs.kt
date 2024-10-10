package com.uvg.ejercicioslabs.ejercicios.datastore.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.uvg.ejercicioslabs.ejercicios.datastore.domain.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlin.math.log

class DataStoreUserPrefs(
    private val dataStore: DataStore<Preferences>
): UserPreferences {
    private val nameKey = stringPreferencesKey("name")
    private val emailKey = stringPreferencesKey("email")
    private val loggedKey = booleanPreferencesKey("logged")

    override suspend fun logIn() {
        dataStore.edit { preferences ->
            preferences[loggedKey] = true
        }
    }

    override suspend fun logOut() {
        dataStore.edit { preferences ->
            preferences[loggedKey] = false
        }
    }

    override fun authStatus(): Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[loggedKey] ?: false
    }

    override suspend fun setName(name: String) {
        dataStore.edit { preferences ->
            preferences[nameKey] = name
        }
    }

    override suspend fun setEmail(email: String) {
        dataStore.edit { preferences ->
            preferences[emailKey] = email
        }
    }

    override suspend fun getValue(key: String): String? {
        val preferencesKey = when (key) {
            "name" -> nameKey
            "email" -> emailKey
            else -> null
        }

        preferencesKey?.let {
            val preferences = dataStore.data.first()
            return preferences[preferencesKey]
        }

        return null
    }

    /**
     * En esta función, como dataStore.data me retorna un Flow<Preferences>, usamos la operacion
     * "map" de un flow, la cual convierte de un Flow a un Flow de cualquier tipo. En nuestro caso,
     * estamos convirtiendo un Flow<Preferences> a un Flow<String>
     */
    override fun getNameAndEmail(): Flow<String> {
        return dataStore.data.map { preferences ->
            "${preferences[emailKey] ?: "No Email"}, ${preferences[nameKey] ?: "No name"}"
        }
    }
}