package com.wajeez.wizardsandelixirs.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wajeez.wizardsandelixirs.data.local.model.WizardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WizardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWizards(wizards: List<WizardEntity>)

    @Query("SELECT * FROM wizards")
    fun getAllWizards(): Flow<List<WizardEntity>>

    @Query("SELECT * FROM wizards WHERE id = :wizardId")
    fun getWizardById(wizardId: String): Flow<WizardEntity?>
}