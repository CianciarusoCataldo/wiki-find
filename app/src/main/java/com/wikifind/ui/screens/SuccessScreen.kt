package com.wikifind.ui.screens

import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

import com.wikifind.ui.components.TitleLabel

@Composable
fun SuccessScreen(pageContent: String, pageTitle: String, modifier: Modifier = Modifier) {
    TitleLabel(title = pageTitle)
    Divider()
    val wikiSplit = pageContent.replace("===", "WIKI_FIND_TITLE").replace("==", "WIKI_FIND_TITLE")
        .split("WIKI_FIND_TITLE")
    for (j in wikiSplit.indices) {
        if (j % 2 == 0) {
            Text(text = wikiSplit[j])
        } else {
            Divider()
            Text(text = wikiSplit[j], fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
            Divider()
        }
    }
}