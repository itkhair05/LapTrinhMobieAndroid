package com.example.themeselector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.themeselector.data.ThemeDataStore
import com.example.themeselector.screen.ThemeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val themeDataStore = ThemeDataStore(this)

        setContent {
            ThemeScreen(themeDataStore)
        }
    }
}
