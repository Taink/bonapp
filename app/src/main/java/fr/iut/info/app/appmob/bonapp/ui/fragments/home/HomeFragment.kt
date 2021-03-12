package fr.iut.info.app.appmob.bonapp.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.iut.info.app.appmob.bonapp.R
import fr.iut.info.app.appmob.bonapp.controllers.DbController
import fr.iut.info.app.appmob.bonapp.recettes.RecipePreview
import fr.iut.info.app.appmob.bonapp.ui.widgets.RecipePreviewRecyclerAdapter

class HomeFragment : Fragment() {

    private lateinit var recipeAdapter: RecipePreviewRecyclerAdapter

    private fun initRecyclerView(root: View) {
        recipeAdapter = RecipePreviewRecyclerAdapter()
        root.findViewById<RecyclerView>(R.id.home_recyclerview).apply{
            layoutManager = LinearLayoutManager(this.context)
            adapter = recipeAdapter
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        initRecyclerView(root)
        val dbController = this.context?.let { DbController(it) }

        dbController?.getRecipesPreviews {
            val recipeList = it.values.toList()
            recipeAdapter.submitList(ArrayList(recipeList))
        }

        return root
    }
}