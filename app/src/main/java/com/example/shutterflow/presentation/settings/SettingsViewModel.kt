package com.example.shutterflow.presentation.settings

import android.annotation.SuppressLint
import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shutterflow.data.ThemePreferenceManager
import com.example.shutterflow.data.UserPreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@SuppressLint("StaticFieldLeak")
class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext


    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName

    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode: StateFlow<Boolean> = _isDarkMode

    init {
        viewModelScope.launch(Dispatchers.IO) {
            ThemePreferenceManager.getDarkModeFlow(application).collect {
                _isDarkMode.value = it
            }
        }


        viewModelScope.launch(Dispatchers.IO) {
            UserPreferencesManager.getUserNameFlow(context).collect {
                _userName.value = it ?: ""
            }
        }
    }

    fun toggleDarkMode(enabled: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            ThemePreferenceManager.setDarkMode(getApplication(), enabled)
        }
    }


    fun setUserName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            UserPreferencesManager.setUserName(context, name)
        }
    }
}