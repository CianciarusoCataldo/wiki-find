package com.wikifind.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.wikifind.R
import com.wikifind.ui.components.TitleLabel

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(30.dp),
                painter = painterResource(id = R.drawable.error_icon),
                contentDescription = "error icon"
            )
            TitleLabel(
                title = stringResource(R.string.error_title),
                color = Color.Red,
                modifier = Modifier.padding(10.dp, 0.dp)
            )
        }
        Text(
            text = stringResource(R.string.error_body),
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp, 0.dp)
        )
    }
}
