package com.wajeez.wizardsandelixirs.data.remote

import com.wajeez.wizardsandelixirs.data.remote.model.ElixirDetails
import com.wajeez.wizardsandelixirs.data.remote.model.Wizard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getWizards(): List<Wizard> = withContext(Dispatchers.IO) { apiService.getWizards() }

    suspend fun getWizardById(id: String): Wizard = withContext(Dispatchers.IO) { apiService.getWizardById(id = id) }

    suspend fun getElixirById(id: String): ElixirDetails = withContext(Dispatchers.IO) { apiService.getElixirById(id) }
}