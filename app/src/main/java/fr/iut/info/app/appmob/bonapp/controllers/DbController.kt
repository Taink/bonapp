package fr.iut.info.app.appmob.bonapp.controllers

import android.content.Context
import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import fr.iut.info.app.appmob.bonapp.db.models.Step
import fr.iut.info.app.appmob.bonapp.db.room.AppDatabase
import fr.iut.info.app.appmob.bonapp.db.room.Favorite
import fr.iut.info.app.appmob.bonapp.recettes.Ingredient
import fr.iut.info.app.appmob.bonapp.recettes.Recipe
import fr.iut.info.app.appmob.bonapp.recettes.RecipePreview
import fr.iut.info.app.appmob.bonapp.services.inList
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


    fun getRecipesPreviews(onReceiveMapPrev: (mapListPreview: HashMap<String,RecipePreview>) -> Unit) {
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
                onReceiveMapPrev(mapListPreview)
            Log.i("firebase","Value ${recipes.value}")
        }   .addOnFailureListener{
            Log.e("firebase","Error while retrieving recipes",it)
        }
    }

    fun getRecipe(num:String, onReceiveRecipe: (recipe: Recipe) -> Unit) {
        database.child("recipies").child(num).get().addOnSuccessListener {recipe ->
            val result = recipe.getValue(fr.iut.info.app.appmob.bonapp.db.models.Recipe::class.java)
            val klef = recipe.key
            if (result != null && klef != null) {
                val recette = Recipe()
                recette.name = result.name
                recette.setKey(klef)
                recette.picture = result.image
                recette.ingredients = result.ingredients
                recette.steps = result.steps

                onReceiveRecipe(recette)
                Log.i("firebase","value retrieved : ${result.name}")
            }
        }.addOnFailureListener{
            Log.e("firebase","Error while retrieving recipe",it)
            }
    }

    fun getAllFavorite(onReceiveMapFav: (mapListFavPreview: HashMap<String, RecipePreview>) -> Unit) {
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
                onReceiveMapFav(mapListPreview)
            }.addOnFailureListener{
                Log.e("firebase","Error while retrieving recipes",it)
            }
    }

    fun setFavorite(num: String){
        val favDAO=localdb.favoriteDAO()
        val fav= Favorite(num)
        favDAO.insertAll(fav)
    }

    fun removeFavorite(num: String){
        val favDAO = localdb.favoriteDAO()
        val fav = Favorite(num)
        favDAO.delete(fav)
    }

    fun getIngredientList() : Array<Ingredient>?{
        return null
    }

    fun getSteps() : Array<Step>?{
        return null
    }
}