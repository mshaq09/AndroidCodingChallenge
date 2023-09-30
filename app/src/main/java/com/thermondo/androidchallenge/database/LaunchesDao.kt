package com.thermondo.androidchallenge.database

import androidx.room.*
import com.thermondo.androidchallenge.model.Launch

@Dao
interface LaunchesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBookMark(launch: Launch)

    @Query("SELECT * FROM launches WHERE id = :Id")
    fun findLaunchById(Id: String): Launch

    @Query("SELECT * FROM launches")
    fun getAllBookMarked(): List<Launch>

    @Update
    suspend fun updateBookMarkedDetails(launch: Launch)

    @Delete
    suspend fun deleteBookMark(launch: Launch)
}