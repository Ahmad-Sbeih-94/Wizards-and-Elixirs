package com.wajeez.wizardsandelixirs.data.remote.model

import com.wajeez.wizardsandelixirs.data.local.model.ElixirDetailsEntity

data class ElixirDetails(
    val characteristics: String,
    val difficulty: String,
    val effect: String,
    val id: String,
    val ingredients: List<Ingredient>,
    val inventors: List<Inventor>,
    val manufacturer: String,
    val name: String,
    val sideEffects: String,
    val time: String
)

fun ElixirDetails.toEntity(): ElixirDetailsEntity = ElixirDetailsEntity(
    id = id,
    name = name,
    ingredients = ingredients.map { it.toEntity() },
    difficulty = difficulty,
    time = time,
    characteristics = characteristics,
    effect = effect,
    sideEffects = sideEffects,
    inventors = inventors.map { it.toEntity() },
    manufacturer = manufacturer
)