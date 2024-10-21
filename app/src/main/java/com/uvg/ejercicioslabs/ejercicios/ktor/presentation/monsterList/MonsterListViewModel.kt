package com.uvg.ejercicioslabs.ejercicios.ktor.presentation.monsterList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.ejercicioslabs.ejercicios.ktor.data.network.KtorZeldaApi
import com.uvg.ejercicioslabs.ejercicios.ktor.di.KtorDependencies
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.network.ZeldaApi
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.network.util.onError
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.network.util.onSuccess
import kotlinx.coroutines.launch

class MonsterListViewModel(
    private val zeldaApi: ZeldaApi
): ViewModel() {

    fun getMonsters() {
        viewModelScope.launch {
            zeldaApi
                .getAllMonsters()
                .onSuccess { monsters ->
                    println(monsters)
                }
                .onError { error ->
                    println(error)
                }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val api = KtorZeldaApi(KtorDependencies.provideHttpClient())
                MonsterListViewModel(api)
            }
        }
    }

}