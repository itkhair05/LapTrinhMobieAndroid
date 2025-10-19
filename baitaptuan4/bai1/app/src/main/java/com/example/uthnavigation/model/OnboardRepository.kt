package com.example.uthnavigation.model

import com.example.uthnavigation.R

object OnboardRepository {
    val pages = listOf(
        OnboardPage(
            title = "Easy Time Management",
            description = "With management based on priority and daily tasks, it gives convenience in managing what must be done first.",
            imageRes = R.drawable.img_time_management
        ),
        OnboardPage(
            title = "Increase Work Effectiveness",
            description = "Time management and prioritization of important tasks increase your work performance.",
            imageRes = R.drawable.img_effectiveness
        ),
        OnboardPage(
            title = "Reminder Notification",
            description = "The advantage of this app is that it provides reminders so you never forget tasks and manage time effectively.",
            imageRes = R.drawable.img_notification
        )
    )
}

