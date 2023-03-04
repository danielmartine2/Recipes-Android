package com.danielmartinez.recipes.data

import com.danielmartinez.recipes.domain.Recipe
import com.danielmartinez.recipes.framework.data.datasources.ServiceRecipe

fun ServiceRecipe.toDomainModel(): Recipe =
    Recipe(
        Name,
        location,
        urlImage,
        url,
        Description,
        Author,
        Ingredients,
        Method
    )