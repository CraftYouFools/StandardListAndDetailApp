package com.standardListAndDetailApp.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.RoomDatabase

@Dao
interface HomesDao {

    @Query("select * from databaseHome")
    fun getDatabaseProperties(): LiveData<List<databaseHome>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(properties: List<databaseHome>)

}


@Database(entities = [databaseHome::class], version = 1)
abstract class HomesDatabase: RoomDatabase() {
    abstract val homesDao: HomesDao
}

private lateinit var INSTANCE: HomesDatabase

fun getDatabase(context: Context): HomesDatabase {
    synchronized(HomesDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                HomesDatabase::class.java, "homes").build()
        }
    }
    return INSTANCE
}
