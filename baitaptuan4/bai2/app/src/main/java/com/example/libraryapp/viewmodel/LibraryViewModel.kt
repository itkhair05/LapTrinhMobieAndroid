package com.example.libraryapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.libraryapp.model.Book
import com.example.libraryapp.model.Student

class LibraryViewModel : ViewModel() {

    private val _students = mutableListOf(
        Student(1, "Nguyễn Văn A", mutableListOf(Book(1, "Lập trình Kotlin"), Book(2, "Android cơ bản"))),
        Student(2, "Trần Thị B", mutableListOf(Book(3, "Giải thuật nâng cao"))),
        Student(3, "Lê Văn C", mutableListOf()) // không mượn sách
    )
    val students: MutableList<Student> get() = _students

    private val _books = mutableListOf(
        Book(1, "Lập trình Kotlin", true),
        Book(2, "Android cơ bản", true),
        Book(3, "Giải thuật nâng cao", true),
        Book(4, "Lập trình Java"),
        Book(5, "Cơ sở dữ liệu")
    )
    val books: MutableList<Book> get() = _books

    // ✅ Thêm sách mới
    fun addBook(title: String) {
        val newId = if (_books.isEmpty()) 1 else _books.maxOf { it.id } + 1
        _books.add(Book(id = newId, title = title))
    }

    // ✅ Xóa sách
    fun removeBook(book: Book) {
        _books.remove(book)
        _students.forEach { student ->
            student.borrowedBooks.removeIf { it.id == book.id }
        }
    }

    // ✅ Cho sinh viên mượn sách mới
    fun addBookForStudent(student: Student?) {
        student ?: return
        val availableBook = _books.find { !it.isBorrowed }
        availableBook?.let {
            it.isBorrowed = true
            student.borrowedBooks.add(it)
        }
    }

    // ✅ Trả về danh sách sinh viên
    fun getAllStudents(): List<Student> {
        return _students
    }
}
