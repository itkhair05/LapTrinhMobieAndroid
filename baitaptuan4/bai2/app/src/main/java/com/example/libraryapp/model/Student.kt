package com.example.libraryapp.model
data class Student(
    val id: Int,
    val name: String,
    val borrowedBooks: MutableList<Book> = mutableListOf()
)


