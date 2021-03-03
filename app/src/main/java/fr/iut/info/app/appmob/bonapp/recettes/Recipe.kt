package fr.iut.info.app.appmob.bonapp.recettes

import android.media.Image
import java.util.*

class Recipe(
    var nom: String,
    var ingredients: ArrayList<Ingredient>,
    var steps: ArrayList<String>,
    var picture: String
) {
    private var isFavorite = false

    fun createPreview(): RecipePreview {
        return RecipePreview(nom, picture, isFavorite)
    }

    fun addSteps(step: String) {
        steps.add(step)
    }

    fun addIngredient(ingredient: Ingredient) {
        ingredients.add(ingredient)
    }

    fun changeFavorite() {
        if (isFavorite) isFavorite = false else isFavorite = true
    }

}