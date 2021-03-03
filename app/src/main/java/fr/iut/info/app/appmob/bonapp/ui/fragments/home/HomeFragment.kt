package fr.iut.info.app.appmob.bonapp.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import fr.iut.info.app.appmob.bonapp.R
import fr.iut.info.app.appmob.bonapp.recettes.RecipePreview

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    fun createRecipeList(): ArrayList<RecipePreview> {
        val names = resources.getStringArray(R.array.recipe_names)

        val list = ArrayList<RecipePreview>()
        list.add(
            RecipePreview(
                names.random(),
                null,
                false
            )
        )
        list.add(
            RecipePreview(
                names.random(),
                null,
                false
            )
        )
        list.add(
            RecipePreview(
                names.random(),
                null,
                false
            )
        )
        list.add(
            RecipePreview(
                names.random(),
                null,
                false
            )
        )
        list.add(
            RecipePreview(
                names.random(),
                null,
                false
            )
        )
        return list
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        @Suppress("UNUSED_VARIABLE")
        var recipeList = createRecipeList()

        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}