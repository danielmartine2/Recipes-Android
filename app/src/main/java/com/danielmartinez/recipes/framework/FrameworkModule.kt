package com.danielmartinez.recipes.framework

import com.danielmartinez.recipes.data.datasources.RemoteDatasource
import com.danielmartinez.recipes.framework.data.datasources.RecipeRemoteService
import com.danielmartinez.recipes.framework.data.datasources.ServerRecipeDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FrameworkModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    fun baseUrlProvider(): String = "https://demo8607359.mockable.io/"

    @Provides
    @Singleton
    @Named("apiKey")
    fun apiKeyProvider(): String = ""

    @Provides
    @Singleton
    fun retrofitProvider(@Named("baseUrl") baseUrl:String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun recipeRemoteServiceProvider(retrofit: Retrofit): RecipeRemoteService = retrofit.create(RecipeRemoteService::class.java)

    @Provides

    fun remoteDataSourceProvider(recipeRemoteService: RecipeRemoteService): RemoteDatasource {
        return  ServerRecipeDataSource(recipeRemoteService)
    }
}