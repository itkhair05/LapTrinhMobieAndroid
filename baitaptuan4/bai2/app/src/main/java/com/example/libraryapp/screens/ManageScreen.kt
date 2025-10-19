package com.example.libraryapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.libraryapp.viewmodel.LibraryViewModel

@Composable
fun ManageScreen(viewModel: LibraryViewModel) {
    val students = viewModel.students
    var currentIndex by remember { mutableStateOf(0) }
    val currentStudent = students.getOrNull(currentIndex)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hệ thống\nQuản lý Thư viện",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Ô nhập sinh viên
        OutlinedTextField(
            value = currentStudent?.name ?: "",
            onValueChange = {},
            label = { Text("Sinh viên") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Nút thay đổi sinh viên
        Button(
            onClick = { currentIndex = (currentIndex + 1) % students.size },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Thay đổi")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Danh sách sách
        Text("Danh sách sách", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(10.dp))

        val borrowedBooks = currentStudent?.borrowedBooks ?: emptyList()

        if (borrowedBooks.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Bạn chưa mượn quyển sách nào\nNhấn 'Thêm' để bắt đầu hành trình đọc sách!",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn {
                items(borrowedBooks) { book ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = true,
                                onCheckedChange = {},
                                enabled = false
                            )
                            Text(book.title, modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ✅ Nút thêm sách
        Button(
            onClick = {
                currentStudent?.let {
                    viewModel.addBookForStudent(it)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Thêm")
        }

    }
}
