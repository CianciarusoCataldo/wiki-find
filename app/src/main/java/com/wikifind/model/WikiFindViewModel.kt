package com.wikifind.model

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

    object Empty : WikiFindUiState
}

class WikiFindViewModel : ViewModel() {
    var wikiFindUiState: WikiFindUiState by mutableStateOf(WikiFindUiState.Empty)
        private set

    init {
    }

    fun getWiki(page: String) {


        viewModelScope.launch {
            if (page.isNotEmpty()) {
                wikiFindUiState = WikiFindUiState.Loading
                wikiFindUiState = try {
                    val response = WikiFindNetworkApi.retrofitService.getWikiData(page)
                    // Log.i("INFO", response.toString())
                    WikiFindUiState.Success(response)
                } catch (e: Exception) {
                    WikiFindUiState.Error(e.toString())
                }
            } else {
                wikiFindUiState = WikiFindUiState.Empty
            }
        }

    }
}
