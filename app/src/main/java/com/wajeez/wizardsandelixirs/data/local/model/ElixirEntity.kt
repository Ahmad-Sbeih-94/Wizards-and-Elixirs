package com.wajeez.wizardsandelixirs.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "elixirs",
    foreignKeys = [ForeignKey(
        entity = WizardEntity::class,
        parentColumns = ["id"],
        childColumns = ["wizardId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ElixirEntity(
    @PrimaryKey val id: String,
    val name: String,
    val wizardId: String // Foreign key reference to WizardEntity
)