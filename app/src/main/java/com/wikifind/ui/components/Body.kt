package com.wikifind.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wikifind.R
import com.wikifind.model.WikiFindUiState
import com.wikifind.model.WikiFindViewModel

@Composable
fun BodyPanel(state: WikiFindUiState, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(10.dp)
    ) {


        when (state) {
            is WikiFindUiState.Empty -> {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp, 50.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        alpha = 0.3f,
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Empty screen logo",
                    )
                }

            }
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
fun TitleLabel(title: String, color: Color = Color.Unspecified, modifier: Modifier = Modifier) {
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