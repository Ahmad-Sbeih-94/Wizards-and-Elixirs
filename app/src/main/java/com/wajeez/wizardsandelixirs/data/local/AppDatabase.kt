package com.wajeez.wizardsandelixirs.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wajeez.wizardsandelixirs.data.local.model.ElixirDetailsEntity
import com.wajeez.wizardsandelixirs.data.local.model.ElixirEntity
import com.wajeez.wizardsandelixirs.data.local.model.WizardEntity

@Database(
    entities = [WizardEntity::class, ElixirEntity::class, ElixirDetailsEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wizardDao(): WizardDao
    abstract fun elixirDao(): ElixirDao
    abstract fun elixirDetailsDao(): ElixirDetailsDao
}