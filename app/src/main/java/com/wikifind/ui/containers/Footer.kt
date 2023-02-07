package com.wikifind.ui.containers

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

import com.wikifind.R
import com.wikifind.model.WikiFindUiState

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
            .padding(10.dp, 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = CenterVertically
    ) {

        when (state) {
            is WikiFindUiState.Success -> {
                FooterButton(
                    imageId = R.drawable.copy_icon,
                    contentDescription = "copy icon",
                    onClick = {
                        if (toShow != null) {
                            clipboardManager.setText(AnnotatedString((toShow)))
                            Toast.makeText(context, copyToastText, Toast.LENGTH_SHORT).show()
                        }
                    })

                FooterButton(
                    imageId = R.drawable.share_icon,
                    contentDescription = "share icon",
                    onClick = {
                        if (toShow != null) {
                            clipboardManager.setText(AnnotatedString((toShow)))
                            val shareIntent = Intent()
                            shareIntent.action = Intent.ACTION_SEND
                            shareIntent.type = "text/plain"
                            shareIntent.putExtra(Intent.EXTRA_TEXT, toShow)
                            ContextCompat.startActivity(
                                context, Intent.createChooser(shareIntent, sendText), null
                            )
                        }
                    })

                FooterButton(imageId = R.drawable.open_link_icon,
                    contentDescription = "open link icon",
                    onClick = {
                        if (toShow != null) {
                            clipboardManager.setText(AnnotatedString((toShow)))
                            val openURL = Intent(Intent.ACTION_VIEW)
                            openURL.data =
                                Uri.parse("https://it.wikipedia.org/wiki/" + state.response.query.pages[0].title)
                            ContextCompat.startActivity(
                                context, openURL, null
                            )
                        }
                    })
            }
            else -> {}
        }
    }
}

@Composable
fun FooterButton(
    onClick: () -> Unit = {}, @DrawableRes imageId: Int, contentDescription: String = "icon"
) {
    ExtendedFloatingActionButton(modifier = Modifier.padding(10.dp, 0.dp), onClick = {
        onClick()
    }) {
        Row(
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                modifier = Modifier.size(25.dp),
                painter = painterResource(id = imageId),
                contentDescription = contentDescription
            )
        }
    }
}