package com.example.smarttasks

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    var email = mutableStateOf("")
    var code = mutableStateOf("")
    var password = mutableStateOf("")
    var isSubmitted = mutableStateOf(false)
}
