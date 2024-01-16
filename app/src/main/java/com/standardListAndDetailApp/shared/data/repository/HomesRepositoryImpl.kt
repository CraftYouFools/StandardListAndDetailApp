package com.standardListAndDetailApp.shared.data.repository

import android.util.Log
import com.standardListAndDetailApp.home_detail.business.HomeDetails
import com.standardListAndDetailApp.home_list.business.HomeListDetails
import com.standardListAndDetailApp.shared.business.HomesRepository
import com.standardListAndDetailApp.shared.business.Result
import com.standardListAndDetailApp.shared.data.repository.database.HomeEntity
import com.standardListAndDetailApp.shared.data.repository.database.HomesDatabase
import com.standardListAndDetailApp.shared.data.repository.mapper.asDatabaseModel
import com.standardListAndDetailApp.shared.data.repository.network.ListingsServiceApi
import com.standardListAndDetailApp.whish_list.business.WishListDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withContext
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
            Log.d(TAG, "insert or update homeId = $homeId")
            database.homesDao.insertHome(home.asDatabaseModel())
        }
    }

    override suspend fun getHomes(): Result<Flow<List<HomeListDetails>>> {
        return withContext(Dispatchers.IO) {
            Log.d(TAG, "Search for available homes")
            try {
                return@withContext Result.OnSuccess(database.homesDao.getDatabaseHomes().map { list ->
                    list.map { entity ->
                        with(entity) {
                            HomeListDetails(
                                id,
                                city,
                                area,
                                url,
                                price,
                                homeType,
                            )
                        }
                    }

                })
            }catch (e : Exception) {
                Log.d(TAG, e.toString())
                return@withContext Result.OnFailure(e)
            }
        }
    }

    override suspend fun getHome(homeId: Int): Result<HomeDetails> {
        return withContext(Dispatchers.IO) {
            Log.d(TAG, "Search for homeId = $homeId")

            val result: HomeEntity? = database.homesDao.getDatabaseHome(homeId)

            if(result==null){
                Log.d(TAG, "No data available for homeId = $homeId")
                return@withContext Result.OnFailure(Exception("No data available for homeId = $homeId"))
            }

            Log.d(TAG, "Data available for homeId = $homeId")
            return@withContext Result.OnSuccess(
                with(result) {
                    HomeDetails(
                        id,
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
            )
        }
    }

    companion object {
        private const val TAG = "HomesRepositoryImpl"
    }

}