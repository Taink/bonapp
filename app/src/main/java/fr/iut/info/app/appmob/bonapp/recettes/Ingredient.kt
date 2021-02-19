package fr.iut.info.app.appmob.bonapp.recettes

import fr.iut.info.app.appmob.bonapp.recettes.Recipe
import java.util.ArrayList

class Ingredient {
    var nom: String
    var quantity: Float
    var unite: String
    var recipes: ArrayList<Recipe>

    constructor(
        nom: String,
        quantity: Float,
        unite: String,
        recipes: ArrayList<Recipe>
    ) {
        this.nom = nom
        this.quantity = quantity
        this.unite = unite
        this.recipes = recipes
    }

    constructor(nom: String, quantity: Float, unite: String) {
        this.nom = nom
        this.quantity = quantity
        this.unite = unite
        recipes = ArrayList()
    }

    fun addRecipe(recipe: Recipe) {
        recipes.add(recipe)
    }

}