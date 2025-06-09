package com.example.shutterflow.data.reminderdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shoots")
data class ScheduledShoot(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val timeMillis: Long,
    val repeatWeekly: Boolean,
    val tag: String = ""
)
