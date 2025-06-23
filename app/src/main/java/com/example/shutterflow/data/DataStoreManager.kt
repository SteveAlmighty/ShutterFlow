package com.example.shutterflow.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object UserPreferencesManager {
    private val Context.dataStore by preferencesDataStore(name = "user_prefs")

    private val PROFILE_PICTURE_URI = stringPreferencesKey("profile_picture_uri")
    private val USER_NAME = stringPreferencesKey("user_name")

    suspend fun setProfilePictureUri(context: Context, uri: String) {
        context.dataStore.edit { it[PROFILE_PICTURE_URI] = uri }
    }

    suspend fun setUserName(context: Context, name: String) {
        context.dataStore.edit { it[USER_NAME] = name }
    }

    fun getProfilePictureUriFlow(context: Context): Flow<String?> =
        context.dataStore.data.map { it[PROFILE_PICTURE_URI] }

    fun getUserNameFlow(context: Context): Flow<String?> =
        context.dataStore.data.map { it[USER_NAME] }
}


object ThemePreferenceManager {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val THEME_KEY = booleanPreferencesKey("dark_mode")

    suspend fun setDarkMode(context: Context, enabled: Boolean) {
        context.dataStore.edit { it[THEME_KEY] = enabled }
    }

    fun getDarkModeFlow(context: Context): Flow<Boolean> {
        return context.dataStore.data
            .map { it[THEME_KEY] ?: false }
    }
}

