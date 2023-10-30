package com.standardListAndDetailApp.repository

import androidx.lifecycle.LiveData
import com.standardListAndDetailApp.database.HomesDatabase
import com.standardListAndDetailApp.database.DatabaseHome
import com.standardListAndDetailApp.network.ListingsServiceApi
import com.standardListAndDetailApp.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class HomesRepository constructor(private val database: HomesDatabase, private val listings : ListingsServiceApi) {

    suspend fun refreshList() {
        withContext(Dispatchers.IO) {
            val homes = listings.getHomesListing()
            database.homesDao.insertAll(homes.asDatabaseModel())
        }
    }

    val homes:LiveData<List<DatabaseHome>> = database.homesDao.getDatabaseProperties()

}