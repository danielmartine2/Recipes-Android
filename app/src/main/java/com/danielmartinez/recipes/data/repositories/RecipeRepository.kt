package com.danielmartinez.recipes.data.repositories

import com.danielmartinez.recipes.data.datasources.RemoteDatasource
import com.danielmartinez.recipes.domain.Recipe

class RecipeRepository (private val remoteDatasource: RemoteDatasource, private val apiKey: String) {
    suspend fun getRecipes(): MutableList<Recipe> = remoteDatasource.getRecipes(apiKey)
}