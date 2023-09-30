package com.thermondo.androidchallenge.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.thermondo.androidchallenge.model.Launch

@Database(entities = [(Launch::class)], version = 1, exportSchema = false)
@TypeConverters(SpaceXTypeConverters::class)
abstract class LaunchRoomDatabase: RoomDatabase() {
    abstract fun launchesDao(): LaunchesDao
}