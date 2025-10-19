package com.example.uthnavigation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uthnavigation.model.OnboardRepository
import com.example.uthnavigation.navigation.Screen

@Composable
fun OnboardScreen(navController: NavController, pageIndex: Int) {
    val page = OnboardRepository.pages[pageIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ====== Nút Skip góc trên ======
        TextButton(
            onClick = { navController.navigate(Screen.Home.route) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Skip")
        }

        // ====== Ảnh minh họa ======
        Image(
            painter = painterResource(id = page.imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .padding(top = 16.dp)
        )

        // ====== Tiêu đề và mô tả ======
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = page.title,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = page.description,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        // ====== Hàng nút điều hướng ======
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Nút Back (icon mũi tên)
            if (pageIndex > 0) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(48.dp)) // Giữ bố cục cân
            }

            // Nút Next chiếm toàn bộ phần còn lại
            Button(
                onClick = {
                    when (pageIndex) {
                        0 -> navController.navigate(Screen.Onboard2.route)
                        1 -> navController.navigate(Screen.Onboard3.route)
                        2 -> navController.navigate(Screen.Home.route)
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .padding(start = 12.dp)
            ) {
                Text(if (pageIndex == 2) "Get Started" else "Next")
            }
        }
    }
}
