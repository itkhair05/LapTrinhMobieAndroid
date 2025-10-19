package com.example.baitap03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baitap03.ui.theme.Baitap03Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Baitap03Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AgeCheckScreen()
                }
            }
        }
    }
}

@Composable
fun AgeCheckScreen() {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var age by remember { mutableStateOf(TextFieldValue("")) }
    var result by remember { mutableStateOf("") }
    var resultColor by remember { mutableStateOf(Color.Black) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "THỰC HÀNH 01",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // 🔲 Card bao toàn bộ phần nhập và kiểm tra
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(2.dp, Color(0xFF646464)),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFADAFB0)
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // --- Họ và tên ---
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                ) {
                    Text(
                        text = "Họ và tên",
                        modifier = Modifier.width(90.dp),
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        singleLine = true,
                        modifier = Modifier
                            .weight(1f)
                            .height(55.dp)
                    )
                }

                // --- Tuổi ---
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Text(
                        text = "Tuổi",
                        modifier = Modifier.width(90.dp),
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                    OutlinedTextField(
                        value = age,
                        onValueChange = { age = it },
                        singleLine = true,
                        modifier = Modifier
                            .weight(1f)
                            .height(55.dp)
                    )
                }

                // --- Nút kiểm tra ---
                Button(
                    onClick = {
                        val nameInput = name.text.trim()
                        val ageInput = age.text.trim()

                        if (nameInput.isEmpty() || ageInput.isEmpty()) {
                            result = "Vui lòng nhập đầy đủ họ tên và tuổi!"
                            resultColor = Color.Red
                            return@Button
                        }

                        val ageValue = ageInput.toIntOrNull()
                        if (ageValue == null || ageValue < 0) {
                            result = "Tuổi không hợp lệ!"
                            resultColor = Color.Red
                            return@Button
                        }

                        resultColor = Color(0xFF0D47A1)
                        result = when {
                            ageValue > 65 -> "$nameInput là Người già 👴"
                            ageValue in 7..65 -> "$nameInput là Người lớn 🧑"
                            ageValue in 2..6 -> "$nameInput là Trẻ em 👦"
                            else -> "$nameInput là Em bé 👶"
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("Kiểm tra", color = Color.White)
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (result.isNotEmpty()) {
                    Text(
                        text = result,
                        color = resultColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}
