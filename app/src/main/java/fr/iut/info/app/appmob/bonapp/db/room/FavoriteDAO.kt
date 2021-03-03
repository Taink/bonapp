package fr.iut.info.app.appmob.bonapp.db.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDAO {
    @Query("SELECT * FROM favorite")
    fun getAll(): List<Favorite>

    @Query("SELECT * FROM favorite WHERE id IN (:favoriteIds)")
    fun loadAllByIds(favoriteIds: IntArray): List<Favorite>

    @Insert
    fun insertAll(vararg favorites: Favorite)

    @Delete
    fun delete(user: Favorite)
}
