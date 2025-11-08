package com.example.maytinh

fun main() {
    println("=== CHUONG TRINH MAY TINH CO BAN ===")

    while (true) {
        print("Nhap so thu nhat: ")
        val a = readInt()

        print("Nhap phep toan (+, -, *, /): ")
        val op = readln().trim()

        print("Nhap so thu hai: ")
        val b = readInt()

        val result = calculate(a, b, op)

        println("Ket qua: $result")
        println("--------------------------------")

        print("Ban co muon tinh tiep khong? (y/n): ")
        val choice = readln().trim().lowercase()
        if (choice != "y") {
            println("Cam on ban da su dung chuong trinh!")
            break
        }
        println()
    }
}

fun calculate(a: Int, b: Int, op: String): String {
    return when (op) {
        "+" -> "${a + b}"
        "-" -> "${a - b}"
        "*" -> "${a * b}"
        "/" -> if (b != 0) {
            val kq = a.toDouble() / b.toDouble()
            "Ket qua chia: $kq"
        } else {
            "Loi: khong the chia cho 0"
        }
        else -> "Loi: phep toan khong hop le"
    }
}

fun readInt(): Int {
    while (true) {
        try {
            val input = readln().trim()
            return input.toInt()
        } catch (e: Exception) {
            print("Gia tri khong hop le, hay nhap lai: ")
        }
    }
}
