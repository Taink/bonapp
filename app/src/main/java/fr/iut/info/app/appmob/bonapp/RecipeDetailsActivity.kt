package fr.iut.info.app.appmob.bonapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
//import androidx.preference.PreferenceFragmentCompat
import fr.iut.info.app.appmob.bonapp.db.models.Step
import fr.iut.info.app.appmob.bonapp.recettes.Ingredient
import fr.iut.info.app.appmob.bonapp.recettes.Recipe

class RecipeDetailsActivity : AppCompatActivity() {

    companion object {
        const val RECIPE_POSITION = "fr.iut.info.app.appmob.bonapp.RECIPE_NUMBER"
    }

    private fun fetchRecipe(): Recipe {
        val ingredientList = ArrayList<Ingredient>()
        ingredientList.addAll(listOf(
            Ingredient("rôti de porc", 1.toFloat(), "kg"),
            Ingredient("pommes de terre nouvelles", 800.toFloat(), "g"),
            Ingredient("kakis", 3.toFloat(), ""),
            Ingredient("échalottes", 2.toFloat(), ""),
            Ingredient("bouillon (fond de veau dilué dans de l'eau)", 20.toFloat(), "cl"),
            Ingredient("romarin", 0.toFloat(), ""),
            Ingredient("huile d'olive", 0.toFloat(), "")
        ))

        val stepList = ArrayList<Step>()
        stepList.addAll(listOf(
            Step("Lavez les pommes de terre et versez-les dans un plat adapté au four." +
                    " Badigeonnez-les d'huile, de sel et de romarin. Mélangez bien avec vos mains" +
                    " et laissez de côté"),
            Step("Faites saisir le rôti sur chaque face dans une poêle. Enfournez pendant une" +
                    " heure et demi à 180°. Au bout d'une demie heure de cuisson, ajouter le plat de" +
                    " pommes de terre au four. Mélangez-les plusieurs fois pendant la cuisson pour" +
                    " qu'elles dorent de tous les côtés.")
        ))

        return Recipe(
            "Rôti de porc, sauce aux kakis, pommes de terre au four",
            ingredientList,
            stepList,
            "https://i.imgur.com/98qnK0J.png",
            "11111"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_details_activity)
        /*if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.recipe_head, RecipeFragment())
                .commit()
        }*/
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_navigate_before_black_24dp)
        val recipeID = intent.getIntExtra(RECIPE_POSITION, -1)
        val recipe = fetchRecipe()

        val recipeNameView = findViewById<TextView>(R.id.details_recipe_name)
        recipeNameView.setText(recipe.name)
        val recipeImageView = findViewById<ImageView>(R.id.details_recipe_image)
        Glide.with(this)
            .load(recipe.picture)
            .into(recipeImageView)


        if (recipeID >= 0) {
            Toast.makeText(this, "Id de la recette : $recipeID", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Il y a eu une erreur", Toast.LENGTH_SHORT).show()
        }
    }

    /*class RecipeFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }*/
}