package com.example.permission

import androidx.compose.ui.graphics.vector.ImageVector
import com.google.accompanist.permissions.PermissionState

data class PermissionData(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val permissionState: PermissionState?
)
