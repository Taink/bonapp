package fr.iut.info.app.appmob.bonapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat

class RecipeDetailsActivity : AppCompatActivity() {

    companion object {
        const val RECIPE_POSITION = "fr.iut.info.app.appmob.bonapp.RECIPE_NUMBER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_details_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.recipe, RecipeFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_navigate_before_black_24dp)
        val recipeID = intent.getIntExtra(RECIPE_POSITION, -1)
        if (recipeID >= 0) {
            Toast.makeText(this, "Id de la recette : $recipeID", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Il y a eu une erreur", Toast.LENGTH_SHORT).show()
        }
    }

    class RecipeFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }
}