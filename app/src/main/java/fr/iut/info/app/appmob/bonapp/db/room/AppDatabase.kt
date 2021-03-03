package fr.iut.info.app.appmob.bonapp.db.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Favorite::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): FavoriteDAO
}
