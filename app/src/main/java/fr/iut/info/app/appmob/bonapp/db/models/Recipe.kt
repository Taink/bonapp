package fr.iut.info.app.appmob.bonapp.db.models

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import fr.iut.info.app.appmob.bonapp.recettes.Ingredient
import fr.iut.info.app.appmob.bonapp.recettes.RecipePreview
import java.util.*

class Recipe(
    var name: String,
    var image: String,
    var ingredients: ArrayList<Ingredient>,
    var steps: ArrayList<Step>
)

