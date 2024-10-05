package com.wajeez.wizardsandelixirs.ui.screens.elixirdetailsscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
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
import com.wajeez.wizardsandelixirs.data.repository.model.ElixirDetailsUIModel

@Composable
fun ElixirDetailsScreen(viewModel: ElixirDetailsScreenViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            uiState.elixirDetailsUIModel != null -> {
                ElixirDetails(
                    uiState.elixirDetailsUIModel!!, modifier = Modifier
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
fun ElixirDetails(elixir: ElixirDetailsUIModel, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = elixir.name,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = stringResource(R.string.effect, elixir.effect),
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = stringResource(R.string.difficulty, elixir.difficulty),
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = stringResource(R.string.characteristics, elixir.characteristics ?: "N/A"),
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = stringResource(R.string.ingredients),
            style = MaterialTheme.typography.titleMedium
        )

        LazyColumn {
            items(elixir.ingredients) { ingredient ->
                Text(text = "- $ingredient", style = MaterialTheme.typography.bodySmall)
            }
        }

        Text(
            text = stringResource(R.string.inventors),
            style = MaterialTheme.typography.titleMedium
        )

        LazyColumn {
            items(elixir.inventors) { inventor ->
                Text(text = "- $inventor", style = MaterialTheme.typography.bodySmall)
            }
        }

        Text(
            text = stringResource(R.string.manufacturer, elixir.manufacturer ?: "N/A"),
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = stringResource(R.string.side_effects, elixir.sideEffects ?: "N/A"),
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = stringResource(R.string.preparation_time, elixir.time ?: "N/A"),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}