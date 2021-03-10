package fr.iut.info.app.appmob.bonapp.recettes

import fr.iut.info.app.appmob.bonapp.db.models.Step
import kotlin.collections.ArrayList

class Recipe{

    var name: String?
    var ingredients: ArrayList<Ingredient>?
    var steps: ArrayList<Step>?
    var picture: String?
    var key:String?


    constructor(name: String?, ingredients: ArrayList<Ingredient>?, steps: ArrayList<Step>?, picture: String?, key: String?){
        this.name=name
        this.ingredients=ingredients;
        this.steps=steps;
        this.picture=picture;
        this.key=key;
    }
    constructor() : this(null,null,null,null,null){}

    fun createPreview(): RecipePreview? {

        //return RecipePreview(name, picture, isFavorite)
        return null
    }

    fun addSteps(step: Step) {
        steps?.add(step)
    }

    fun setAll(name: String?, ingredients: ArrayList<Ingredient>?, steps: ArrayList<Step>?, picture: String?, key: String?){
        this.name = name;
        this.ingredients=ingredients;
        this.steps=steps;
        this.picture=picture;
        this.key=key;
    }



    fun addIngredient(ingredient: Ingredient) {
        ingredients?.add(ingredient)
    }
}