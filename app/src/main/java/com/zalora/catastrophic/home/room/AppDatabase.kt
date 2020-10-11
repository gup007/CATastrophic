package com.zalora.catastrophic.home.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zalora.catastrophic.home.Cat


@Database(entities = [Cat::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): CatDao
}