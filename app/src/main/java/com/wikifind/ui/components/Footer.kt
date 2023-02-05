package com.wikifind.ui.components

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.wikifind.model.WikiFindUiState
import com.wikifind.R
import androidx.compose.foundation.layout.Arrangement

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
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = CenterVertically
    ) {

        when (state) {
            is WikiFindUiState.Success -> {
                FloatingActionButton(modifier = Modifier.padding(20.dp, 0.dp), onClick = {
                    clipboardManager.setText(AnnotatedString((toShow)))
                    Toast.makeText(context, copyToastText, Toast.LENGTH_SHORT).show()
                }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(0.dp, 10.dp)
                    ) {
                        Image(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(id = R.drawable.copy_icon),
                            contentDescription = "copy icon"
                        )
                        Text(stringResource(R.string.copy))
                    }
                }

                ExtendedFloatingActionButton(onClick = {
                    clipboardManager.setText(AnnotatedString((toShow)))
                    val shareIntent = Intent()
                    shareIntent.action = Intent.ACTION_SEND
                    shareIntent.type = "text/plain"
                    shareIntent.putExtra(Intent.EXTRA_TEXT, toShow);
                    ContextCompat.startActivity(
                        context, Intent.createChooser(shareIntent, sendText), null
                    )
                }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(0.dp, 10.dp)
                    ) {
                        Image(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(id = R.drawable.share_icon),
                            contentDescription = "share icon"
                        )
                        Text(stringResource(R.string.share))
                    }
                }

                ExtendedFloatingActionButton(onClick = {
                    clipboardManager.setText(AnnotatedString((toShow)))
                    val openURL = Intent(android.content.Intent.ACTION_VIEW)
                    openURL.data =
                        Uri.parse("https://it.wikipedia.org/wiki/" + state.response.query.pages[0].title)
                    ContextCompat.startActivity(
                        context, openURL, null
                    )
                }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(0.dp, 10.dp)
                    ) {
                        Image(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(id = R.drawable.open_link_icon),
                            contentDescription = "open link icon"
                        )
                        Text(stringResource(R.string.view_page))
                    }
                }
            }
            else -> {}
        }
    }
}