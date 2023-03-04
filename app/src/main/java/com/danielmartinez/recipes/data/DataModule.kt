package com.danielmartinez.recipes.data

import com.danielmartinez.recipes.data.datasources.RemoteDatasource
import com.danielmartinez.recipes.data.repositories.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun recipeRepositoryProvider(remoteDatasource: RemoteDatasource, @Named("apiKey") apiKey: String): RecipeRepository {
        return RecipeRepository(remoteDatasource, apiKey)
    }
}