package fr.iut.info.app.appmob.bonapp.db.models

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import fr.iut.info.app.appmob.bonapp.recettes.Ingredient
import fr.iut.info.app.appmob.bonapp.recettes.RecipePreview
import java.lang.reflect.Constructor
import java.util.*
import kotlin.collections.ArrayList

class Recipe{
    var name: String? = null
        private set
    var image: String? = null
        private set
    var ingredients: ArrayList<Ingredient>? =null
        private set
    var steps: ArrayList<Step>? = null
        private set

    private constructor(){}
    constructor(name:String?,image:String?,ingredients:ArrayList<Ingredient>,steps:ArrayList<Step>){
        this.name=name
        this.image=image
        this.ingredients=ingredients
        this.steps=steps
    }
}