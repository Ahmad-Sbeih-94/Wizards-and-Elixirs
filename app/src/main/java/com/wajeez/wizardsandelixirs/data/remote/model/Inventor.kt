package com.wajeez.wizardsandelixirs.data.remote.model

import com.wajeez.wizardsandelixirs.data.local.model.InventorEntity

data class Inventor(
    val firstName: String,
    val id: String,
    val lastName: String
)

fun Inventor.toEntity(): InventorEntity = InventorEntity(
    id = id,
    firstName = firstName,
    lastName = lastName
)