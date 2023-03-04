package com.danielmartinez.recipes.framework.data.datasources

import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeRemoteService {

    @GET("recipes")
    suspend fun listPopularRecipes(
        @Query("apiKey") apiKey: String
    ): RemoteResult
}