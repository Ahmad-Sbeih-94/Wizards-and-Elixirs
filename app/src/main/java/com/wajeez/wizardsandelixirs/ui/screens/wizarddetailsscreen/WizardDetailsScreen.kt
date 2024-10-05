package com.wajeez.wizardsandelixirs.ui.screens.wizarddetailsscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import com.wajeez.wizardsandelixirs.data.repository.model.WizardDetailsUIModel
import androidx.compose.foundation.lazy.items
import com.wajeez.wizardsandelixirs.data.repository.model.ElixirsUIModel

@Composable
fun WizardDetailsScreen(
    viewModel: WizardDetailsScreenViewModel = hiltViewModel(),
    onElixirClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            uiState.wizard != null -> {
                WizardDetails(
                    uiState.wizard!!, onElixirClick = onElixirClick, modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize()
                )
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
fun WizardDetails(wizard: WizardDetailsUIModel, onElixirClick: (String) -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "${wizard.firstName ?: ""} ${wizard.lastName}",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = stringResource(R.string.elixirs_created),
            style = MaterialTheme.typography.titleMedium
        )

        LazyColumn {
            items(wizard.elixirs) { elixir ->
                ElixirItem(elixir) { elixirId ->
                    onElixirClick(elixirId)
                }
            }
        }
    }
}

@Composable
fun ElixirItem(elixir: ElixirsUIModel, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(elixir.id) }
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = elixir.name,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}