package com.uvg.ejercicioslabs.ejercicios.room.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.ejercicioslabs.ejercicios.room.data.localdb.dao.UserDao
import com.uvg.ejercicioslabs.ejercicios.room.data.localdb.entity.mapToEntity
import com.uvg.ejercicioslabs.ejercicios.room.data.localdb.entity.mapToModel
import com.uvg.ejercicioslabs.ejercicios.room.di.RoomDependencies
import com.uvg.ejercicioslabs.ejercicios.room.domain.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RoomViewModel(
    private val userDao: UserDao
) : ViewModel() {
    val users: StateFlow<List<User>> = userDao.getAllUsers()
        .map { entities -> entities.map { it.mapToModel() } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    fun updateUserName(name: String) {
        _userName.value = name
    }

    fun addUser() {
        viewModelScope.launch {
            val newUser = User(
                id = 0,  // Room auto-genera esto
                name = _userName.value,
                age = (17..30).random(),
                country = listOf("Guatemala", "Mexico", "El Salvador", "Honduras").random()
            )
            userDao.createUser(newUser.mapToEntity())
            _userName.value = ""
        }
    }

    fun deleteUser(id: Int) {
        viewModelScope.launch {
            userDao.deleteUser(id)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val db = RoomDependencies.provideDatabase(application)
                RoomViewModel(
                    userDao = db.userDao()
                )
            }
        }
    }
}