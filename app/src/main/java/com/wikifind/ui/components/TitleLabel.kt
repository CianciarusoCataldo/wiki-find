package com.wikifind.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitleLabel(title: String, modifier: Modifier = Modifier, color: Color = Color.Unspecified) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp, 20.dp)
    ) {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 25.sp, color = color)
    }
}