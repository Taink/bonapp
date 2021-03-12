package fr.iut.info.app.appmob.bonapp.ui.widgets

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.iut.info.app.appmob.bonapp.R
import fr.iut.info.app.appmob.bonapp.recettes.Ingredient

open class IngredientRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Ingredient> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return IngredientViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.ingredient_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is IngredientViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

     open fun submitList(ingredientList: List<Ingredient>) {
        items = ingredientList
    }

    class IngredientViewHolder (
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {
        val ingredientName = itemView.findViewById<TextView>(R.id.ingredient_name)

        fun bind(ingredient: Ingredient) {
            Log.e("XOAIZHFUIAHFUIO", ingredient.name.toString())
            ingredientName.setText(ingredient.name)
        }
    }
}