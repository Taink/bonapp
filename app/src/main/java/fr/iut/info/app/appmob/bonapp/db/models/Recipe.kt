package fr.iut.info.app.appmob.bonapp.db.models

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties
import fr.iut.info.app.appmob.bonapp.recettes.Ingredient
import fr.iut.info.app.appmob.bonapp.recettes.RecipePreview
import java.lang.reflect.Constructor
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@IgnoreExtraProperties
class Recipe(
    val ingredients: HashMap<String,Ingredient>? =null,
    val name: String? = null,
    val image: String? = null,
    val steps: HashMap<String,Step>? = null
    )