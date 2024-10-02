package com.wajeez.wizardsandelixirs.data.remote.model

data class Wizard(
    val elixirs: List<Elixir>,
    val firstName: String,
    val id: String,
    val lastName: String
)