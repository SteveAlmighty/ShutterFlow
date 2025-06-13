package com.example.shutterflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.shutterflow.data.ThemePreferenceManager
import com.example.shutterflow.presentation.gallery.PhotoGalleryViewModel
import com.example.shutterflow.presentation.settings.SettingsViewModel
import com.example.shutterflow.ui.theme.ShutterflowTheme
import com.example.shutterflow.utils.NavHostScreen
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
                    ShutterflowTheme(darkTheme = isDark) {
                        NavHostScreen(viewModel, settingsViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShutterflowTheme {
        Greeting("Android")
    }
}