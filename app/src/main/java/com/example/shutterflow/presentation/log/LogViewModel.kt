package com.example.shutterflow.presentation.log

import android.app.Application
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.shutterflow.data.reminderdb.AppDatabase
import com.example.shutterflow.data.reminderdb.ScheduledShoot
import kotlinx.coroutines.launch
import java.util.Calendar



class ShootViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "shoot_db"
    ).build()
    val dao = db.shootDao()

//    val shoots = dao.getAllShoots().collectAsState(initial = emptyList())

    fun addShoot(shoot: ScheduledShoot) = viewModelScope.launch {
        dao.insert(shoot)
    }

    fun deleteShoot(shoot: ScheduledShoot) = viewModelScope.launch {
        dao.delete(shoot)
    }

    fun updateShoot(shoot: ScheduledShoot) = viewModelScope.launch {
        dao.update(shoot)
    }
}