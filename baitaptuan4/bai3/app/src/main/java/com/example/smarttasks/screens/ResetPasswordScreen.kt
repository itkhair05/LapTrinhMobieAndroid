package com.example.smarttasks.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smarttasks.R
import com.example.smarttasks.SharedViewModel

@Composable
fun ResetPasswordScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(50.dp))
            Image(
                painter = painterResource(id = R.drawable.logo_uth),
                contentDescription = "Logo",
                modifier = Modifier.size(140.dp)
            )
            Spacer(Modifier.height(20.dp))
            Text("SmartTasks", fontSize = 22.sp, color = Color(0xFF3B82F6), fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(12.dp))
            Text("Create New Password", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(Modifier.height(4.dp))
            Text("Your new password must be different from the old one.", color = Color.Gray)

            Spacer(Modifier.height(28.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                modifier = Modifier.fillMaxWidth()
            )

            if (error.isNotEmpty()) {
                Spacer(Modifier.height(8.dp))
                Text(error, color = Color.Red)
            }

            Spacer(Modifier.height(40.dp))
            Button(
                onClick = {
                    if (password == confirmPassword) {
                        sharedViewModel.password.value = password
                        navController.navigate("confirm")
                    } else {
                        error = "Passwords do not match!"
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B82F6)),
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Next", fontSize = 18.sp, color = Color.White)
            }
        }
    }
}
