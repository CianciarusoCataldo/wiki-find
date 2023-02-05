package com.wikifind.ui.components

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.wikifind.model.WikiFindUiState
import com.wikifind.R

@Composable
fun FooterPanel(state: WikiFindUiState, modifier: Modifier = Modifier) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    val toShow = when (state) {
        is WikiFindUiState.Success -> state.response.query.pages[0].extract
        else -> ""
    }

    val context = LocalContext.current
    val sendText = stringResource(R.string.send)
    val copyToastText = stringResource(R.string.toast_copy_success)

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
            .padding(30.dp, 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ElevatedButton(modifier = Modifier.padding(20.dp, 0.dp), onClick = {
            clipboardManager.setText(AnnotatedString((toShow)))
            Toast.makeText(context, copyToastText, Toast.LENGTH_SHORT).show()
        }) {
            Text("Copia")
        }

        ElevatedButton(onClick = {
            clipboardManager.setText(AnnotatedString((toShow)))
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, toShow);
            ContextCompat.startActivity(context, Intent.createChooser(shareIntent, sendText), null)
        }) {
            Text(stringResource(R.string.share))
        }
    }
}