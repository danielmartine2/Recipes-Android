package com.danielmartinez.recipes.framework.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielmartinez.recipes.domain.Recipe
import com.danielmartinez.recipes.usecases.GetRecipes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getRecipes: GetRecipes): ViewModel() {

    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible: LiveData<Boolean> get() = _progressVisible

    private val _recipes = MutableLiveData<MutableList<Recipe>>()
    val recipes: LiveData<MutableList<Recipe>> get() = _recipes

    private val _goToRecipe = MutableLiveData<Recipe>()
    val goToRecipe: LiveData<Recipe> get() = _goToRecipe

    fun onCreate(){
        viewModelScope.launch {
            _progressVisible.value = true
            _recipes.value = getRecipes.invoke()
            _progressVisible.value = false
        }
    }

    fun onRecipeCLicked(recipe: Recipe){
        _goToRecipe.value = recipe
    }

    fun apolloFilter(recipeArray: MutableList<Recipe>, textSearch: String): MutableList<Recipe>{

        val searchLowerCase = textSearch.replace(" ", "").toLowerCase()
        val resultFilter = recipeArray.filter { index ->
            val keywordLowerCase = index.Name?.replace(" ", "")?.toLowerCase()
            keywordLowerCase?.contains(searchLowerCase) ?: false
        }

        return resultFilter.toMutableList()
    }
}