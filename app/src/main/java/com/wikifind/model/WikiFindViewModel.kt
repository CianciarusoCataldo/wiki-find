package com.wikifind.model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wikifind.network.WikiFindNetworkApi
import kotlinx.coroutines.launch

/**
 * UI state for WikiFind
 */
sealed interface WikiFindUiState {
    data class Success(val response: WikiResponse) : WikiFindUiState
    data class Error(val error: String) : WikiFindUiState
    object Loading : WikiFindUiState
}

class WikiFindViewModel : ViewModel() {
    var wikiFindUiState: WikiFindUiState by mutableStateOf(WikiFindUiState.Loading)
        private set

    init {
        getWiki("wikipedia")
    }

    fun getWiki(page: String) {

        if (page.isNotEmpty()) {
            viewModelScope.launch {
                wikiFindUiState = try {
                    val response = WikiFindNetworkApi.retrofitService.getWikiData(page)
                    // Log.i("INFO", response.toString())
                    WikiFindUiState.Success(response)
                } catch (e: Exception) {
                    WikiFindUiState.Error(e.toString())
                }
            }
        }
    }
}
