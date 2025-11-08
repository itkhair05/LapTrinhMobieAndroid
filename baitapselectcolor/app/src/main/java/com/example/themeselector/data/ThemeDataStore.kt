package com.example.themeselector.data
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "theme_prefs")

class ThemeDataStore(private val context: Context) {

    companion object {
        private val THEME_KEY = stringPreferencesKey("theme_key")
    }

    val getTheme: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[THEME_KEY] ?: "Light"
    }

    suspend fun saveTheme(theme: String) {
        context.dataStore.edit { prefs ->
            prefs[THEME_KEY] = theme
        }
    }
}

