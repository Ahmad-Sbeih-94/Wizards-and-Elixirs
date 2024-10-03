package com.wajeez.wizardsandelixirs.di

import android.content.Context
import androidx.room.Room
import com.wajeez.wizardsandelixirs.data.local.AppDatabase
import com.wajeez.wizardsandelixirs.data.local.ElixirDao
import com.wajeez.wizardsandelixirs.data.local.ElixirDetailsDao
import com.wajeez.wizardsandelixirs.data.local.WizardDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "wizard_database"
        ).build()
    }

    @Provides
    fun provideWizardDao(database: AppDatabase): WizardDao {
        return database.wizardDao()
    }

    @Provides
    fun provideElixirDao(database: AppDatabase): ElixirDao {
        return database.elixirDao()
    }

    @Provides
    fun provideElixirDetailsDao(database: AppDatabase): ElixirDetailsDao {
        return database.elixirDetailsDao()
    }
}