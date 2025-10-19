package com.example.baitap1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baitap1.ui.theme.Baitap1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Baitap1Theme() {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NumberInputScreen()
                }
            }
        }
    }
}

@Composable
fun NumberInputScreen() {
    var inputText by remember { mutableStateOf("") }
    var numbers by remember { mutableStateOf(listOf<Int>()) }
    var errorMessage by remember { mutableStateOf("") }

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
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                label = { Text("Nhập vào số lượng") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                try {
                    val count = inputText.toInt()
                    if (count <= 0) {
                        errorMessage = "Vui lòng nhập số dương!"
                        numbers = emptyList()
                    } else {
                        numbers = (1..count).toList()
                        errorMessage = ""
                    }
                } catch (e: NumberFormatException) {
                    errorMessage = "Dữ liệu bạn nhập không hợp lệ!"
                    numbers = emptyList()
                }
            }) {
                Text("Tạo")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(numbers) { num ->
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(text = num.toString(), color = Color.White)
                }
            }
        }
    }
}
