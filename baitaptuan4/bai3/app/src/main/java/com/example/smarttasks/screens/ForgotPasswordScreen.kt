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
fun ForgotPasswordScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    var email by remember { mutableStateOf(sharedViewModel.email.value) }

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

            Text(
                "SmartTasks",
                fontSize = 22.sp,
                color = Color(0xFF3B82F6),
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(12.dp))
            Text("Forget Password?", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(Modifier.height(4.dp))
            Text(
                "Enter your Email, we will send you a verification code.",
                color = Color.Gray,
                fontSize = 14.sp
            )

            Spacer(Modifier.height(28.dp))
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    sharedViewModel.email.value = it
                },
                label = { Text("Your Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(40.dp))
            Button(
                onClick = {
                    navController.navigate("verify")
                    sharedViewModel.isSubmitted.value = false // 🔹 Reset trạng thái khi bắt đầu lại
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B82F6)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Next", fontSize = 18.sp, color = Color.White)
            }


            if (sharedViewModel.isSubmitted.value) {
                val enteredEmail = sharedViewModel.email.value
                val enteredCode = sharedViewModel.code.value
                val enteredPassword = sharedViewModel.password.value

                Spacer(Modifier.height(30.dp))
                Divider(color = Color.LightGray, thickness = 1.dp)
                Spacer(Modifier.height(10.dp))
                Text("🔹 Thông tin bạn đã nhập:", fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                if (enteredEmail.isNotEmpty()) Text("Email: $enteredEmail")
                if (enteredCode.isNotEmpty()) Text("Code: $enteredCode")
                if (enteredPassword.isNotEmpty()) Text("Password: $enteredPassword")
            }
        }
    }
}
