package fr.iut.info.app.appmob.bonapp.db.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = arrayOf(Favorite::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDAO(): FavoriteDAO

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase?= null
        fun getDatabase(context: Context):AppDatabase{

            return INSTANCE ?: synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "local_database"
                ).build()
                INSTANCE = instance

                return instance
            }
        }
    }
}
