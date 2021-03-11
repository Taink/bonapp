package fr.iut.info.app.appmob.bonapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
//import androidx.preference.PreferenceFragmentCompat
import fr.iut.info.app.appmob.bonapp.db.models.Step
import fr.iut.info.app.appmob.bonapp.recettes.Ingredient
import fr.iut.info.app.appmob.bonapp.recettes.Recipe
import fr.iut.info.app.appmob.bonapp.ui.widgets.IngredientRecyclerAdapter
import fr.iut.info.app.appmob.bonapp.ui.widgets.StepRecyclerAdapter
import java.util.*
import kotlin.collections.ArrayList

class RecipeDetailsActivity : AppCompatActivity() {

    private lateinit var ingredientsAdapter: IngredientRecyclerAdapter
    private lateinit var stepsAdapter: StepRecyclerAdapter

    companion object {
        const val RECIPE_ID = "fr.iut.info.app.appmob.bonapp.RECIPE_ID"
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

    private fun initIngredientsAdapter() {
        ingredientsAdapter = IngredientRecyclerAdapter()
        findViewById<RecyclerView>(R.id.ingredient_list).apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = ingredientsAdapter
        }
    }

    private fun initStepsAdapter() {
        stepsAdapter = StepRecyclerAdapter()
        findViewById<RecyclerView>(R.id.step_list).apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = stepsAdapter
        }
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


        val recipeID = intent.getIntExtra(RECIPE_ID, -1)
        val recipe = fetchRecipe()

        this.title = getString(
            R.string.title_activity_recipe_details,
            recipe.name?.toLowerCase(Locale.ROOT)
        )

        val recipeNameView = findViewById<TextView>(R.id.details_recipe_name)
        recipeNameView.setText(recipe.name)
        val recipeImageView = findViewById<ImageView>(R.id.details_recipe_image)
        Glide.with(this)
            .load(recipe.picture)
            .into(recipeImageView)
        val peopleAmount = findViewById<TextView>(R.id.text_peopleamnt)
        peopleAmount.setText(getString(R.string.people_amount, 4))

        initIngredientsAdapter()
        recipe.ingredients?.let {
            ingredientsAdapter.submitList(it)
        }
        initStepsAdapter()
        recipe.steps?.let {
            stepsAdapter.submitList(it)
        }


        if (recipeID >= 0) {
            Toast.makeText(this, "Id de la recette : $recipeID", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Il y a eu une erreur", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater;
        inflater.inflate(R.menu.recipedeatailsmenu,menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.share -> {

            val myShare = Intent(Intent.ACTION_SEND)
            myShare.type = "text/plain"
            myShare.putExtra(Intent.EXTRA_TEXT,"Salut, je te recommande la recette: " + fetchRecipe().name + ". \nJe l'ai trouvé sur l'application BonApp' ! \nhttps://play.google.com/store")
            startActivity(Intent.createChooser(myShare,"Share"))

            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }


    /*class RecipeFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }*/
}
