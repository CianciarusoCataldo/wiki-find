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
import com.wikifind.ui.containers.BodyPanel
import com.wikifind.ui.containers.FooterPanel
import com.wikifind.ui.containers.HeaderPanel
import com.wikifind.ui.theme.WikiFindTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = this.getSharedPreferences("config", MODE_PRIVATE) ?: return

        setContent {
            WikiFindTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val wikiFindModel: WikiFindViewModel = viewModel()
                    Column() {
                        HeaderPanel(
                            modifier = Modifier.weight(1.0f, true), onLanguageChange = {
                                with(sharedPref.edit()) {
                                    putString("lang", it)
                                    apply()
                                }

                            }, languageSet = sharedPref.getString("lang", null).toString()
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

