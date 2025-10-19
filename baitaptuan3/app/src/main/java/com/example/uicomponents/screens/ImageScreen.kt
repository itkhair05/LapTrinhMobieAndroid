package com.example.uicomponents.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
import com.example.uicomponents.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageScreen(navController: NavController) {
    // Trạng thái tick của checkbox
    var checked by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Images", color = Color(0xFF00AEEF), fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Ảnh online
            Image(
                painter = rememberAsyncImagePainter("https://cdn2.fptshop.com.vn/unsafe/hoc_phi_uth_2025_c5fa7e3508.jpg"),
                contentDescription = "UTH Logo Online",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentScale = ContentScale.Crop
            )

            Text("https://cdn2.fptshop.com.vn/unsafe/hoc_phi_uth_2025_c5fa7e3508.jpg")

            // Ảnh trong app
            Image(
                painter = painterResource(R.drawable.ic_university),
                contentDescription = "UTH in app",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentScale = ContentScale.Crop
            )

            // Hàng chữ + checkbox
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "In app",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )

                Checkbox(
                    checked = checked,
                    onCheckedChange = { checked = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF00AEEF),
                        uncheckedColor = Color.Gray,
                        checkmarkColor = Color.White
                    )
                )
            }
        }
    }
}
