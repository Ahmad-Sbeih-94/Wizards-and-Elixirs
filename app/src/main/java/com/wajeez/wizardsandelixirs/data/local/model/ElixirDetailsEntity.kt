package com.wajeez.wizardsandelixirs.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wajeez.wizardsandelixirs.data.repository.model.ElixirDetailsUIModel

@Entity(tableName = "elixir_details")
data class ElixirDetailsEntity(
    @PrimaryKey val id: String,
    val name: String,
    val effect: String,
    val difficulty: String,
    val characteristics: String?,
    val ingredients: List<IngredientEntity>,
    val inventors: List<InventorEntity>,
    val manufacturer: String?,
    val sideEffects: String?,
    val time: String?
)

fun ElixirDetailsEntity.toView(): ElixirDetailsUIModel = ElixirDetailsUIModel(
    id = this.id,
    name = this.name,
    effect = this.effect,
    difficulty = this.difficulty,
    characteristics = this.characteristics,
    ingredients = this.ingredients.map { it.name },
    inventors = this.inventors.map { "${it.firstName} ${it.lastName}" },
    manufacturer = this.manufacturer,
    sideEffects = this.sideEffects,
    time = this.time
)