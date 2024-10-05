package com.wajeez.wizardsandelixirs.ui.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wajeez.wizardsandelixirs.R
import com.wajeez.wizardsandelixirs.ui.screens.elixirdetailsscreen.ElixirDetailsScreen
import com.wajeez.wizardsandelixirs.ui.screens.wizarddetailsscreen.WizardDetailsScreen
import com.wajeez.wizardsandelixirs.ui.screens.wizardsscreen.WizardsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WizardsAndElixirs() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = stringResource(R.string.app_name)) }) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Wizards.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Wizards.route) {
                WizardsScreen { wizardId ->
                    navController.navigate(Screen.WizardDetails(wizardId).route)
                }
            }
            composable("wizardDetails/{wizardId}") {
                WizardDetailsScreen { elixirId ->
                    navController.navigate(Screen.ElixirDetails(elixirId).route)
                }
            }
            composable("elixirDetails/{elixirId}") {
                ElixirDetailsScreen()
            }
        }
    }
}