package fr.iut.info.app.appmob.bonapp.ui.widgets

import fr.iut.info.app.appmob.bonapp.recettes.RecipePreview

class FavoriteRecipePreviewRecyclerAdapter: RecipePreviewRecyclerAdapter() {


    override fun submitList(recipeList: List<RecipePreview>) {

        val list = ArrayList<RecipePreview>()

        recipeList.forEach{
            if (it.isFavorite){
                list.add(it)
            }
        }

        super.submitList(list)
    }
}