package com.wajeez.wizardsandelixirs.data.repository.model

data class WizardDetailsUIModel(
    val id: String,
    val firstName: String?,
    val lastName: String?,
    val elixirs: List<ElixirsUIModel>
)