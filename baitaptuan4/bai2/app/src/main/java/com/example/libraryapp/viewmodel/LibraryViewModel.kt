package com.example.libraryapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.libraryapp.model.Book
import com.example.libraryapp.model.Student

class LibraryViewModel : ViewModel() {

    // ✅ Danh sách sinh viên có thể theo dõi thay đổi
    private val _students = mutableStateListOf(
        Student(1, "Nguyễn Văn A", mutableListOf(Book(1, "Lập trình Kotlin"), Book(2, "Android cơ bản"))),
        Student(2, "Trần Thị B", mutableListOf(Book(3, "Giải thuật nâng cao"))),
        Student(3, "Lê Văn C", mutableListOf()) // không mượn sách
    )
    val students: List<Student> get() = _students

    // Danh sách sách
    private val _books = mutableStateListOf(
        Book(1, "Lập trình Kotlin", true),
        Book(2, "Android cơ bản", true),
        Book(3, "Giải thuật nâng cao", true),
        Book(4, "Lập trình Java"),
        Book(5, "Cơ sở dữ liệu")
    )
    val books: List<Book> get() = _books

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

            // 🔁 Tạo bản sao Student mới để Compose phát hiện thay đổi
            val updatedStudent = student.copy(
                borrowedBooks = (student.borrowedBooks + it).toMutableList()
            )

            val index = _students.indexOfFirst { s -> s.id == student.id }
            if (index != -1) _students[index] = updatedStudent
        }
    }

    // ✅ Thêm sinh viên mới
    fun addStudent(name: String): Student {
        val newId = if (_students.isEmpty()) 1 else _students.maxOf { it.id } + 1
        val newStudent = Student(newId, name)
        _students.add(newStudent)
        return newStudent
    }

    // ✅ Sửa sinh viên
    fun updateStudent(student: Student, newName: String) {
        val index = _students.indexOfFirst { it.id == student.id }
        if (index != -1) {
            _students[index] = _students[index].copy(name = newName)
        }
    }

    // ✅ Xóa sinh viên
    fun removeStudent(student: Student) {
        _students.removeIf { it.id == student.id }
    }

    fun addBooksForStudent(student: Student, books: List<Book>) {
        val index = _students.indexOfFirst { it.id == student.id }
        if (index != -1) {
            val currentBorrowed = _students[index].borrowedBooks.toMutableList()
            for (book in books) {
                if (!currentBorrowed.contains(book)) {
                    currentBorrowed.add(book)
                }
            }
            _students[index] = _students[index].copy(
                borrowedBooks = currentBorrowed.toMutableList() // ✅ ép lại về MutableList
            )
        }
    }

    fun removeBookFromStudent(student: Student, book: Book) {
        val index = _students.indexOfFirst { it.id == student.id }
        if (index != -1) {
            val updatedList = _students[index].borrowedBooks
                .filter { it.id != book.id }
                .toMutableList() // ✅ ép lại về MutableList
            _students[index] = _students[index].copy(
                borrowedBooks = updatedList
            )
        }
    }



    fun getAllStudents(): List<Student> = _students
}
