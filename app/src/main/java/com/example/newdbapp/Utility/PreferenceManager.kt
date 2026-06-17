package com.example.newdbapp.Utility

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    // 1. Define all keys in a private object to keep them organized
    private object PreferencesKeys {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val USER_NAME = stringPreferencesKey("user_name")
        val PASSWORD = stringPreferencesKey("password")
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val THEME_MODE = intPreferencesKey("theme_mode") // 0: System, 1: Light, 2: Dark
    }

    // 2. READ LOGIC (Exposing data as Flows)

    val accessToken: Flow<String?> = dataStore.data
        .handleErrors()
        .map { preferences -> preferences[PreferencesKeys.ACCESS_TOKEN] }

    val userName: Flow<String> = dataStore.data
        .handleErrors()
        .map { preferences -> preferences[PreferencesKeys.USER_NAME] ?: "Guest" }

    val password: Flow<String> = dataStore.data
        .handleErrors()
        .map { preferences -> preferences[PreferencesKeys.PASSWORD] ?: "" }

    val isLoggedIn: Flow<Boolean> = dataStore.data
        .handleErrors()
        .map { preferences -> preferences[PreferencesKeys.IS_LOGGED_IN] ?: false }

    val themeMode: Flow<Int> = dataStore.data
        .handleErrors()
        .map { preferences -> preferences[PreferencesKeys.THEME_MODE] ?: 0 }

    // 3. WRITE LOGIC (Suspend functions for saving)

    suspend fun saveUserData(token: String, name: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.ACCESS_TOKEN] = token
            preferences[PreferencesKeys.USER_NAME] = name
            preferences[PreferencesKeys.IS_LOGGED_IN] = true
        }
    }
    suspend fun saveUserNamePassword(userName: String, password: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.USER_NAME] = userName
            preferences[PreferencesKeys.PASSWORD] = password
        }
    }

    suspend fun updateTheme(mode: Int) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.THEME_MODE] = mode
        }
    }

    // 4. CLEAR LOGIC (Logout)
    suspend fun clearAllData() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    // Extension function to handle IOExceptions gracefully
    private fun Flow<Preferences>.handleErrors(): Flow<Preferences> {
        return this.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
    }
}