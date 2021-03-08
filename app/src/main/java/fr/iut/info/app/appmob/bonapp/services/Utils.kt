package fr.iut.info.app.appmob.bonapp.services

import fr.iut.info.app.appmob.bonapp.db.room.Favorite
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

fun inList(num:String, listfav:List<Favorite>) : Boolean{

    for (i in listfav.indices){
        if (num==listfav[i].id) {
            return true
        }
    }
    return false
}
