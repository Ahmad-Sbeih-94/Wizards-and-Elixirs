package com.wajeez.wizardsandelixirs.data.repository

import com.wajeez.wizardsandelixirs.data.local.LocalDataSource
import com.wajeez.wizardsandelixirs.data.local.model.toView
import com.wajeez.wizardsandelixirs.data.remote.RemoteDataSource
import com.wajeez.wizardsandelixirs.data.remote.model.toEntity
import com.wajeez.wizardsandelixirs.data.repository.model.WizardDetailsUIModel
import com.wajeez.wizardsandelixirs.data.repository.model.WizardUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WizardsRepoImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : WizardsRepo {

    override fun getWizards(): Flow<List<WizardUIModel>> =
        localDataSource.getAllWizards().map { wizardEntities -> wizardEntities.map { it.toView() } }

    override fun getWizardDetails(wizardId: String): Flow<WizardDetailsUIModel?> {
        return combine(
            localDataSource.getWizardById(wizardId),
            localDataSource.getElixirsForWizard(wizardId)
        ) { wizard, elixirs ->
            if (wizard != null) {
                WizardDetailsUIModel(
                    id = wizard.id,
                    firstName = wizard.firstName,
                    lastName = wizard.lastName,
                    elixirs = elixirs.map { it.name })
            } else {
                null
            }
        }
    }

    override suspend fun refreshWizards() {
        val newWizards = remoteDataSource.getWizards()
        val wizardEntities = newWizards.map { it.toEntity() }
        localDataSource.insertWizards(wizardEntities)
    }

    override suspend fun refreshWizard(id: String) {
        val newWizard = remoteDataSource.getWizardById(id = id)
        val newElixirs = newWizard.elixirs.map { it.toEntity(newWizard.id) }
        localDataSource.insertWizards(listOf(newWizard.toEntity()))
        localDataSource.insertElixirs(newElixirs)
    }
}