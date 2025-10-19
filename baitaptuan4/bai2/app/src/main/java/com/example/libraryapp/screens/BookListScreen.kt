package com.example.libraryapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.libraryapp.model.Book
import com.example.libraryapp.viewmodel.LibraryViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MenuBook   // ✅ thêm dòng này

@Composable
fun BookListScreen(viewModel: LibraryViewModel) {
    val books = remember { viewModel.books }
    var newTitle by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text("Danh sách sách", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = newTitle,
            onValueChange = { newTitle = it },
            label = { Text("Nhập tên sách mới") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (newTitle.isNotBlank()) {
                    viewModel.addBook(newTitle)
                    newTitle = ""
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Text("Thêm sách")
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            items(books) { book ->
                BookItem(
                    book = book,
                    onDelete = { viewModel.removeBook(book) }
                )
            }
        }
    }
}

@Composable
fun BookItem(book: Book, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(book.title)
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Xóa") // ✅ sửa icon cho đúng
            }
        }
    }
}
