package com.wajeez.wizardsandelixirs.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "elixir_details")
data class ElixirDetailsEntity(
    @PrimaryKey val id: String,
    val name: String,
    val effect: String,
    val difficulty: String,
    val characteristics: String,
    val ingredients: List<IngredientEntity>,
    val inventors: List<InventorEntity>,
    val manufacturer: String?,
    val sideEffects: String,
    val time: String?
)