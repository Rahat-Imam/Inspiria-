package com.motivation.inspiria.core.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Preferences(private val context: Context) {

    private val dataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create {
        context.preferencesDataStoreFile("settings")
    }

    private val isDarkTheme = booleanPreferencesKey("IS_DARK_THEME")

    suspend fun setIsDarkTheme(isOnBoarding: Boolean) {
        dataStore.edit { setting ->
            setting[isDarkTheme] = isOnBoarding
        }
    }

    fun getIsDarkTheme(): Flow<Boolean> = dataStore.data.map { settings ->
        settings[isDarkTheme] ?: false
    }


    private val isNotificationEnabled = booleanPreferencesKey("IS_NOTIFICATION_ENABLED")

    suspend fun setNotificationEnabledValue(isOnBoarding: Boolean) {
        dataStore.edit { setting ->
            setting[isNotificationEnabled] = isOnBoarding
        }
    }

    fun getIsNotificationEnabled(): Flow<Boolean> = dataStore.data.map { settings ->
        settings[isNotificationEnabled] ?: true
    }

}
