package fr.iut.info.app.appmob.bonapp.ui.widgets

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import fr.iut.info.app.appmob.bonapp.R
import fr.iut.info.app.appmob.bonapp.RecipeDetailsActivity
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

     open fun submitList(recipeList: List<Ingredient>) {
        items = recipeList
    }

    class IngredientViewHolder (
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {
        val recipeName = itemView.findViewById<TextView>(R.id.ingredient_name)

        fun bind(ingredient: Ingredient) {
            recipeName.setText(ingredient.nom)
        }
    }
}