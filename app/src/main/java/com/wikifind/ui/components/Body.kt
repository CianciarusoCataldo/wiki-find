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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wikifind.R
import com.wikifind.model.WikiFindUiState

@Composable
fun BodyPanel(
    state: WikiFindUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(10.dp)
    ) {
        when (state) {
            is WikiFindUiState.Empty -> EmptyScreen()
            is WikiFindUiState.Success -> {
                if (state.response.query.pages[0].extract !== null) {
                    Text(state.response.query.pages[0].extract.toString())
                } else {
                    if (state.response.query.pages[0].missing == true) {
                        NotFoundScreen(state.response.query.pages[0].title)
                    } else {
                        EmptyScreen()
                    }
                }
            }
            is WikiFindUiState.Loading -> LoadingScreen()
            is WikiFindUiState.Error -> ErrorScreen()
        }
    }
}


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

@Composable
fun EmptyScreen(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
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
                contentDescription = "error icon"
            )
            TitleLabel(
                title = stringResource(id = R.string.notFound_header),
                color = Color.Red,
                modifier = Modifier.padding(10.dp, 0.dp)
            )
        }
        Text(
            text = stringResource(R.string.notFound_body).replace("PAGE_TO_SEARCH", page),
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp, 0.dp)
        )
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(0.dp, 80.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = stringResource(R.string.loading_body),
                lineHeight = 36.sp,
                modifier = Modifier.padding(40.dp, 0.dp),
                fontSize = 32.sp
            )
        }
    }
}