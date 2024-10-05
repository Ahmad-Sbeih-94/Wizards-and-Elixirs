package com.wajeez.wizardsandelixirs.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventors")
data class InventorEntity(
    @PrimaryKey val id: String,
    val firstName: String?,
    val lastName: String
)