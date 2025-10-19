package com.example.libraryapp.navigation

sealed class Screen(val route: String, val title: String) {
    object Manage : Screen("manage", "Quản lý")
    object Books : Screen("books", "Sách")
    object Students : Screen("students", "Sinh viên")
}
