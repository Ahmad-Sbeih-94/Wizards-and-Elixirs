package com.wajeez.wizardsandelixirs.ui.screens.elixirdetailsscreen

import android.net.ConnectivityManager
import android.net.Network
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wajeez.wizardsandelixirs.data.repository.ElixirsRepo
import com.wajeez.wizardsandelixirs.data.repository.model.ElixirDetailsUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

data class ElixirDetailsScreenUiState(
    val elixirDetailsUIModel: ElixirDetailsUIModel? = null,
    val isLoading: Boolean = false,
    val userMessage: String? = null
)

@HiltViewModel
class ElixirDetailsScreenViewModel @Inject constructor(
    private val connectivityManager: ConnectivityManager,
    private val elixirsRepo: ElixirsRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(ElixirDetailsScreenUiState())
    val uiState: StateFlow<ElixirDetailsScreenUiState> = _uiState

    val elixirId = savedStateHandle.get<String>("elixirId")

    init {
        elixirId?.let {
            fetchElixirDetails(elixirId = it)
            refreshElixirDetails(elixirId = elixirId)
        }
    }

    private fun fetchElixirDetails(elixirId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            elixirsRepo.getElixirDetailsById(elixirId).collect { result ->
                _uiState.value = _uiState.value.copy(elixirDetailsUIModel = result, isLoading = false)
            }
        }
    }

    fun refreshElixirDetails(elixirId: String) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true, userMessage = null)
                elixirsRepo.refreshElixirDetail(id = elixirId)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(userMessage = e.message, isLoading = false)
                if (e is UnknownHostException) {
                    observeConnectivity(elixirId = elixirId)
                }
            }
        }
    }

    private fun observeConnectivity(elixirId: String) {
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                refreshElixirDetails(elixirId)
                connectivityManager.unregisterNetworkCallback(this)
            }
        }
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }

    fun retry() {
        _uiState.value = _uiState.value.copy(userMessage = null)
        elixirId?.let { refreshElixirDetails(it) }
    }
}