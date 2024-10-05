package com.wajeez.wizardsandelixirs.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wajeez.wizardsandelixirs.data.local.model.ElixirDetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ElixirDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertElixirDetails(elixirDetails: ElixirDetailsEntity)

    @Query("SELECT * FROM elixir_details WHERE id = :elixirId")
    fun getElixirDetailsById(elixirId: String): Flow<ElixirDetailsEntity?>
}