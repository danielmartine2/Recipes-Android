package com.danielmartinez.recipes.data.datasources

import com.danielmartinez.recipes.domain.Recipe

interface RemoteDatasource {
    suspend fun getRecipes(apikey: String): MutableList<Recipe>
}