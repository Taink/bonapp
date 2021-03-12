package fr.iut.info.app.appmob.bonapp.ui.fragments.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecipesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Coming soon"
    }
    val text: LiveData<String> = _text
}