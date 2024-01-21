package com.m.ammar.composeTemplate.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m.ammar.composeTemplate.repository.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: Repo
) : ViewModel() {
    private val _response = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState.Initial)
    val response: StateFlow<HomeScreenUiState> = _response.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            _response.value = HomeScreenUiState.Loading
            repo.getList().run {
                onSuccess {
                    val response =
                        it.firstOrNull() ?: run {
                            _response.value =
                                HomeScreenUiState.Error(
                                    msg = "Something went wrong"
                                )
                            return@launch
                        }
                    _response.value =
                        HomeScreenUiState.Success(obj = response)
                }
                onFailure {

                    _response.value =
                        HomeScreenUiState.Error(
                            msg = it.message ?: "Something went wrong"
                        )
                }
            }
        }
    }
}
