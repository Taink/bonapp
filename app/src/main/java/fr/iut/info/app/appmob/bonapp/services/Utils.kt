package fr.iut.info.app.appmob.bonapp.services

import fr.iut.info.app.appmob.bonapp.recettes.Ingredient
import java.util.ArrayList

fun ingredientsMultiplier(
    ingredients: ArrayList<Ingredient>,
    numberOfPeople: Int
): ArrayList<Ingredient> {
    for (i in ingredients) {
        i.quantity = i.quantity * numberOfPeople
    }
    return ingredients
}

