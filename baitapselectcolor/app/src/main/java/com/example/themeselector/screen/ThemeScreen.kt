package com.example.themeselector.screen
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.themeselector.data.ThemeDataStore
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeScreen(themeDataStore: ThemeDataStore) {
    val coroutineScope = rememberCoroutineScope()
    val currentTheme by themeDataStore.getTheme.collectAsState(initial = "Light")
    var selectedTheme by remember { mutableStateOf(currentTheme) }

    // Hiệu ứng chuyển màu nền
    val animatedBgColor by animateColorAsState(
        targetValue = when (currentTheme) {
            "Dark" -> Color(0xFF121212)
            "Pink" -> Color(0xFFF06292)
            "Blue" -> Color(0xFF64B5F6)
            else -> Color.White
        },
        label = "backgroundAnim"
    )

    val animatedTextColor by animateColorAsState(
        targetValue = if (currentTheme == "Dark") Color.White else Color.Black,
        label = "textColorAnim"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(animatedBgColor)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .shadow(10.dp, RoundedCornerShape(20.dp)),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.08f)
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Theme Settings",
                    style = TextStyle(
                        color = animatedTextColor,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(30.dp))

                // Danh sách các theme
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val themes = listOf(
                        "Light" to Color.White,
                        "Dark" to Color(0xFF212121),
                        "Pink" to Color(0xFFE91E63),
                        "Blue" to Color(0xFF2196F3)
                    )

                    themes.forEach { (name, color) ->
                        Box(
                            modifier = Modifier
                                .size(55.dp)
                                .shadow(6.dp, CircleShape)
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(
                                            color.copy(alpha = 0.9f),
                                            color.copy(alpha = 0.7f)
                                        )
                                    ),
                                    shape = CircleShape
                                )
                                .border(
                                    width = if (selectedTheme == name) 4.dp else 2.dp,
                                    color = if (selectedTheme == name) Color.Gray else Color.LightGray,
                                    shape = CircleShape
                                )
                                .clickable { selectedTheme = name }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                Button(
                    onClick = {
                        coroutineScope.launch {
                            themeDataStore.saveTheme(selectedTheme)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = when (selectedTheme) {
                            "Pink" -> Color(0xFFE91E63)
                            "Blue" -> Color(0xFF2196F3)
                            "Dark" -> Color(0xFF212121)
                            else -> Color(0xFFBDBDBD)
                        },
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .height(50.dp)
                        .width(160.dp)
                        .shadow(8.dp, RoundedCornerShape(50))
                ) {
                    Text(
                        text = "Apply",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}

