package com.example.uthnavigation.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.uthnavigation.R
import com.example.uthnavigation.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    // Hiển thị logo trong 2 giây rồi chuyển qua Onboarding
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(Screen.Onboard1.route) {
            popUpTo(Screen.Splash.route) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.uth_logo),
            contentDescription = "UTH Logo",
            modifier = Modifier.size(200.dp)
        )
    }
}
