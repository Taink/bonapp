package fr.iut.info.app.appmob.bonapp.recettes

import fr.iut.info.app.appmob.bonapp.db.models.Recipe
import fr.iut.info.app.appmob.bonapp.db.models.Step
import kotlin.collections.ArrayList

class Recipe{

    var name: String?
    var ingredients: HashMap<String,Ingredient>?
    var steps: HashMap<String,Step>?
    var picture: String?
    private var key:String?


    constructor(name: String?, ingredients: HashMap<String,Ingredient>?, steps: HashMap<String,Step>?, picture: String?, key: String?){
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

    fun addSteps(clef: String ,step: Step) {
        steps?.set(clef,step)
    }

    fun setKey(key:String){
        this.key= key
    }

    fun setAll(name: String?, ingredients: HashMap<String,Ingredient>?, steps: HashMap<String,Step>?, picture: String?, key: String?){
        this.name = name;
        this.ingredients=ingredients;
        this.steps=steps;
        this.picture=picture;
        this.key=key;
    }

    fun getKey(): String?{
        return this.key
    }


    fun addIngredient(clef:String ,ingredient: Ingredient) {
        ingredients?.set(clef,ingredient)
    }
}