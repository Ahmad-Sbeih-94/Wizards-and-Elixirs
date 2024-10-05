package com.wajeez.wizardsandelixirs.ui.screens.main

sealed class Screen(val route: String) {
    data object Wizards : Screen("wizards")
    data class WizardDetails(val wizardId: String) : Screen("wizardDetails/$wizardId")
    data class ElixirDetails(val elixirId: String) : Screen("elixirDetails/$elixirId")
}