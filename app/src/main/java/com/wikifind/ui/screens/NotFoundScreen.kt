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
fun NotFoundScreen(page: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(50.dp),
                painter = painterResource(id = R.drawable.failure_icon),
                contentDescription = "not found icon"
            )
            TitleLabel(
                title = stringResource(id = R.string.notFound_header),
                color = Color.Red,
                modifier = Modifier.padding(10.dp, 0.dp)
            )
        }
        Column(modifier = Modifier.padding(10.dp, 0.dp)) {
            Row() {
                val textSplit = stringResource(R.string.notFound_body).split("PAGE_TO_SEARCH")

                Text(
                    text = textSplit[0],
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                )
                Text(
                    text = page,
                    color = Color.Blue,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                )
                Text(
                    text = textSplit[1],
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                )
            }
            Text(
                text = stringResource(R.string.notFound_tryAgain),
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
            )
        }


    }
}