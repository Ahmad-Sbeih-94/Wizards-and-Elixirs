package com.wajeez.wizardsandelixirs.data.remote.model

import com.wajeez.wizardsandelixirs.data.local.model.IngredientEntity

data class Ingredient(
    val id: String,
    val name: String
)

fun Ingredient.toEntity(): IngredientEntity = IngredientEntity(
    id = id,
    name = name
)