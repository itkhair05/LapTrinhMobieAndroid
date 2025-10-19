package com.example.uthnavigation.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uthnavigation.screens.*

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboard1 : Screen("onboard1")
    object Onboard2 : Screen("onboard2")
    object Onboard3 : Screen("onboard3")
    object Home : Screen("home")
}

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) { SplashScreen(navController) }
        composable(Screen.Onboard1.route) { OnboardScreen(navController, 0) }
        composable(Screen.Onboard2.route) { OnboardScreen(navController, 1) }
        composable(Screen.Onboard3.route) { OnboardScreen(navController, 2) }
        composable(Screen.Home.route) { HomeScreen() }
    }
}
