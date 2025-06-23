package com.example.shutterflow.presentation.home

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shutterflow.data.UserPreferencesManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserSettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val _profileImageName = MutableStateFlow<String?>(null)
    val profileImageName = _profileImageName.asStateFlow()

    private val _userName = MutableStateFlow<String?>(null)
    val userName = _userName.asStateFlow()

    @SuppressLint("StaticFieldLeak")
    private val context = application.applicationContext

    init {
        viewModelScope.launch {
            UserPreferencesManager.getProfilePictureUriFlow(context).collect {
                _profileImageName.value = it
            }
        }

        viewModelScope.launch {
            UserPreferencesManager.getUserNameFlow(context).collect {
                _userName.value = it
            }
        }
    }

//    fun updateProfileImage(imageName: String) {
//        viewModelScope.launch {
//            UserPreferencesManager.setProfilePictureUri(context, imageName)
//        }
//    }
//
//    fun updateUserName(name: String){
//        viewModelScope.launch {
//            UserPreferencesManager.setUserName(context, name)
//        }
//    }
}
