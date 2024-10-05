package com.wajeez.wizardsandelixirs.ui.screens.wizardsscreen

import android.net.ConnectivityManager
import android.net.Network
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wajeez.wizardsandelixirs.data.repository.WizardsRepo
import com.wajeez.wizardsandelixirs.data.repository.model.WizardUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

data class WizardsScreenUiState(
    val wizards: List<WizardUIModel> = emptyList(),
    val isLoading: Boolean = false,
    val userMessage: String? = null
)

@HiltViewModel
class WizardsScreenViewModel @Inject constructor(
    private val connectivityManager: ConnectivityManager,
    private val wizardsRepo: WizardsRepo
) : ViewModel() {

    private val _uiState = MutableStateFlow(WizardsScreenUiState())
    val uiState: StateFlow<WizardsScreenUiState> = _uiState

    init {
        fetchWizards()
        refreshWizards()
    }

    private fun fetchWizards() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            wizardsRepo.getWizards().collect { result ->
                _uiState.value = _uiState.value.copy(wizards = result, isLoading = false)
            }
        }
    }

    fun refreshWizards() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true, userMessage = null)
                wizardsRepo.refreshWizards()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(userMessage = e.message, isLoading = false)
                if (e is UnknownHostException) {
                    observeConnectivity()
                }
            }
        }
    }

    private fun observeConnectivity() {
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                refreshWizards()
                connectivityManager.unregisterNetworkCallback(this)
            }
        }
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }

    fun retry() {
        _uiState.value = _uiState.value.copy(userMessage = null)
        refreshWizards()
    }
}