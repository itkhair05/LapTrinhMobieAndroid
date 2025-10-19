package com.example.uthnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.uthnavigation.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Áp dụng Material 3 theme toàn app
            MaterialTheme {
                // Surface dùng làm nền cho toàn bộ giao diện
                Surface {
                    // Gọi điều hướng chính của ứng dụng
                    AppNavigation()
                }
            }
        }
    }
}
