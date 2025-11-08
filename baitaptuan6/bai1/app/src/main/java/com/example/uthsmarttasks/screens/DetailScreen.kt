package com.example.uthsmarttasks.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.uthsmarttasks.model.Task
import com.example.uthsmarttasks.model.Reminder
import com.example.uthsmarttasks.network.RetrofitClient
import com.example.uthsmarttasks.viewmodel.TaskViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    taskId: Int,
    viewModel: TaskViewModel = viewModel()
) {
    var task by remember { mutableStateOf<Task?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // ===== Load task =====
    LaunchedEffect(taskId, viewModel.tasks) {
        task = viewModel.tasks.find { it.id == taskId }

        if (task == null) {
            try {
                val response = RetrofitClient.api.getTasks() // hoặc api.getTaskDetail(taskId)
                if (response.isSuccessful) {
                    val tasksFromApi = response.body()?.data ?: emptyList()
                    task = tasksFromApi.find { it.id == taskId }
                }
            } catch (e: Exception) {
                Log.e("DetailScreen", "Load task failed: ${e.message}")
            }
        }

        isLoading = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chi tiết công việc", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Box(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFF5F6FA))
        ) {
            when {
                isLoading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }

                task == null -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Không tìm thấy công việc!", color = Color.Gray)
                }

                else -> TaskDetailContent(task!!, viewModel, taskId, navController, snackbarHostState)
            }
        }
    }
}

@Composable
fun TaskDetailContent(
    task: Task,
    viewModel: TaskViewModel,
    taskId: Int,
    navController: NavController,
    snackbarHostState: SnackbarHostState
) {
    val scope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // ===== Thông tin cơ bản =====
        item {
            Text(task.title, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(4.dp))
            Text(task.description ?: "No description", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            Spacer(Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(16.dp)) {
                    InfoRow(Icons.Default.Folder, "Danh mục", task.category)
                    InfoRow(Icons.Default.Timer, "Trạng thái", task.status)
                    InfoRow(Icons.Default.PriorityHigh, "Độ ưu tiên", task.priority)
                    InfoRow(Icons.Default.Event, "Hạn cuối", task.dueDate)
                }
            }
            Spacer(Modifier.height(20.dp))
        }

        // ===== Subtasks =====
        if (task.subtasks.isNotEmpty()) {
            item {
                Text("Công việc phụ", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))
            }
            items(task.subtasks) { sub ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        if (sub.isCompleted) Icons.Default.CheckBox else Icons.Default.CheckBoxOutlineBlank,
                        contentDescription = null,
                        tint = if (sub.isCompleted) Color(0xFF4CAF50) else Color.Gray
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(sub.title)
                }
            }
            item { Spacer(Modifier.height(20.dp)) }
        }

        // ===== Attachments =====
        if (task.attachments.isNotEmpty()) {
            item {
                Text("Tệp đính kèm", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))
            }
            items(task.attachments) { file ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.AttachFile, contentDescription = null, tint = Color(0xFF1976D2))
                    Spacer(Modifier.width(8.dp))
                    Text(file.fileName)
                }
            }
            item { Spacer(Modifier.height(20.dp)) }
        }

        // ===== Reminders =====
        if (task.reminders.isNotEmpty()) {
            item {
                Text("Nhắc nhở", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))
            }
            items(task.reminders) { reminder ->
                ReminderRow(reminder)
            }
            item { Spacer(Modifier.height(20.dp)) }
        }

        // ===== Nút Xóa =====
        item {
            Button(
                onClick = {
                    scope.launch {
                        try {
                            val response = RetrofitClient.api.deleteTask(task.id)
                            if (response.isSuccessful) {
                                snackbarHostState.showSnackbar("Xóa task thành công!")
                                // Quay về màn hình trước
                                navController.popBackStack()
                            } else {
                                snackbarHostState.showSnackbar("Xóa task thất bại: ${response.code()}")
                                Log.e("DetailScreen", "Delete failed: ${response.errorBody()?.string()}")
                            }
                        } catch (e: Exception) {
                            snackbarHostState.showSnackbar("Xóa task thất bại: ${e.message}")
                            Log.e("DetailScreen", "Delete exception: ${e.message}")
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
            ) {
                Icon(Icons.Default.Delete, contentDescription = null, tint = Color.White)
                Spacer(Modifier.width(8.dp))
                Text("Xóa Task", color = Color.White)
            }
        }

    }
}

@Composable
fun InfoRow(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String?) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = Color(0xFF1976D2))
        Spacer(Modifier.width(10.dp))
        Text("$label: ", fontWeight = FontWeight.SemiBold)
        Text(value ?: "-", color = Color.DarkGray)
    }
}

@Composable
fun ReminderRow(reminder: Reminder) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Notifications, contentDescription = null, tint = Color(0xFFFF9800))
        Spacer(Modifier.width(8.dp))
        Text(
            text = "${reminder.type} — ${formatDate(reminder.time) ?: reminder.time}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

// ===== Hàm formatDate =====
fun formatDate(dateString: String?): String? {
    return try {
        if (dateString == null) return null
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val outputFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
        val date = inputFormat.parse(dateString)
        outputFormat.format(date!!)
    } catch (e: Exception) {
        null
    }
}
