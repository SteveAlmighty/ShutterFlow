package com.example.shutterflow.data.reminderdb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ScheduledShoot::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun shootDao(): ShootDao
}