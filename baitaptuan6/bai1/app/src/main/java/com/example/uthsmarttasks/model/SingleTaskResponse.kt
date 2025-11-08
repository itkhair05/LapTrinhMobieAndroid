package com.example.uthsmarttasks.model

data class SingleTaskResponse(
    val isSuccess: Boolean,
    val message: String,
    val data: Task
)
