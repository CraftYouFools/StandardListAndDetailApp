package com.standardListAndDetailApp.repository

import androidx.lifecycle.LiveData
import com.standardListAndDetailApp.repository.database.HomesDatabase
import com.standardListAndDetailApp.repository.database.DatabaseHome
import com.standardListAndDetailApp.repository.mapper.asDatabaseModel
import com.standardListAndDetailApp.repository.network.ListingsServiceApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class HomesRepository @Inject constructor(
    private val database: HomesDatabase,
    private val listings: ListingsServiceApi
) {

    suspend fun refreshList() {
        withContext(Dispatchers.IO) {
            val homes = listings.getHomesListing()
            database.homesDao.insertAll(homes.asDatabaseModel())
        }
    }

    suspend fun refreshHomeItem(homeId: Int) {
        withContext(Dispatchers.IO) {
            val home = listings.getHomeDetail(homeId.toString())
            database.homesDao.insertHome(home.asDatabaseModel())
        }
    }

    val homes:LiveData<List<DatabaseHome>> = database.homesDao.getDatabaseHomes()

    fun getHome(homeId : Int) :LiveData<DatabaseHome> = database.homesDao.getDatabaseHome(homeId)

}