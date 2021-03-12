package fr.iut.info.app.appmob.bonapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.iut.info.app.appmob.bonapp.controllers.DbController
//import androidx.preference.PreferenceFragmentCompat
import fr.iut.info.app.appmob.bonapp.db.models.Step
import fr.iut.info.app.appmob.bonapp.recettes.Ingredient
import fr.iut.info.app.appmob.bonapp.recettes.Recipe
import fr.iut.info.app.appmob.bonapp.ui.widgets.IngredientRecyclerAdapter
import fr.iut.info.app.appmob.bonapp.ui.widgets.StepRecyclerAdapter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class RecipeDetailsActivity : AppCompatActivity() {

    private lateinit var ingredientsAdapter: IngredientRecyclerAdapter
    private lateinit var stepsAdapter: StepRecyclerAdapter
    private var recipeName = "[...]"

    companion object {
        const val RECIPE_ID = "fr.iut.info.app.appmob.bonapp.RECIPE_ID"
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


        val recipeID = intent.getStringExtra(RECIPE_ID)
        val dbController = DbController(this)

        if (recipeID != null) {
            dbController.getRecipe(recipeID) {recipe ->
                this.recipeName = recipe.name.toString()

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
                val ingredientsList = ArrayList<Ingredient>()
                recipe.ingredients?.values?.let { ingredientsList.addAll(it) }
                ingredientsAdapter.submitList(ingredientsList)
                initStepsAdapter()
                val stepsList = ArrayList<Step>()
                recipe.steps?.values?.let { stepsList.addAll(it) }
                stepsAdapter.submitList(stepsList)
            }
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
            myShare.putExtra(Intent.EXTRA_TEXT,
                "Salut, je te recommande la recette: $recipeName. \nJe l'ai trouvé sur l'application BonApp' ! \nhttps://play.google.com/store"
            )
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
