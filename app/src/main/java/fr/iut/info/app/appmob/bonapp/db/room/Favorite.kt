package fr.iut.info.app.appmob.bonapp.db.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(
    @PrimaryKey val id: String
)
