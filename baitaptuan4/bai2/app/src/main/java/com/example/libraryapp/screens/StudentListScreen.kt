package com.example.libraryapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.libraryapp.model.Student
import com.example.libraryapp.viewmodel.LibraryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentListScreen(viewModel: LibraryViewModel) {
    val students = remember { mutableStateListOf<Student>().apply { addAll(viewModel.getAllStudents()) } }
    var showDialog by remember { mutableStateOf(false) }
    var editStudent by remember { mutableStateOf<Student?>(null) }
    var studentName by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                editStudent = null
                studentName = ""
                showDialog = true
            }) {
                Icon(Icons.Default.Add, contentDescription = "Thêm sinh viên")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "📚 Danh sách sinh viên",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(students) { student ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = student.name,
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Medium
                                    )
                                )
                                Text(
                                    text = "Sách đang mượn: ${student.borrowedBooks.size}",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }

                            Row {
                                IconButton(onClick = {
                                    editStudent = student
                                    studentName = student.name
                                    showDialog = true
                                }) {
                                    Icon(Icons.Default.Edit, contentDescription = "Sửa")
                                }
                                IconButton(onClick = {
                                    students.remove(student)
                                    viewModel.removeStudent(student)
                                }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Xóa")
                                }
                            }
                        }
                    }
                }
            }
        }

        // 🔹 Hộp thoại thêm/sửa sinh viên
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(if (editStudent == null) "Thêm sinh viên" else "Sửa thông tin sinh viên") },
                text = {
                    OutlinedTextField(
                        value = studentName,
                        onValueChange = { studentName = it },
                        label = { Text("Tên sinh viên") },
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                confirmButton = {
                    TextButton(onClick = {
                        if (studentName.isNotBlank()) {
                            if (editStudent == null) {
                                val newStudent = viewModel.addStudent(studentName)
                                students.add(newStudent)
                            } else {
                                viewModel.updateStudent(editStudent!!, studentName)
                                val index = students.indexOf(editStudent)
                                if (index != -1) students[index] = editStudent!!.copy(name = studentName)
                            }
                            showDialog = false
                        }
                    }) {
                        Text("Lưu")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Hủy")
                    }
                }
            )
        }
    }
}
