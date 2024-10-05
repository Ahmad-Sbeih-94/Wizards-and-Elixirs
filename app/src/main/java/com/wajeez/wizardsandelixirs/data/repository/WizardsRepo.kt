package com.wajeez.wizardsandelixirs.data.repository

import com.wajeez.wizardsandelixirs.data.repository.model.WizardDetailsUIModel
import com.wajeez.wizardsandelixirs.data.repository.model.WizardUIModel
import kotlinx.coroutines.flow.Flow

interface WizardsRepo {
    fun getWizards(): Flow<List<WizardUIModel>>
    suspend fun refreshWizards()
    fun getWizardDetails(wizardId: String): Flow<WizardDetailsUIModel?>
    suspend fun refreshWizard(id: String)
}