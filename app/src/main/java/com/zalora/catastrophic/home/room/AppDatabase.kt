package com.zalora.catastrophic.home.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Cat::class, RemoteKeys::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getRepoDao(): RemoteKeysDao
    abstract fun getCatDao(): CatDao
}