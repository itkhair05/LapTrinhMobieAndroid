package com.example.uthnavigation.model
import androidx.annotation.DrawableRes

data class OnboardPage(
    val title: String,
    val description: String,
    @DrawableRes val imageRes: Int
)

