package com.uvg.ejercicioslabs.ejercicios.ktor.presentation.monsterList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.ejercicioslabs.ejercicios.ktor.data.network.KtorZeldaApi
import com.uvg.ejercicioslabs.ejercicios.ktor.data.network.dto.mapToMonsterModel
import com.uvg.ejercicioslabs.ejercicios.ktor.di.KtorDependencies
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.network.ZeldaApi
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.network.util.map
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.network.util.onError
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.network.util.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MonsterListViewModel(
    private val zeldaApi: ZeldaApi
): ViewModel() {

    private val _state = MutableStateFlow(MonsterListState())
    val state = _state.asStateFlow()

    init {
        getMonsters()
    }

    fun getMonsters() {
        viewModelScope.launch {
            zeldaApi
                .getAllMonsters()
                .map { response -> response.data.map { it.mapToMonsterModel() } }
                .onSuccess { monsters ->
                    _state.update { it.copy(
                        isLoading = false,
                        data = monsters
                    ) }
                }
                .onError { error ->
                    _state.update { it.copy(
                        isLoading = false,
                        isError = true,
                    ) }

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