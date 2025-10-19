package com.example.uicomponents.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.uicomponents.screens.*

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "start"
    ) {
        composable("start") { StartScreen(navController) }
        composable("components_list") { ComponentsListScreen(navController) }
        composable("text_detail") { TextDetailScreen(navController) }
        composable("image_screen") { ImageScreen(navController) }
        composable("textfield_screen") { TextFieldScreen(navController) }
        composable("row_layout") { RowLayoutScreen(navController) }
        composable("column_layout") { ColumnLayoutScreen(navController) }
    }
}
