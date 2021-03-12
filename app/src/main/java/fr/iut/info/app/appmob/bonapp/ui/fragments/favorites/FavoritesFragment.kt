package fr.iut.info.app.appmob.bonapp.ui.fragments.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.loader.content.AsyncTaskLoader
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.iut.info.app.appmob.bonapp.R
import fr.iut.info.app.appmob.bonapp.controllers.DbController
import fr.iut.info.app.appmob.bonapp.recettes.RecipePreview
import fr.iut.info.app.appmob.bonapp.ui.widgets.FavoriteRecipePreviewRecyclerAdapter

class FavoritesFragment : Fragment() {

    private lateinit var favoriteRecipeAdapter: FavoriteRecipePreviewRecyclerAdapter

    private fun initRecyclerView(root: View) {
        favoriteRecipeAdapter = FavoriteRecipePreviewRecyclerAdapter()
        root.findViewById<RecyclerView>(R.id.home_recyclerview).apply{
            layoutManager = LinearLayoutManager(this.context)
            adapter = favoriteRecipeAdapter
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

        dbController?.getAllFavorite {
            val recipeList = it.values.toList()
            favoriteRecipeAdapter.submitList(ArrayList(recipeList))
        }

        return root
    }
}