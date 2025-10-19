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
fun ConfirmScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    val email = sharedViewModel.email.value
    val code = sharedViewModel.code.value
    val password = sharedViewModel.password.value

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
            Text("Confirm", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(Modifier.height(4.dp))
            Text("We are here to help you!", color = Color.Gray)

            Spacer(Modifier.height(24.dp))
            OutlinedTextField(value = email, onValueChange = {}, label = { Text("Email") }, readOnly = true, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(value = code, onValueChange = {}, label = { Text("Code") }, readOnly = true, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(value = password, onValueChange = {}, label = { Text("Password") }, readOnly = true, modifier = Modifier.fillMaxWidth())

            Spacer(Modifier.height(40.dp))
            Button(
                onClick = {
                    sharedViewModel.isSubmitted.value = true
                    navController.navigate("forgot") {
                        popUpTo("forgot") { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B82F6))
            ) {
                Text("Submit", color = Color.White, fontSize = 18.sp)
            }

        }
    }
}
