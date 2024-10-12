package com.uvg.ejercicioslabs.ejercicios.room.di

import android.content.Context
import androidx.room.Room
import com.uvg.ejercicioslabs.ejercicios.room.data.localdb.AppDatabase

object Dependencies {
    private var database: AppDatabase? = null

    private fun buildDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "uvg.db"
        ).build()
    }

    fun provideDatabase(context: Context): AppDatabase {
        return database ?: synchronized(this) {
            database ?: buildDatabase(context).also { database = it }
        }
    }
}