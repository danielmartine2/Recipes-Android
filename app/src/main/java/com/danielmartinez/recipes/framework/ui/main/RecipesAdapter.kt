package com.danielmartinez.recipes.framework.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danielmartinez.recipes.R
import com.danielmartinez.recipes.domain.Recipe
import com.danielmartinez.recipes.databinding.RecipeItemBinding
import com.danielmartinez.recipes.loadUrl

class RecipesAdapter(private val listener: (Recipe) -> Unit) : RecyclerView.Adapter<RecipesAdapter.ViewHolder>(){

    var recipes: MutableList<Recipe> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RecipeItemBinding.bind(view)
        fun bind(recipe: Recipe) = with(binding){
            tvName.text = recipe.Name
            tvDescription.text = recipe.Description
            if (recipe.urlImage.isNullOrBlank()){
                ivRecipe.setImageResource(R.drawable.ic_chef)
            }else{
                recipe.urlImage?.let { ivRecipe.loadUrl(it) }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val recipe = recipes[position]
        holder.bind(recipe)
        holder.itemView.setOnClickListener { listener(recipe) }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}