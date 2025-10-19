package com.example.libraryapp.data

import com.example.libraryapp.model.Book
import com.example.libraryapp.model.Student

object LibraryRepository {
    val books = mutableListOf(
        Book(1, "Lập trình Kotlin"),
        Book(2, "Cấu trúc dữ liệu"),
        Book(3, "Giải tích 1"),
        Book(4, "Cơ sở dữ liệu"),
        Book(5, "Trí tuệ nhân tạo")
    )

    val students = mutableListOf(
        Student(1, "Bạn A", mutableListOf(books[0], books[1])), // 2 sách
        Student(2, "Bạn B", mutableListOf(books[2])),           // 1 sách
        Student(3, "Bạn C", mutableListOf())                    // 0 sách
    )
}
