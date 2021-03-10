package fr.iut.info.app.appmob.bonapp.ui.widgets

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import fr.iut.info.app.appmob.bonapp.R
import fr.iut.info.app.appmob.bonapp.RecipeDetailsActivity
import fr.iut.info.app.appmob.bonapp.recettes.RecipePreview

open class RecipePreviewRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<RecipePreview> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecipePreviewViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recipe_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is RecipePreviewViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

     open fun submitList(recipeList: List<RecipePreview>) {
        items = recipeList
    }

    class RecipePreviewViewHolder (
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {
        val recipeName = itemView.findViewById<TextView>(R.id.recipe_name)
        val recipeImage = itemView.findViewById<ImageView>(R.id.recipe_image)
        val recipeFavoriteState = itemView.findViewById<ImageView>(R.id.recipe_favoritestate)

        fun bind(recipePreview: RecipePreview) {
            recipeName.setText(recipePreview.name)

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_missing_image_black_24dp)
                .error(R.drawable.ic_missing_image_black_24dp)

            Glide.with(itemView.context)
                .load(recipePreview.picture)
                .into(recipeImage)

            if (recipePreview.isFavorite){
                recipeFavoriteState.setImageResource(R.drawable.ic_star_black_24dp)
            }


        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val detailsIntent = Intent(it.context, RecipeDetailsActivity::class.java).apply {
                        putExtra(RecipeDetailsActivity.RECIPE_ID, position)
                    }
                    startActivity(it.context, detailsIntent, null)
                }
            }

            recipeFavoriteState.setOnClickListener{
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {

                    Toast.makeText(itemView.context, position.toString(), Toast.LENGTH_SHORT).show()

                }
            }

        }
    }
}