package com.danielmartinez.recipes.framework.ui.RacipeDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danielmartinez.recipes.R
import com.danielmartinez.recipes.databinding.IngredientsItemBinding

class ListStringAdapter: RecyclerView.Adapter<ListStringAdapter.ViewHolder>(){

    var listString: List<String> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = IngredientsItemBinding.bind(view)
        fun bind(ingredient: String) = with(binding){
            tvIngredients.text = ingredient
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ingredients_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredient = listString[position]
        holder.bind(ingredient)
    }

    override fun getItemCount(): Int {
        return listString.size
    }
}