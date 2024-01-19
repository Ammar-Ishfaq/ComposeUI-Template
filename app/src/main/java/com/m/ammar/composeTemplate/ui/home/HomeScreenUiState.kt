package com.m.ammar.composeTemplate.ui.home

import com.m.ammar.composeTemplate.models.ResistorObject

/**
 * Sealed class to represent UI states in [HomeScreen]
 */
sealed interface HomeScreenUiState {
    data object Initial : HomeScreenUiState
    data object Loading : HomeScreenUiState
    data class Success(val obj: ResistorObject) : HomeScreenUiState
    data class Error(val msg: String) : HomeScreenUiState
}
