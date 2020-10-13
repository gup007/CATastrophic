package com.zalora.catastrophic.home.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CatDao {

    @Query("SELECT * FROM cat")
    fun findAll(): PagingSource<Int, Cat>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(doggoModel: List<Cat>)

    @Query("DELETE FROM cat")
    suspend fun clearAllCat()

}