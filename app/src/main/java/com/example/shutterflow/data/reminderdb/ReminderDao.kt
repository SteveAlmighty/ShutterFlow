package com.example.shutterflow.data.reminderdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ShootDao {
    @Query("SELECT * FROM shoots ORDER BY timeMillis ASC")
    fun getAllShoots(): Flow<List<ScheduledShoot>>

    @Insert
    suspend fun insert(shoot: ScheduledShoot)

    @Delete
    suspend fun delete(shoot: ScheduledShoot)

    @Update
    suspend fun update(shoot: ScheduledShoot)
}
