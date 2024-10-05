package com.wajeez.wizardsandelixirs.di

import com.wajeez.wizardsandelixirs.data.repository.WizardsRepo
import com.wajeez.wizardsandelixirs.data.repository.WizardsRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ReposModule {
    @Binds
    abstract fun bindWizardsRepo(
        wizardsRepoImpl: WizardsRepoImpl
    ): WizardsRepo
}