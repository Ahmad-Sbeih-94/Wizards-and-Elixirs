package com.wajeez.wizardsandelixirs.data.remote.model

import com.wajeez.wizardsandelixirs.data.local.model.WizardEntity

data class Wizard(
    val elixirs: List<Elixir>,
    val firstName: String,
    val id: String,
    val lastName: String
)

fun Wizard.toEntity(): WizardEntity = WizardEntity(id = this.id, firstName = this.firstName, lastName = this.lastName)