package com.wikifind.ui.containers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.wikifind.model.WikiFindUiState
import com.wikifind.ui.screens.*

@Composable
fun BodyPanel(
    state: WikiFindUiState, modifier: Modifier = Modifier
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
                    SuccessScreen(
                        pageTitle = state.response.query.pages[0].title,
                        pageContent = state.response.query.pages[0].extract.toString()
                    )
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

