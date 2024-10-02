package com.wajeez.wizardsandelixirs.data.remote.model

data class ElixirDetails(
    val characteristics: String,
    val difficulty: String,
    val effect: String,
    val id: String,
    val ingredients: List<Ingredient>,
    val inventors: List<Inventor>,
    val manufacturer: Any,
    val name: String,
    val sideEffects: String,
    val time: Any
)