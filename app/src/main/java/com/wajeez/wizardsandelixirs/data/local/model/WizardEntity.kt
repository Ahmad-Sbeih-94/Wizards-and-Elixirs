package com.wajeez.wizardsandelixirs.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wajeez.wizardsandelixirs.data.repository.model.WizardUIModel

@Entity(tableName = "wizards")
data class WizardEntity(
    @PrimaryKey val id: String,
    val firstName: String?,
    val lastName: String
)

fun WizardEntity.toView(): WizardUIModel = WizardUIModel(id = this.id, firstName = this.firstName, lastName = this.lastName)