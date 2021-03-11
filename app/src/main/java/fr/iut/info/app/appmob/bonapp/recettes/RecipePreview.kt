package fr.iut.info.app.appmob.bonapp.recettes

import fr.iut.info.app.appmob.bonapp.db.room.Favorite

class RecipePreview {
    lateinit var name: String
        private set
    lateinit var picture: String
        private set
    var isFavorite: Boolean
        private set

    // Premier constructeur ( à partir d'une recette)
    constructor(
        name: String,
        image: String,
        isFavorite: Boolean
    ) {
        this.name = name
        picture = image
        this.isFavorite = isFavorite
    }

    constructor(name: String?, image: String?) {
        if (name != null) {
            this.name = name
        }
        else
        if (image != null) {
            picture = image
        }
        isFavorite = false
    }

    fun changeFavorite(){
        isFavorite =!isFavorite
    }

}