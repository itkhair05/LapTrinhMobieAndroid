package com.example.uicomponents.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnLayoutScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Column Layout", color = Color(0xFF00AEEF)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(3) { colIndex ->
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color(0xFFF2F2F2), RoundedCornerShape(16.dp))
                        .padding(12.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    repeat(4) { boxIndex ->
                        val color = if (boxIndex == 1)
                            Color(0xFF3F8CFF)
                        else
                            Color(0xFFB3D1FF)

                        Box(
                            modifier = Modifier
                                .size(width = 70.dp, height = 50.dp)
                                .background(color, RoundedCornerShape(12.dp))
                        )
                    }
                }
            }
        }
    }
}
