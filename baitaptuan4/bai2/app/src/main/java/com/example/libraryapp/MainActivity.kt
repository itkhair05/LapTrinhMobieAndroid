package com.example.libraryapp
import androidx.compose.foundation.layout.padding
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.libraryapp.navigation.NavGraph
import com.example.libraryapp.navigation.Screen
import com.example.libraryapp.viewmodel.LibraryViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                LibraryApp()
            }
        }
    }
}

@Composable
fun LibraryApp() {
    val navController = rememberNavController()
    val viewModel: LibraryViewModel = viewModel()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val currentRoute =
                    navController.currentBackStackEntryAsState().value?.destination?.route

                NavigationBarItem(
                    selected = currentRoute == Screen.Manage.route,
                    onClick = {
                        if (currentRoute != Screen.Manage.route)
                            navController.navigate(Screen.Manage.route)
                    },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Quản lý") },
                    label = { Text("Quản lý") }
                )

                NavigationBarItem(
                    selected = currentRoute == Screen.Books.route,
                    onClick = {
                        if (currentRoute != Screen.Books.route)
                            navController.navigate(Screen.Books.route)
                    },
                    icon = { Icon(Icons.Default.MenuBook, contentDescription = "DS Sách") },
                    label = { Text("DS Sách") }
                )

                NavigationBarItem(
                    selected = currentRoute == Screen.Students.route,
                    onClick = {
                        if (currentRoute != Screen.Students.route)
                            navController.navigate(Screen.Students.route)
                    },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Sinh viên") },
                    label = { Text("Sinh viên") }
                )
            }
        }
    ) { innerPadding ->
        NavGraph(
            navController = navController,
            viewModel = viewModel,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
