package com.danielmartinez.recipes.framework.ui.main

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.danielmartinez.recipes.R
import com.danielmartinez.recipes.databinding.ActivityMainBinding
import com.danielmartinez.recipes.domain.Recipe
import com.danielmartinez.recipes.framework.ui.RacipeDetail.RecipeDetailActivity
import com.danielmartinez.recipes.setVisible
import dagger.hilt.android.AndroidEntryPoint

private const val EXTRA_DETAIL = "Detail"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private var checkGoToDetail = false
    private lateinit var recipesAdapter:RecipesAdapter
    lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recipesAdapter = RecipesAdapter(viewModel::onRecipeCLicked)
        binding.recycler.adapter = recipesAdapter

        viewModel.recipes.observe(this) { recipes ->
            recipesAdapter.recipes = recipes
        }

        viewModel.progressVisible.observe(this){
            binding.progress.setVisible(it)
        }

        viewModel.goToRecipe.observe(this){
            navigateToDetail(it)
        }

        viewModel.onCreate()
    }

    private fun navigateToDetail(recipe: Recipe) {
        if (!checkGoToDetail) {
            checkGoToDetail = true
            val intent = Intent(this, RecipeDetailActivity::class.java)
            intent.putExtra(EXTRA_DETAIL, recipe)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        checkGoToDetail = false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val searchItem = menu?.findItem(R.id.menu_search)

        searchItem?.let {
            searchView = searchItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText.isNullOrBlank()){

                        recipesAdapter.recipes = viewModel.recipes.value ?: mutableListOf()
                    }else{
                        val apolloFilter = viewModel.apolloFilter(viewModel.recipes.value ?: mutableListOf(), newText)
                        recipesAdapter.recipes = apolloFilter
                    }
                    return true
                }
            })
            searchView.isSubmitButtonEnabled
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

}