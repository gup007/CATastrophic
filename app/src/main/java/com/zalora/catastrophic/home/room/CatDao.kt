package com.zalora.catastrophic.home.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zalora.catastrophic.home.Cat

@Dao
interface CatDao {

    @Query("SELECT * FROM Cat")
    fun findAll(): LiveData<List<Cat>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(newsList: List<Cat>): LongArray?

    @Insert
    fun insert(cat: Cat)

    @Update
    fun update(cat: Cat)

    @Delete
    fun delete(cat: Cat)

}