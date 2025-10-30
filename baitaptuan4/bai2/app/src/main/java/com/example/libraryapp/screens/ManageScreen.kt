package com.example.libraryapp.screens
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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
import com.example.libraryapp.model.Book
import com.example.libraryapp.viewmodel.LibraryViewModel
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll


@Composable
fun ManageScreen(viewModel: LibraryViewModel) {
    val students = viewModel.students
    var currentIndex by remember { mutableStateOf(0) }
    val currentStudent = students.getOrNull(currentIndex)

    var showAddBookDialog by remember { mutableStateOf(false) }

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

        // Ô hiển thị sinh viên hiện tại
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
            onClick = {
                if (students.isNotEmpty()) {
                    currentIndex = (currentIndex + 1) % students.size
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Thay đổi")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Danh sách sách đang mượn
        Text("📚 Danh sách sách đã mượn", style = MaterialTheme.typography.titleMedium)
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
                    "Bạn chưa mượn quyển sách nào\nNhấn 'Thêm' để chọn sách!",
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
                            Text(book.title, modifier = Modifier.weight(1f))
                            IconButton(onClick = {
                                currentStudent?.let {
                                    viewModel.removeBookFromStudent(it, book)
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Xóa sách"
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ✅ Nút thêm sách
        Button(
            onClick = { showAddBookDialog = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Thêm")
        }

        // 🔹 Hộp thoại chọn sách
        if (showAddBookDialog) {
            AddBookDialog(
                allBooks = viewModel.books,
                onConfirm = { selectedBooks ->
                    currentStudent?.let {
                        viewModel.addBooksForStudent(it, selectedBooks)
                    }
                    showAddBookDialog = false
                },
                onCancel = { showAddBookDialog = false }
            )
        }
    }
}

@Composable
fun AddBookDialog(
    allBooks: List<Book>,
    onConfirm: (List<Book>) -> Unit,
    onCancel: () -> Unit
) {
    val selectedBooks = remember { mutableStateListOf<Book>() }

    // 🔹 Lọc chỉ những sách chưa được mượn
    val availableBooks = allBooks.filter { it.isBorrowed.not() }

    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text("Chọn sách để thêm") },
        text = {
            if (availableBooks.isEmpty()) {
                Text("Hiện không còn sách nào trống để thêm.")
            } else {
                // 🔹 Thêm thanh cuộn cho danh sách
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 300.dp)
                        .padding(top = 4.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    availableBooks.forEach { book ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = selectedBooks.contains(book),
                                onCheckedChange = { checked ->
                                    if (checked) selectedBooks.add(book)
                                    else selectedBooks.remove(book)
                                }
                            )
                            Text(book.title)
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(selectedBooks) }) {
                Text("Lưu")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                Text("Hủy")
            }
        }
    )
}

