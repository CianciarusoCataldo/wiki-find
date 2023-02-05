package com.wikifind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wikifind.model.WikiFindViewModel
import com.wikifind.ui.components.BodyPanel
import com.wikifind.ui.components.FooterPanel
import com.wikifind.ui.components.HeaderPane
import com.wikifind.ui.theme.WikiFindTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WikiFindTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val wikiFindModel: WikiFindViewModel = viewModel()

                    Column() {
                        HeaderPane(
                            state = wikiFindModel.wikiFindUiState,
                            modifier = Modifier.weight(0.7f, true)
                        )
                        BodyPanel(
                            state = wikiFindModel.wikiFindUiState,
                            modifier = Modifier.weight(3f, true)
                        )
                        FooterPanel(
                            state = wikiFindModel.wikiFindUiState,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.4f, true)

                        )
                    }
                }
            }
        }
    }
}

