package com.example.shutterflow.presentation.log

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.shutterflow.data.reminderdb.AppDatabase
import com.example.shutterflow.data.reminderdb.ScheduledShoot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class ShootViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "shoot_db"
    ).build()
    val dao = db.shootDao()

    val shoots: Flow<List<ScheduledShoot>> = dao.getAllShoots()

    fun addShoot(shoot: ScheduledShoot) = viewModelScope.launch(Dispatchers.IO) {
        dao.insert(shoot)
    }

    fun deleteShoot(shoot: ScheduledShoot) = viewModelScope.launch(Dispatchers.IO) {
        dao.delete(shoot)
    }

    fun updateShoot(shoot: ScheduledShoot) = viewModelScope.launch(Dispatchers.IO) {
        dao.update(shoot)
    }
}