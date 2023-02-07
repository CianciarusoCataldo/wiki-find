package com.wikifind.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

import com.wikifind.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchWidget(onSearch: (searchText: String) -> Unit) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        maxLines = 1,
        trailingIcon = {
            TextButton(modifier = Modifier.background(Color.Transparent), onClick = {
                onSearch(text)
            }) {
                Image(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = "search icon",
                    modifier = Modifier.size(35.dp)
                )
            }

        },
        leadingIcon = {
            Image(
                modifier = Modifier
                    .padding(5.dp)
                    .size(70.dp),
                painter = painterResource(id = R.drawable.wiki_logo),
                contentDescription = "wikipedia logo"
            )
        },
        value = text,
        onValueChange = {
            text = it
        },
        placeholder = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.header_input_placeholder)
            )
        },
        singleLine = true,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(60.dp)
            .shadow(
                elevation = 8.dp, ambientColor = Color.Black, spotColor = Color.Black
            ),
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardActions = KeyboardActions(onDone = {
            onSearch(text)
        }) {},
        textStyle = TextStyle.Default
    )
}