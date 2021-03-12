package fr.iut.info.app.appmob.bonapp.recettes

import com.google.firebase.database.IgnoreExtraProperties
import fr.iut.info.app.appmob.bonapp.recettes.Recipe
import java.util.ArrayList

@IgnoreExtraProperties
class Ingredient {
    var name: String? = null
    var quantity: Float? = null
    var unite: String? = null
    var recipes: ArrayList<Recipe>? = null

    constructor(
        nom: String?,
        quantity: Float?,
        unite: String?,
        recipes: ArrayList<Recipe>?
    ) {
        this.name = nom
        this.quantity = quantity
        this.unite = unite
        this.recipes = recipes
    }

    constructor(){}
    constructor(nom: String?, quantity: Float?, unite: String?) {
        this.name = nom
        this.quantity = quantity
        this.unite = unite
        recipes = ArrayList()
    }

    fun addRecipe(recipe: Recipe) {
        recipes?.add(recipe)
    }

}