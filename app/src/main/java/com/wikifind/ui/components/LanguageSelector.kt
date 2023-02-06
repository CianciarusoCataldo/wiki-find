package com.wikifind.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wikifind.R

val supportedLanguages = listOf("it", "en", "es", "de")

@Composable
fun LanguageSelector(
    language: String = "en", onChange: (newLang: String) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    val imgId = when (language) {
        "en" -> R.drawable.en
        "es" -> R.drawable.es
        "fr" -> R.drawable.fr
        "it" -> R.drawable.it
        "de" -> R.drawable.de
        else -> R.drawable.en
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextButton(modifier = Modifier.padding(5.dp, 0.dp), onClick = { expanded = !expanded }) {
            Image(
                modifier = Modifier.size(60.dp),
                painter = painterResource(id = imgId),
                contentDescription = "api icon"
            )
        }
        Text(text = language, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(50.dp)
        ) {
            supportedLanguages.forEachIndexed { index, s ->
                DropdownMenuItem(modifier = Modifier.width(50.dp), onClick = {
                    expanded = false
                    onChange(supportedLanguages[index])
                }, text = { Text(s) })
            }
        }
    }
}