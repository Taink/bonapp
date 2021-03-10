package fr.iut.info.app.appmob.bonapp.db.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Ingredient(
    val name: String?= null,
    val quantity: Float? = null,
    val unite: String?=null
    )