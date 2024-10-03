package com.wajeez.wizardsandelixirs.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wajeez.wizardsandelixirs.data.local.model.ElixirEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ElixirDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertElixirs(elixirs: List<ElixirEntity>)

    @Query("SELECT * FROM elixirs WHERE wizardId = :wizardId")
    fun getElixirsForWizard(wizardId: String): Flow<List<ElixirEntity>>

    @Query("SELECT * FROM elixirs WHERE id = :elixirId")
    suspend fun getElixirById(elixirId: String): ElixirEntity
}