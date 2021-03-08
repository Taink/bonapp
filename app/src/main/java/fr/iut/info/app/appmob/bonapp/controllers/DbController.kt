package fr.iut.info.app.appmob.bonapp.controllers

import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import fr.iut.info.app.appmob.bonapp.recettes.Ingredient
import fr.iut.info.app.appmob.bonapp.recettes.Recipe
import fr.iut.info.app.appmob.bonapp.recettes.RecipePreview
import java.util.*
import kotlin.collections.HashMap


class DbController {

    // [START declare_database_ref]
    private lateinit var database: DatabaseReference
    // [END declare_database_ref]

    constructor(){
        database = Firebase.database.reference
    }


    fun getRecipesPreviews() : HashMap<String,RecipePreview> {
        //val listPreview = arrayListOf<RecipePreview>()
        val mapListPreview:HashMap<String,RecipePreview> = HashMap<String,RecipePreview>()
        database.child("recipies")
            .get()
            .addOnSuccessListener { recipes ->
                for (recipe in recipes.children){

                    var name = recipe.child("name").getValue<String>()
                    var image = recipe.child("picture").getValue<String>()
                    //val recette = recipe.getValue<fr.iut.info.app.appmob.bonapp.db.models.Recipe>()
                    val prev = RecipePreview(name, image)
                    if (recipe.key != null){
                        mapListPreview[recipe.key as String] = prev
                    }
                    Log.i("recette",prev.name)
                }
            Log.i("firebase","Value ${recipes.value}")
        }   .addOnFailureListener{
            Log.e("firebase","Error while retrieving recipes",it)
        }
        return mapListPreview
    }

    fun getRecipe() : Recipe?{

        return null
    }

    fun getIngredientList() : Array<Ingredient>?{

        return null
    }

    fun getSteps() : Array<Objects>?{

        return null
    }
}