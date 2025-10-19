package com.example.smarttasks.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smarttasks.SharedViewModel
import com.example.smarttasks.screens.*

@Composable
fun NavGraph(navController: NavHostController) {
    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(navController = navController, startDestination = "forgot") {
        composable("forgot") { ForgotPasswordScreen(navController, sharedViewModel) }
        composable("verify") { VerifyCodeScreen(navController, sharedViewModel) }
        composable("reset") { ResetPasswordScreen(navController, sharedViewModel) }
        composable("confirm") { ConfirmScreen(navController, sharedViewModel) }
    }
}
