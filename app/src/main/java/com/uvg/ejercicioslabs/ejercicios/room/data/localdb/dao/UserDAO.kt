package com.uvg.ejercicioslabs.ejercicios.room.data.localdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.uvg.ejercicioslabs.ejercicios.room.data.localdb.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM UserEntity WHERE (:id IS NULL OR id = :id)")
    fun getAllUsers(id: Int? = null): Flow<List<UserEntity>>

    @Query("DELETE FROM UserEntity WHERE id = :id")
    suspend fun deleteUser(id: Int)

    @Insert
    suspend fun createUser(user: UserEntity)
}