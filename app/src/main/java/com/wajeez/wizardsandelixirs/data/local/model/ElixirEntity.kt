package com.wajeez.wizardsandelixirs.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.wajeez.wizardsandelixirs.data.repository.model.ElixirsUIModel

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

fun ElixirEntity.toView() = ElixirsUIModel(id = this.id, name = this.name)