package com.wajeez.wizardsandelixirs.data.repository.model

data class ElixirDetailsUIModel(
    val id: String,
    val name: String,
    val effect: String,
    val difficulty: String,
    val characteristics: String,
    val ingredients: List<String>,
    val inventors: List<String>,
    val manufacturer: String?,
    val sideEffects: String,
    val time: String?
)