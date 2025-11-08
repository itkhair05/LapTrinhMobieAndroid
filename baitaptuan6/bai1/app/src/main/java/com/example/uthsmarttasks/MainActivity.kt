package com.example.uthsmarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.uthsmarttasks.screens.DetailScreen
import com.example.uthsmarttasks.screens.ListScreen
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SmartTasksApp() }
    }
}

@Composable
fun SmartTasksApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "list") {
        composable("list") { ListScreen(navController) }
        composable(
            "detail/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStack ->
            val taskId = backStack.arguments?.getInt("taskId") ?: 0
            DetailScreen(navController, taskId)
        }
    }
}
