package com.standardListAndDetailApp.shared.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ListenableWorker.Result.Success
import com.standardListAndDetailApp.home_detail.business.HomeDetails
import com.standardListAndDetailApp.shared.business.HomesRepository
import com.standardListAndDetailApp.shared.business.Result
import com.standardListAndDetailApp.shared.data.repository.database.HomesDatabase
import com.standardListAndDetailApp.shared.data.repository.database.HomeEntity
import com.standardListAndDetailApp.shared.data.repository.mapper.asDatabaseModel
import com.standardListAndDetailApp.shared.data.repository.network.ListingsServiceApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject


class HomesRepositoryImpl @Inject constructor(
    private val database: HomesDatabase,
    private val listings: ListingsServiceApi
) : HomesRepository {

    override suspend fun refreshList() {
        withContext(Dispatchers.IO) {
            val homes = listings.getHomesListing()
            database.homesDao.insertAll(homes.asDatabaseModel())
        }
    }

    override suspend fun refreshHomeItem(homeId: Int) {
        withContext(Dispatchers.IO) {
            val home = listings.getHomeDetail(homeId.toString())
            database.homesDao.insertHome(home.asDatabaseModel())
        }
    }

    override fun getHomes(): LiveData<List<HomeEntity>> {
        return database.homesDao.getDatabaseHomes()
    }

    override fun getHome(homeId: Int): Result<LiveData<HomeDetails>> {
        return Result.OnSuccess(database.homesDao.getDatabaseHome(homeId).map {
            with(it!!) {
                HomeDetails(
                    bedrooms,
                    city,
                    area,
                    url,
                    price,
                    professional,
                    homeType,
                    offerType,
                    rooms
                )
            }
        })
    }

    companion object {
        private const val TAG = "HomesRepositoryImpl"
    }

}