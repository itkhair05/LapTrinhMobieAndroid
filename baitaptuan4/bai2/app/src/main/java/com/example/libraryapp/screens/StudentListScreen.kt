package com.example.libraryapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.libraryapp.viewmodel.LibraryViewModel

@Composable
fun StudentListScreen(viewModel: LibraryViewModel) {
    val students = viewModel.getAllStudents()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text("Danh sách sinh viên", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(10.dp))
        for (s in students) {
            Text("${s.name}: ${s.borrowedBooks.size} sách")
        }
    }
}
