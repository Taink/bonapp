package fr.iut.info.app.appmob.bonapp.ui.widgets

import android.widget.ImageView
import fr.iut.info.app.appmob.bonapp.recettes.RecipePreview

class FavoriteRecipePreviewRecyclerAdapter: RecipePreviewRecyclerAdapter() {


    override fun submitList(recipeList: ArrayList<RecipePreview>) {

        val list = ArrayList<RecipePreview>()


        recipeList.forEach{
            if (it.isFavorite){
                list.add(it)
            }
        }

        super.submitList(list)
    }

    override fun removeItem(position: Int, isChecked: Boolean){
        super.removeItem(position, isChecked)

        if(!isChecked){
            notifyItemRemoved(position)
            items.removeAt(position)

        }

    }

}