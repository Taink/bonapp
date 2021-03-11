package fr.iut.info.app.appmob.bonapp.db.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Step(
    val text: String? = null,
    val number: Int? = null,
    )