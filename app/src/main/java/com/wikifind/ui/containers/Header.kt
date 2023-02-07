package com.wikifind.ui.containers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.wikifind.model.WikiFindViewModel
import com.wikifind.ui.components.LanguageSelector
import com.wikifind.ui.components.SearchWidget

@Composable
fun HeaderPanel(
    modifier: Modifier = Modifier,
    languageSet: String,
    onLanguageChange: (language: String) -> Unit = {}
) {
    val wikiFindModel: WikiFindViewModel = viewModel()
    val focusManager = LocalFocusManager.current
    var lang by remember {
        mutableStateOf(languageSet)
    }

    Column(
        modifier = modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(123, 123, 255),
                        Color(160, 123, 255),
                    )
                )
            )
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        SearchWidget(onSearch = {
            focusManager.clearFocus()
            wikiFindModel.getWiki(page = it, lang = lang)
        })
        Row(
            horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()
        ) {
            LanguageSelector(language = lang, onChange = {
                lang = it
                onLanguageChange(it)
            })
        }


    }

}