package com.standardListAndDetailApp.shared.data.repository.database

import android.content.Context
import androidx.room.*
import androidx.room.RoomDatabase
import com.standardListAndDetailApp.whish_list.data.repository.database.WishListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HomesDao {

    @Query("select * from HomeEntity")
    fun getDatabaseHomes(): Flow<List<HomeEntity>>

    @Query("SELECT * FROM HomeEntity WHERE id IN (SELECT homeId FROM WishListEntity ORDER BY homeId DESC)")
    fun getWishList():Flow<List<HomeEntity>>

    @Query("select * from HomeEntity WHERE id=:idList")
    fun getDatabaseHomes(idList:List<Int>): Flow<List<HomeEntity>>

    @Query("select * from HomeEntity where id = :id")
    suspend fun getDatabaseHome(id: Int): HomeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(homes: List<HomeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHome(home: HomeEntity)

    @Query("SELECT * FROM WishListEntity WHERE homeId=:homeId ")
    fun isHomeInWishList(homeId: Int): WishListEntity?

    @Query("DELETE FROM WishListEntity WHERE homeId=:homeId ")
    fun removeHomeInWishList(homeId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHomeInWishList(home: WishListEntity)

    @Delete
    fun removeHomeInWishList(home: WishListEntity)
}


@Database(entities = [HomeEntity::class,WishListEntity::class], version = 1)
abstract class HomesDatabase : RoomDatabase() {
    abstract val homesDao: HomesDao
}

private lateinit var INSTANCE: HomesDatabase

fun getDatabase(context: Context): HomesDatabase {
    synchronized(HomesDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                HomesDatabase::class.java, "homes"
            ).build()
        }
    }
    return INSTANCE
}
