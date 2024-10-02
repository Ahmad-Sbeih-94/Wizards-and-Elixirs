package com.wajeez.wizardsandelixirs.data.remote

import com.wajeez.wizardsandelixirs.data.remote.model.ElixirDetails
import com.wajeez.wizardsandelixirs.data.remote.model.Wizard
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("Wizards")
    suspend fun getWizards(): List<Wizard>

    @GET("Wizards/{id}")
    suspend fun getWizardById(@Path("id") id: String): Wizard

    @GET("Elixirs/{id}")
    suspend fun getElixirById(@Path("id") id: String): ElixirDetails
}