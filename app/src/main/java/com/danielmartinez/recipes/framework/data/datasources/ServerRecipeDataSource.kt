package com.danielmartinez.recipes.framework.data.datasources

import com.danielmartinez.recipes.data.datasources.RemoteDatasource
import com.danielmartinez.recipes.data.toDomainModel
import com.danielmartinez.recipes.domain.Recipe

class ServerRecipeDataSource(private val recipeRemoteService: RecipeRemoteService): RemoteDatasource {
    override suspend fun getRecipes(apikey: String): MutableList<Recipe> {
        val recipesResult = recipeRemoteService.listPopularRecipes(apikey)
        return recipesResult.results.map {it.toDomainModel()}.toMutableList()
    }
}