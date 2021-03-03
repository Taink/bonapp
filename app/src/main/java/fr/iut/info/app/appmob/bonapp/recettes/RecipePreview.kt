package fr.iut.info.app.appmob.bonapp.recettes

import android.media.Image

class RecipePreview {
    var nom: String
        private set
    var picture: String
        private set
    var isFavorite: Boolean
        private set

    constructor(
        nom: String,
        image: String,
        isFavorite: Boolean
    ) { //Premier constructeur ( à partir d'une recette)
        this.nom = nom
        picture = image
        this.isFavorite = isFavorite
    }

    constructor(nom: String, image: String) {
        this.nom = nom
        picture = image
        isFavorite = false
    }

}