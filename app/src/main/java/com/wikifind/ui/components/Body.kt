package com.wikifind.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import com.wikifind.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wikifind.model.WikiFindUiState
import com.wikifind.model.WikiFindViewModel

@Composable
fun BodyPanel(state: WikiFindUiState, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {


        when (state) {
            is WikiFindUiState.Success -> {
                TitleLabel(title = state.response.query.pages[0].title)
                Text(state.response.query.pages[0].extract)
            }
            is WikiFindUiState.Loading -> {
                val strokeWidth = 5.dp

                CircularProgressIndicator(
                    modifier = Modifier.drawBehind {
                        drawCircle(
                            Color.Red,
                            radius = size.width / 2 - strokeWidth.toPx() / 2,
                            style = Stroke(strokeWidth.toPx())
                        )
                    }, color = Color.LightGray, strokeWidth = strokeWidth
                )
            }
            is WikiFindUiState.Error -> ErrorScreen(state = state)
        }

    }
}

@Composable
fun TitleLabel(title: String, color: Color = Color.Black, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp, 20.dp)
    ) {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 25.sp, color = color)
    }
}

@Composable
fun ErrorScreen(state: WikiFindUiState, modifier: Modifier = Modifier) {
    val wikiFindModel: WikiFindViewModel = viewModel()

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
                title = "Error", color = Color.Red, modifier = Modifier.padding(10.dp, 0.dp)
            )
        }
        Text(
            text = "Error during search. Re-try search with a valid Wikipedia page title.",
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp, 0.dp)
        )
    }
}