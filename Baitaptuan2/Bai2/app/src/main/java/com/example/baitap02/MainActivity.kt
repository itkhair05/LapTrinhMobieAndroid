package com.example.baitap02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baitap02.ui.theme.Baitap02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Baitap02Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EmailCheckScreen()
                }
            }
        }
    }
}

@Composable
fun EmailCheckScreen() {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var message by remember { mutableStateOf("") }
    var messageColor by remember { mutableStateOf(Color.Red) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Thực hành 02",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (message.isNotEmpty()) {
            Text(
                text = message,
                color = messageColor,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Button(
            onClick = {
                val input = email.text.trim()

                when {
                    input.isEmpty() -> {
                        message = "Email không hợp lệ"
                        messageColor = Color.Red
                    }
                    !input.contains("@") -> {
                        message = "Email không đúng định dạng"
                        messageColor = Color.Red
                    }
                    else -> {
                        message = "Bạn đã nhập email hợp lệ"
                        messageColor = Color(0xFF00C853) // xanh lá
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Kiểm tra", color = Color.White)
        }
    }
}
