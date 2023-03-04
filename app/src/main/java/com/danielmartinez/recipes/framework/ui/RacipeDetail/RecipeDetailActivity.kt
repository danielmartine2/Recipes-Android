package com.danielmartinez.recipes.framework.ui.RacipeDetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.danielmartinez.recipes.databinding.ActivityRecipeDetailBinding
import com.danielmartinez.recipes.domain.Location
import com.danielmartinez.recipes.domain.Recipe
import com.danielmartinez.recipes.framework.ui.mapa.MapsActivity
import com.danielmartinez.recipes.loadUrl

private const val EXTRA_DETAIL = "Detail"

class RecipeDetailActivity : AppCompatActivity() {

    private var recipeData: Recipe? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recipeData = intent?.getSerializableExtra(EXTRA_DETAIL) as Recipe?

        binding.fbRecipeMap.setOnClickListener {navigateToMaps(recipeData)}
        binding.tbRecipeDetailToolbar.setNavigationOnClickListener {this.onBackPressed()}

        val ingredientsAdapter = ListStringAdapter()
        binding.rvIngredients.adapter = ingredientsAdapter

        val methodAdapter = ListStringAdapter()
        binding.rvMethod.adapter = methodAdapter
        recipeData?.let {
            binding.tbRecipeDetailToolbar.title = it.Name
            it.urlImage?.let { url -> binding.ivRecipeDetailImage.loadUrl(url) }
            binding.tvRecipeDetailDescription.text = it.Description
            ingredientsAdapter.listString = it.Ingredients
            methodAdapter.listString = it.Method
        }
    }

    private fun navigateToMaps(recipe: Recipe?) {
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("Location", recipe)
        startActivity(intent)
    }
}