package com.wajeez.wizardsandelixirs.data.repository

import com.wajeez.wizardsandelixirs.data.local.LocalDataSource
import com.wajeez.wizardsandelixirs.data.local.model.toView
import com.wajeez.wizardsandelixirs.data.remote.RemoteDataSource
import com.wajeez.wizardsandelixirs.data.remote.model.toEntity
import com.wajeez.wizardsandelixirs.data.repository.model.ElixirDetailsUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ElixirsRepoImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ElixirsRepo {

    override fun getElixirDetailsById(elixirId: String): Flow<ElixirDetailsUIModel?> =
        localDataSource.getElixirDetailsById(elixirId).map { elixirDetails -> elixirDetails?.toView() }

    override suspend fun refreshElixirDetail(id: String) {
        val elixirDetails = remoteDataSource.getElixirById(id = id)
        localDataSource.insertElixirDetails(elixirDetails.toEntity())
    }
}