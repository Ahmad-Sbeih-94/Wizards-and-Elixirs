package com.wajeez.wizardsandelixirs.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class IngredientEntity(
    @PrimaryKey val id: String,
    val name: String
)