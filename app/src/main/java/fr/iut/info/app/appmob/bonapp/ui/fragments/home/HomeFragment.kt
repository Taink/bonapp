package fr.iut.info.app.appmob.bonapp.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.iut.info.app.appmob.bonapp.R
import fr.iut.info.app.appmob.bonapp.recettes.RecipePreview
import fr.iut.info.app.appmob.bonapp.ui.widgets.RecipePreviewRecyclerAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var recipeAdapter: RecipePreviewRecyclerAdapter
    private var randomNbr = 0

    private fun getRandomImage(): String {
        randomNbr++
        return "https://picsum.photos/500?random=$randomNbr"
    }

    private fun createRecipeList(): ArrayList<RecipePreview> {
        val names = resources.getStringArray(R.array.recipe_names)

        val list = ArrayList<RecipePreview>()
        list.add(
            RecipePreview(
                names.random(),
                getRandomImage(),
                false
            )
        )
        list.add(
            RecipePreview(
                names.random(),
                getRandomImage(),
                true
            )
        )
        list.add(
            RecipePreview(
                names.random(),
                getRandomImage(),
                false
            )
        )
        list.add(
            RecipePreview(
                names.random(),
                getRandomImage(),
                false
            )
        )
        list.add(
            RecipePreview(
                names.random(),
                getRandomImage(),
                true
            )
        )
        list.add(
            RecipePreview(
                names.random(),
                getRandomImage(),
                true
            )
        )
        list.add(
            RecipePreview(
                names.random(),
                getRandomImage(),
                true
            )
        )
        list.add(
            RecipePreview(
                names.random(),
                getRandomImage(),
                false
            )
        )
        list.add(
            RecipePreview(
                names.random(),
                getRandomImage(),
                false
            )
        )
        list.add(
            RecipePreview(
                names.random(),
                getRandomImage(),
                false
            )
        )
        return list
    }

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
        val recipeList = createRecipeList()
        recipeAdapter.submitList(recipeList)

        return root
    }
}