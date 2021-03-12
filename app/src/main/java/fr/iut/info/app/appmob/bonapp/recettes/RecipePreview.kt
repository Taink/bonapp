package fr.iut.info.app.appmob.bonapp.recettes

import fr.iut.info.app.appmob.bonapp.db.room.Favorite

class RecipePreview {
    lateinit var name: String
        private set
    lateinit var picture: String
        private set
    var isFavorite: Boolean
        private set
    lateinit var klef:String
        private set

    // Premier constructeur ( à partir d'une recette)
    constructor(
        name: String,
        image: String,
        klef:String,
        isFavorite: Boolean
    ) {
        this.name = name
        picture = image
        this.klef=klef
        this.isFavorite = isFavorite
    }

    constructor(name: String?, image: String?,key:String?){
        if (name != null) {
            this.name = name
        }
        if (image != null) {
            picture = image
        }
        if (key != null){
            this.klef=key
        }
        isFavorite = false
    }

    fun changeFavorite(){
        isFavorite =!isFavorite
    }

    override fun toString(): String {
        return "${this.name} : URL = ${this.picture}, FAVORITE = ${this.isFavorite}"
    }

}