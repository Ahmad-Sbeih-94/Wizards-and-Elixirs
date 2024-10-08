package com.wajeez.wizardsandelixirs.data.local

import com.wajeez.wizardsandelixirs.data.local.model.ElixirDetailsEntity
import com.wajeez.wizardsandelixirs.data.local.model.ElixirEntity
import com.wajeez.wizardsandelixirs.data.local.model.WizardEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val wizardDao: WizardDao,
    private val elixirDao: ElixirDao,
    private val elixirDetailsDao: ElixirDetailsDao
) {
    suspend fun insertWizards(wizards: List<WizardEntity>) =
        withContext(Dispatchers.IO) { wizardDao.insertWizards(wizards) }

    fun getAllWizards(): Flow<List<WizardEntity>> = wizardDao.getAllWizards()

    fun getWizardById(wizardId: String): Flow<WizardEntity?> = wizardDao.getWizardById(wizardId)

    suspend fun insertElixirs(elixirs: List<ElixirEntity>) =
        withContext(Dispatchers.IO) { elixirDao.insertElixirs(elixirs) }

    fun getElixirsForWizard(wizardId: String): Flow<List<ElixirEntity>> = elixirDao.getElixirsForWizard(wizardId)

    suspend fun insertElixirDetails(elixirDetails: ElixirDetailsEntity) =
        withContext(Dispatchers.IO) { elixirDetailsDao.insertElixirDetails(elixirDetails) }

    fun getElixirDetailsById(elixirId: String): Flow<ElixirDetailsEntity?> =
        elixirDetailsDao.getElixirDetailsById(elixirId)
}