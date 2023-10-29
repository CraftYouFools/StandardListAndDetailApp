package com.standardListAndDetailApp.repository

import androidx.lifecycle.LiveData
import com.standardListAndDetailApp.database.HomesDatabase
import com.standardListAndDetailApp.database.DatabaseHome
import com.standardListAndDetailApp.network.GslNetwork
import com.standardListAndDetailApp.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class HomesRepository(private val database: HomesDatabase) {

    suspend fun refreshList() {
        withContext(Dispatchers.IO) {
            val homes = GslNetwork.listings.getHomesListing()
            database.homesDao.insertAll(homes.asDatabaseModel())
        }
    }

    val homes:LiveData<List<DatabaseHome>> = database.homesDao.getDatabaseProperties()

}