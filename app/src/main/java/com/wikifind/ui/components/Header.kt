package com.wikifind.ui.components

import android.util.Log
import com.wikifind.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wikifind.model.WikiFindUiState
import com.wikifind.model.WikiFindViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderPane(state: WikiFindUiState, modifier: Modifier = Modifier) {

    var text by remember { mutableStateOf("") }
    val wikiFindModel: WikiFindViewModel = viewModel()
    val focusManager = LocalFocusManager.current

    Row(
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
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = CenterVertically
    ) {

        Row(
            verticalAlignment = CenterVertically
        ) {
            OutlinedTextField(
                trailingIcon = {

                    TextButton(
                        modifier = Modifier.background(Color.Transparent),
                        onClick = { wikiFindModel.getWiki(text) }) {
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
                placeholder = { Text("Insert a Wikipedia page title") },
                singleLine = true,
                modifier = Modifier
                    .weight(2f)
                    .padding(10.dp)
                    .height(60.dp)
                    .shadow(
                        elevation = 8.dp, ambientColor = Color.Black, spotColor = Color.Black
                    ),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                keyboardActions = KeyboardActions(onDone = {
                    Log.i("INFO", text.replace(" ", "_"))
                    wikiFindModel.getWiki(text)
                    focusManager.clearFocus()
                }) {},
                textStyle = TextStyle.Default
            )
        }

    }
}