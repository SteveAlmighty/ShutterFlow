package com.example.shutterflow.presentation.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shutterflow.data.UserPreferencesManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val _selectedImageName = MutableStateFlow<String?>(null)
    val selectedImageName: StateFlow<String?> = _selectedImageName.asStateFlow()

    init {
        viewModelScope.launch {
            UserPreferencesManager.getProfilePictureUriFlow(application.applicationContext).collect {
                _selectedImageName.value = it
            }
        }
    }

    fun selectImage(imageName: String) {
        viewModelScope.launch {
            UserPreferencesManager.setProfilePictureUri(getApplication(), imageName)
        }
    }
}