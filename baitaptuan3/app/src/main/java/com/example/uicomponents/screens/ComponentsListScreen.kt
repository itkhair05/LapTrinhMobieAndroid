package com.example.uicomponents.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ComponentsListScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "UI Components List",
            style = MaterialTheme.typography.titleLarge.copy(color = Color(0xFF0A84FF)),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(16.dp))

        Section("Display") {
            ComponentCard("Text", "Displays text") {
                navController.navigate("text_detail")
            }
            ComponentCard("Image", "Displays an image") {
                navController.navigate("image_screen")
            }
        }

        Section("Input") {
            ComponentCard("TextField", "Input field for text") {
                navController.navigate("textfield_screen")
            }
            ComponentCard("PasswordField", "Input field for passwords") {
                navController.navigate("textfield_screen")
            }
        }

        Section("Layout") {
            ComponentCard("Column", "Arranges elements vertically") {
                navController.navigate("column_layout")
            }
            ComponentCard("Row", "Arranges elements horizontally") {
                navController.navigate("row_layout")
            }
        }
    }
}

@Composable
fun Section(title: String, content: @Composable ColumnScope.() -> Unit) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(vertical = 8.dp)
    )
    Column(content = content)
}

@Composable
fun ComponentCard(title: String, description: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color(0xFFBFE8FF), shape = RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Column {
            Text(title, fontWeight = FontWeight.Bold)
            Text(description)
        }
    }
}
