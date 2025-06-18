package com.example.shutterflow.presentation.explore

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class TutorialViewModel(application: Application) : AndroidViewModel(application) {
    private val _tutorials = MutableStateFlow<List<Tutorial>>(emptyList())
    val tutorials: StateFlow<List<Tutorial>> = _tutorials

    init {
        loadTutorials()
    }

    private fun loadTutorials() {
        _tutorials.value = loadTutorialsFromAssets(getApplication())
    }
}

fun loadTutorialsFromAssets(context: Context): List<Tutorial> {
    val jsonString = context.assets.open("tutorials.json")
        .bufferedReader()
        .use { it.readText() }

    val listType = object : TypeToken<List<Tutorial>>() {}.type
    return Gson().fromJson(jsonString, listType)
}
