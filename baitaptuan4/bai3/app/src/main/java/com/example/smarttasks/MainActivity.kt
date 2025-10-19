package com.example.smarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.smarttasks.navigation.NavGraph
import com.example.smarttasks.ui.theme.SmartTasksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartTasksTheme {
                val navController = rememberNavController()
                NavGraph(navController)
            }
        }
    }
}
