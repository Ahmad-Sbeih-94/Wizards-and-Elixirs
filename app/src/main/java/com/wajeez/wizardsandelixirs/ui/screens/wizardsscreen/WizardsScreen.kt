package com.wajeez.wizardsandelixirs.ui.screens.wizardsscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wajeez.wizardsandelixirs.R
import com.wajeez.wizardsandelixirs.data.repository.model.WizardUIModel

@Composable
fun WizardsScreen(viewModel: WizardsScreenViewModel = hiltViewModel(), onWizardClick: (String) -> Unit) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            uiState.wizards.isNotEmpty() -> {
                LazyColumn(
                    contentPadding = PaddingValues(all = 8.dp),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxSize()
                ) {
                    items(uiState.wizards) { wizard ->
                        WizardItem(wizardUIModel = wizard, onWizardClick = onWizardClick)
                    }
                }
            }

            uiState.userMessage != null -> {
                Snackbar(
                    action = {
                        Button(onClick = {
                            viewModel.retry()
                        }) {
                            Text(stringResource(R.string.retry))
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(8.dp)
                ) {
                    Text(text = uiState.userMessage!!)
                }
            }
        }
    }
}

@Composable
fun WizardItem(wizardUIModel: WizardUIModel, onWizardClick: (String) -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onWizardClick(wizardUIModel.id) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Text(text = "${wizardUIModel.firstName ?: ""} ${wizardUIModel.lastName}", modifier = Modifier.padding(16.dp))
    }
}
