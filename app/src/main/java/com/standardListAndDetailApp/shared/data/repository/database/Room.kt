package com.standardListAndDetailApp.shared.data.repository.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.RoomDatabase
import kotlin.jvm.Throws

@Dao
interface HomesDao {

    @Query("select * from HomeEntity")
    fun getDatabaseHomes(): LiveData<List<HomeEntity>>

    @Query("select * from HomeEntity where id = :id")
    fun getDatabaseHome(id: Int): LiveData<HomeEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(homes: List<HomeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHome(home: HomeEntity)

}


@Database(entities = [HomeEntity::class], version = 1)
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
