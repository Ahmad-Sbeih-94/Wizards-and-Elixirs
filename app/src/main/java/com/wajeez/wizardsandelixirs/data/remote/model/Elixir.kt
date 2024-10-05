package com.wajeez.wizardsandelixirs.data.remote.model

import com.wajeez.wizardsandelixirs.data.local.model.ElixirEntity

data class Elixir(
    val id: String,
    val name: String
)

fun Elixir.toEntity(wizardId: String): ElixirEntity = ElixirEntity(id = id, name = name, wizardId = wizardId)