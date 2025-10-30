package com.example.uthnavigation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uthnavigation.R

@Composable
fun HomeScreen() {
    val blueColor = Color(0xFF2196F3)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 🌟 Logo UTH
            Image(
                painter = painterResource(id = R.drawable.uth_logo),
                contentDescription = "UTH Logo",
                modifier = Modifier
                    .size(150.dp)
                    .padding(bottom = 16.dp)
            )

            // 🌟 Tiêu đề
            Text(
                text = "Welcome to",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Gray
            )

            Text(
                text = "UTH SmartTasks",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = blueColor
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 🌟 Mô tả ngắn
            Text(
                text = "Nơi giúp bạn quản lý nhiệm vụ, học tập và mục tiêu cá nhân một cách thông minh!",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

        }
    }
}
