package com.wajeez.wizardsandelixirs.data.repository

import com.wajeez.wizardsandelixirs.data.repository.model.ElixirDetailsUIModel
import kotlinx.coroutines.flow.Flow

interface ElixirsRepo {
    fun getElixirDetailsById(elixirId: String): Flow<ElixirDetailsUIModel?>
    suspend fun refreshElixirDetail(id: String)
}