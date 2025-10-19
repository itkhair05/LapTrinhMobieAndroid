package com.example.libraryapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.libraryapp.screens.BookListScreen
import com.example.libraryapp.screens.ManageScreen
import com.example.libraryapp.screens.StudentListScreen
import com.example.libraryapp.viewmodel.LibraryViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: LibraryViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Manage.route,
        modifier = modifier
    ) {
        composable(Screen.Manage.route) {
            ManageScreen(viewModel)
        }
        composable(Screen.Books.route) {
            BookListScreen(viewModel)
        }
        composable(Screen.Students.route) {
            StudentListScreen(viewModel)
        }
    }
}
