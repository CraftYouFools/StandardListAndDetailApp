package com.standardListAndDetailApp.repository.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.RoomDatabase

@Dao
interface HomesDao {

    @Query("select * from DatabaseHome")
    fun getDatabaseHomes(): LiveData<List<DatabaseHome>>

    @Query("select * from DatabaseHome where id = :id")
    fun getDatabaseHome(id: Int): LiveData<DatabaseHome>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(homes: List<DatabaseHome>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHome(home: DatabaseHome)

}


@Database(entities = [DatabaseHome::class], version = 1)
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
