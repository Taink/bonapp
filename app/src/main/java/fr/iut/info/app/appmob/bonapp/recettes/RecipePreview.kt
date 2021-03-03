package fr.iut.info.app.appmob.bonapp.recettes

class RecipePreview {
    var name: String
        private set
    var picture: String
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

    constructor(name: String, image: String) {
        this.name = name
        picture = image
        isFavorite = false
    }

}