package com.example.uthsmarttasks.screens

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import com.example.uthsmarttasks.model.Task
import com.example.uthsmarttasks.viewmodel.TaskViewModel
import androidx.compose.ui.res.painterResource
import com.example.uthsmarttasks.R  // đổi theo package của bạn
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale




@Composable
fun ListScreen(navController: NavController, viewModel: TaskViewModel = viewModel()) {
    var tasks by remember { mutableStateOf<List<Task>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.onTasksUpdated = {
            tasks = viewModel.tasks
            isLoading = false
        }
        viewModel.loadTasks()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6FA))
    ) {
        when {
            isLoading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }

            tasks.isEmpty() -> EmptyView()

            else -> LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                // ===== Header =====
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Logo
                        Image(
                            painter = painterResource(id = R.drawable.logo_uth),
                            contentDescription = "Logo UTH",
                            modifier = Modifier.size(50.dp),
                            contentScale = ContentScale.Fit // giữ tỉ lệ ảnh
                        )
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "SmartTasks",
                                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFF1976D2)
                            )
                            Text(
                                text = "A simple and efficient to-do app",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        }
                    }
                }

                // ===== Task Items =====
                items(tasks) { task ->
                    TaskCard(task = task) {
                        navController.navigate("detail/${task.id}")
                    }
                }
            }
        }
    }
}


@Composable
fun TaskCard(task: Task, onClick: () -> Unit) {
    val bgColor = when (task.status?.lowercase()) {
        "in progress" -> Color(0xFFFFE4E1) // Hồng nhạt
        "pending" -> Color(0xFFFFF8DC) // Vàng nhạt
        "completed" -> Color(0xFFE0FFF4) // Xanh nhạt
        else -> Color(0xFFE0E0E0)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = bgColor),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(task.title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(4.dp))
            Text(task.description ?: "No description", style = MaterialTheme.typography.bodyMedium, color = Color.DarkGray)
            Spacer(Modifier.height(8.dp))
            Text("Status: ${task.status ?: "Unknown"}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun EmptyView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6FA)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(Icons.Default.HourglassEmpty, contentDescription = "Empty", tint = Color.Gray, modifier = Modifier.size(100.dp))
        Spacer(Modifier.height(12.dp))
        Text("No Tasks Yet!", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
        Text("Stay productive — add something to do", color = Color.Gray, textAlign = TextAlign.Center)
    }
}
