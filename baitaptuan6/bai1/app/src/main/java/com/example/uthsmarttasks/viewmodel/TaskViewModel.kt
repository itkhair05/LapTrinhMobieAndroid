package com.example.uthsmarttasks.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uthsmarttasks.model.Task
import com.example.uthsmarttasks.network.RetrofitClient
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
    val tasks = mutableStateListOf<Task>()  // mutableStateListOf để Compose observe
    var onTasksUpdated: (() -> Unit)? = null

    // Load tasks từ API
    fun loadTasks() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.getTasks()
                if (response.isSuccessful) {
                    tasks.clear()
                    tasks.addAll(response.body()?.data ?: emptyList())
                    onTasksUpdated?.invoke()
                    Log.d("TaskViewModel", "Tasks loaded: ${tasks.size}")
                } else {
                    tasks.clear()
                    onTasksUpdated?.invoke()
                    Log.e("TaskViewModel", "Failed: ${response.code()}")
                }
            } catch (e: Exception) {
                tasks.clear()
                onTasksUpdated?.invoke()
                Log.e("TaskViewModel", "Exception: ${e.message}")
            }
        }
    }

    // Xóa task local
    fun deleteTaskLocal(taskId: Int) {
        tasks.removeAll { it.id == taskId }
        onTasksUpdated?.invoke()
    }
}
