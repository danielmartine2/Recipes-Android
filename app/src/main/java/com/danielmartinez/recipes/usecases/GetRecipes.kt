package com.danielmartinez.recipes.usecases

import com.danielmartinez.recipes.data.repositories.RecipeRepository
import com.danielmartinez.recipes.domain.Recipe
import javax.inject.Inject

class GetRecipes @Inject constructor(private val repository: RecipeRepository) {

    suspend fun invoke(): MutableList<Recipe> = repository.getRecipes()
}