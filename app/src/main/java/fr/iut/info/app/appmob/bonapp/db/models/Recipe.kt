package fr.iut.info.app.appmob.bonapp.db.models

import fr.iut.info.app.appmob.bonapp.recettes.Ingredient
import java.util.*

class Recipe(
    var name: String,
    var image: String,
    var ingredients: ArrayList<Ingredient>,
    var steps: ArrayList<Step>
)