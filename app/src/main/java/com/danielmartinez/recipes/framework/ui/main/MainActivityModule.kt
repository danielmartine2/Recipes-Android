package com.danielmartinez.recipes.framework.ui.main

import com.danielmartinez.recipes.data.repositories.RecipeRepository
import com.danielmartinez.recipes.usecases.GetRecipes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class MainActivityModule {

    @Provides
    fun loadPopularRecipesProvider(repository: RecipeRepository): GetRecipes {
        return GetRecipes(repository)
    }
}