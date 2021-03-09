package fr.iut.info.app.appmob.bonapp.controllers

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import fr.iut.info.app.appmob.bonapp.db.models.Step
import fr.iut.info.app.appmob.bonapp.db.room.AppDatabase
import fr.iut.info.app.appmob.bonapp.db.room.Favorite
import fr.iut.info.app.appmob.bonapp.db.room.FavoriteDAO
import fr.iut.info.app.appmob.bonapp.recettes.Ingredient
import fr.iut.info.app.appmob.bonapp.recettes.Recipe
import fr.iut.info.app.appmob.bonapp.recettes.RecipePreview
import fr.iut.info.app.appmob.bonapp.services.inList
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class DbController {

    // [START declare_database_ref]
    private lateinit var database: DatabaseReference
    // [END declare_database_ref]
    private lateinit var localdb: AppDatabase

    constructor(context: Context){
        database = Firebase.database.reference
        localdb = AppDatabase.getDatabase(context)
    }


    fun getRecipesPreviews() : HashMap<String,RecipePreview> {
        val mapListPreview:HashMap<String,RecipePreview> = HashMap<String,RecipePreview>()
        val favDAO=localdb.favoriteDAO()
        val favoris:List<Favorite> = favDAO.getAll()
        database.child("recipies")
            .get()
            .addOnSuccessListener { recipes ->
                for (recipe in recipes.children){
                    if (recipe.key !=null){
                        if (!inList(recipe.key!!,favoris)){
                            var name = recipe.child("name").getValue<String>()
                            var image = recipe.child("picture").getValue<String>()
                            val prev = RecipePreview(name, image)
                            mapListPreview[recipe.key as String] = prev
                            Log.i("recette",prev.name)
                        }
                    }
                }
            Log.i("firebase","Value ${recipes.value}")
        }   .addOnFailureListener{
            Log.e("firebase","Error while retrieving recipes",it)
        }
        return mapListPreview
    }

    fun getRecipe(num:String) : Recipe?{
       var recetteFinal = Recipe()
        database.child("recipies").child(num).get().addOnSuccessListener {recipe ->
            /*val recettekey = recipe.key
            var name = recipe.child("name").getValue<String>()
            var image = recipe.child("picture").getValue<String>()
            val ingredients = ArrayList<Ingredient>()
            for (i in recipe.child("ingredients").children){
                val newIng = Ingredient(i.child("name").getValue<String>(),i.child("quantity").getValue<Float>(),i.child("unite").getValue<String>())
                if (i.key != null){
                    ingredients.add(newIng)
                }
            }
            val etapes = ArrayList<Step>()
            for (s in recipe.child("steps").children){
                if(s.key !=null){
                    val step = Step(s.getValue<String>())
                    etapes.add(step)
                }
            }
            Log.i("jpep","data retrieved : $recettekey")
            if (recettekey != null) {
                //recetteFinal.setAll(name,ingredients,etapes,image,recettekey)
            }*/
        }.addOnFailureListener{
            Log.e("firebase","Error while retrieving recipe",it)
            }
        return recetteFinal
    }

    fun getAllFavorite(): HashMap<String, RecipePreview> {
        val mapListPreview:HashMap<String,RecipePreview> = HashMap<String,RecipePreview>()
        val favDAO=localdb.favoriteDAO()
        val favoris:List<Favorite> = favDAO.getAll()
        database.child("recipies")
            .get()
            .addOnSuccessListener {recipes->
                for (recipe in recipes.children){
                    for (i in favoris.indices ) {
                        if (favoris[i].id == recipe.key) {
                            var name = recipe.child("name").getValue<String>()
                            var image = recipe.child("picture").getValue<String>()
                            var isFav = true
                            if (recipe.key != null && name !=null && image !=null) {
                                val prev = RecipePreview(name, image,isFav)
                                mapListPreview[recipe.key as String] = prev
                            }
                        }
                    }
                }
            }.addOnFailureListener{
                Log.e("firebase","Error while retrieving recipes",it)
            }
        return mapListPreview
    }

    fun setFavorite(num: String){
        val favDAO=localdb.favoriteDAO()
        val fav= Favorite(num)
        favDAO.insertAll(fav)
    }

    fun getIngredientList() : Array<Ingredient>?{
        return null
    }

    fun getSteps() : Array<Step>?{
        return null
    }
}