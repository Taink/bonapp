package fr.iut.info.app.appmob.bonapp.controlers

import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import fr.iut.info.app.appmob.bonapp.recettes.Ingredient
import fr.iut.info.app.appmob.bonapp.recettes.Recipe
import fr.iut.info.app.appmob.bonapp.recettes.RecipePreview
import java.util.*


class DbControler {

    // [START declare_database_ref]
    private lateinit var database: DatabaseReference
    // [END declare_database_ref]

    init {
        database = Firebase.database.reference
    }

    fun getRecipesPreviews() : Array<RecipePreview>? {
        //GenericTypeIndicator<List<Recipe>> recettes = new GenericTypeIndicator<List<Recipe>>(){};
        val ti = object : GenericTypeIndicator<List<fr.iut.info.app.appmob.bonapp.db.models.Recipe>>(){}
        database.child("recipies")
            .get()
            .addOnSuccessListener { recipes ->
                for (recipe in recipes.children){

                    var r = recipe.getValue(ti)
                }
            Log.i("firebase","Value ${recipes.value}")
        }   .addOnFailureListener{
            Log.e("firebase","Error while retrieving recipes",it)
        }


        return null
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