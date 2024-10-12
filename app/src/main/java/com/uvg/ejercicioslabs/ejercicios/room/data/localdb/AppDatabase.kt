package com.uvg.ejercicioslabs.ejercicios.room.data.localdb

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.uvg.ejercicioslabs.ejercicios.room.data.localdb.dao.UserDao
import com.uvg.ejercicioslabs.ejercicios.room.data.localdb.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
