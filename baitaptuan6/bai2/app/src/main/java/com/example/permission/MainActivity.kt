package com.example.permission

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import androidx.compose.foundation.BorderStroke

// --- THEME ---
@Composable
fun PermissionTheme(content: @Composable () -> Unit) {
    val colors = lightColorScheme(
        primary = Color(0xFFFF7043),
        secondary = Color(0xFFFF8A65),
        background = Color.White
    )
    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}

// --- MAIN ACTIVITY ---
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PermissionTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "permission") {
                    composable("permission") { PermissionScreen(navController) }
                    composable("home") { HomeScreen() }
                }
            }
        }
    }
}

// --- PERMISSION SCREEN ---
// --- PERMISSION SCREEN NÂNG CAO ---
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    var step by remember { mutableStateOf(0) }

    val locationState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    val cameraState = rememberPermissionState(Manifest.permission.CAMERA)
    val notifState: com.google.accompanist.permissions.PermissionState? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            rememberPermissionState(Manifest.permission.POST_NOTIFICATIONS)
        else null

    val permissionsList = listOf(
        PermissionData("Location", "Allow maps to access your location?", Icons.Filled.Place, locationState),
        PermissionData("Notification", "Enable notifications for updates", Icons.Filled.Notifications, notifState),
        PermissionData("Camera", "Access camera to scan QR codes", Icons.Filled.CameraAlt, cameraState)
    )

    val current = permissionsList.getOrNull(step) ?: return
    val isGranted = when (current.permissionState?.status) {
        is PermissionStatus.Granted -> true
        else -> false
    }

    LaunchedEffect(current.title, isGranted) {
        if (isGranted) {
            if (step < permissionsList.size - 1) step++
            else navController.navigate("home") { popUpTo("permission") { inclusive = true } }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(listOf(Color(0xFFFFF3E0), Color.White))
            )
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(32.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(32.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(28.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .background(
                            Brush.radialGradient(listOf(Color(0xFFFFCCBC), Color(0xFFFF7043))),
                            RoundedCornerShape(70.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        current.icon,
                        contentDescription = current.title,
                        tint = Color.White,
                        modifier = Modifier.size(70.dp)
                    )
                }

                Text(
                    current.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFF212121)
                )
                Text(
                    current.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { current.permissionState?.launchPermissionRequest() },
                        modifier = Modifier
                            .weight(1f)
                            .height(55.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7043))
                    ) {
                        Text(
                            if (current.permissionState != null) "Allow" else "Turn on",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    OutlinedButton(
                        onClick = { if (step < permissionsList.size - 1) step++ },
                        modifier = Modifier
                            .weight(1f)
                            .height(55.dp),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(1.dp, Color(0xFFFF7043))
                    ) {
                        Text("Skip", color = Color(0xFFFF7043))
                    }
                }

            }
        }
    }
}

// --- HOME SCREEN NÂNG CAO ---
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(listOf(Color(0xFFFFF3E0), Color(0xFFFFCCBC)))
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                "Welcome!",
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xFFBF360C)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "You have successfully granted all permissions",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF5D4037),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { /* TODO: thêm hành động */ },
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7043))
            ) {
                Text("Get Started", color = Color.White)
            }
        }
    }
}


// --- PREVIEW ---
@Preview(showBackground = true)
@Composable
fun PermissionScreenPreview() {
    PermissionTheme {
        PermissionScreen(navController = rememberNavController())
    }
}
