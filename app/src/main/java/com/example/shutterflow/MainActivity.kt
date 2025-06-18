package com.example.shutterflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import com.example.shutterflow.data.ThemePreferenceManager
import com.example.shutterflow.presentation.SplashScreen
import com.example.shutterflow.presentation.gallery.PhotoGalleryViewModel
import com.example.shutterflow.presentation.settings.SettingsViewModel
import com.example.shutterflow.ui.theme.ShutterflowTheme
import com.example.shutterflow.utils.NavHostScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = PhotoGalleryViewModel(applicationContext)
        val settingsViewModel by viewModels<SettingsViewModel>()


        enableEdgeToEdge()
        lifecycleScope.launch {
            ThemePreferenceManager.getDarkModeFlow(this@MainActivity).collect { isDark ->

                setContent {
                    var showSplash by remember { mutableStateOf(true) }

                    LaunchedEffect(Unit) {
                        delay(2000) // simulate loading or delay for 2 seconds
                        showSplash = false
                    }
                    if (showSplash) {
                        SplashScreen()
                    } else {
                        ShutterflowTheme(darkTheme = isDark) {
                            NavHostScreen(viewModel, settingsViewModel)
                        }
                    }
                }
            }
        }
    }
}

