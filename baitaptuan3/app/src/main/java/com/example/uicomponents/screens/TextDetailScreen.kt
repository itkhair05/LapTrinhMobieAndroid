package com.example.uicomponents.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextDetailScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Text Detail", color = Color(0xFF00AEEF), fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            val styledText = buildAnnotatedString {
                append("The ")
                withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                    append("quick ")
                }
                withStyle(style = SpanStyle(color = Color(0xFFF44336), fontSize = MaterialTheme.typography.headlineSmall.fontSize)) {
                    append("Brown ")
                }
                append("fox j u m p s ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("over ")
                }
                append("the ")
                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic, textDecoration = TextDecoration.Underline)) {
                    append("lazy ")
                }
                append("dog.")
            }

            Text(
                text = styledText,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )
        }
    }
}
