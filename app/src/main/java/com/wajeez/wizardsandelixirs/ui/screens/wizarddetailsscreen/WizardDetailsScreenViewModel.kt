package com.wajeez.wizardsandelixirs.ui.screens.wizarddetailsscreen

import android.net.ConnectivityManager
import android.net.Network
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wajeez.wizardsandelixirs.data.repository.WizardsRepo
import com.wajeez.wizardsandelixirs.data.repository.model.WizardDetailsUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

data class WizardDetailsScreenUiState(
    val wizard: WizardDetailsUIModel? = null,
    val isLoading: Boolean = false,
    val userMessage: String? = null
)

@HiltViewModel
class WizardDetailsScreenViewModel @Inject constructor(
    private val connectivityManager: ConnectivityManager,
    private val wizardsRepo: WizardsRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val wizardId = savedStateHandle.get<String>("wizardId")

    private val _uiState = MutableStateFlow(WizardDetailsScreenUiState())
    val uiState: StateFlow<WizardDetailsScreenUiState> = _uiState

    init {
        wizardId?.let {
            fetchWizard(it)
            refreshWizard(wizardId)
        }
    }

    private fun fetchWizard(id: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            wizardsRepo.getWizardDetails(wizardId = id).collect { result ->
                _uiState.value = _uiState.value.copy(wizard = result, isLoading = false)
            }
        }
    }

    fun refreshWizard(id: String) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true, userMessage = null)
                wizardsRepo.refreshWizard(id = id)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(userMessage = e.message, isLoading = false)
                if (e is UnknownHostException) {
                    observeConnectivity(id = id)
                }
            }
        }
    }

    private fun observeConnectivity(id: String) {
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                refreshWizard(id = id)
                connectivityManager.unregisterNetworkCallback(this)
            }
        }
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }

    fun retry() {
        _uiState.value = _uiState.value.copy(userMessage = null)
        wizardId?.let { refreshWizard(id = it) }
    }
}