package com.wajeez.wizardsandelixirs.data.local

import com.wajeez.wizardsandelixirs.data.local.model.ElixirEntity
import com.wajeez.wizardsandelixirs.data.local.model.WizardEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val wizardDao: WizardDao,
    private val elixirDao: ElixirDao
) {

    suspend fun insertWizards(wizards: List<WizardEntity>) {
        wizardDao.insertWizards(wizards)
    }

    fun getAllWizards(): Flow<List<WizardEntity>> {
        return wizardDao.getAllWizards()
    }

    suspend fun getWizardById(wizardId: String): WizardEntity? {
        return wizardDao.getWizardById(wizardId)
    }

    suspend fun insertElixirs(elixirs: List<ElixirEntity>) {
        elixirDao.insertElixirs(elixirs)
    }

    fun getElixirsForWizard(wizardId: String): Flow<List<ElixirEntity>> {
        return elixirDao.getElixirsForWizard(wizardId)
    }

    suspend fun getElixirById(elixirId: String): ElixirEntity? {
        return elixirDao.getElixirById(elixirId)
    }
}