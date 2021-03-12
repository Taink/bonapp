package fr.iut.info.app.appmob.bonapp.ui.widgets

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
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

    protected var items: ArrayList<RecipePreview> = ArrayList()

    private var mListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onClick(position: Int, isChecked: Boolean)
    }

    open fun setOnItemClickListener(listener: OnItemClickListener?) {
        mListener = listener
    }

    open fun removeItem(position: Int, isChecked: Boolean){
        items[position].changeFavorite()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecipePreviewViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recipe_list_item, parent, false),
            object : OnItemClickListener {
                override fun onClick(position: Int, isChecked: Boolean) {
                    removeItem(position, isChecked)
                }
            }
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

    open fun submitList(recipeList: ArrayList<RecipePreview>) {
        items = recipeList
        notifyDataSetChanged()
    }

    class RecipePreviewViewHolder(
        itemView: View,
        listener: OnItemClickListener
    ): RecyclerView.ViewHolder(itemView) {
        val recipeName = itemView.findViewById<TextView>(R.id.recipe_name)
        val recipeImage = itemView.findViewById<ImageView>(R.id.recipe_image)
        val recipeFavoriteState = itemView.findViewById<CheckBox>(R.id.recipe_favorite)

        fun bind(recipePreview: RecipePreview) {
            recipeName.setText(recipePreview.name)


            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_missing_image_black_24dp)
                .error(R.drawable.ic_missing_image_black_24dp)

            Glide.with(itemView.context)
                .load(recipePreview.picture)
                .into(recipeImage)

            if (recipePreview.isFavorite) {
                recipeFavoriteState.isChecked = true
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

            recipeFavoriteState.setOnCheckedChangeListener { checkBox, isChecked ->
                val position = adapterPosition

                Log.i("Favorite","$position est maintenant ${if (isChecked) "favorite" else "non favorite"}")

                listener.onClick(position,isChecked)
            }
        }
    }
}