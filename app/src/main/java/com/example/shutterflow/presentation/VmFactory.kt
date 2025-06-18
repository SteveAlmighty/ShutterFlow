package com.example.shutterflow.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shutterflow.presentation.explore.TutorialViewModel


class TutorialViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TutorialViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TutorialViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
