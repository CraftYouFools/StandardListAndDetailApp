package com.standardListAndDetailApp.whish_list.data.repository

import android.util.Log
import com.standardListAndDetailApp.shared.business.Result
import com.standardListAndDetailApp.shared.data.repository.database.HomesDatabase
import com.standardListAndDetailApp.whish_list.business.WishListDetail
import com.standardListAndDetailApp.whish_list.business.WishListRepository
import com.standardListAndDetailApp.whish_list.data.repository.database.WishListEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WishListRepositoryImpl @Inject constructor(
    private val database: HomesDatabase,
) : WishListRepository {
    override suspend fun isInWishList(homeId: Int): Boolean {
        return withContext(Dispatchers.IO) {
            return@withContext database.homesDao.isHomeInWishList(homeId)!=null
        }

    }

    override suspend fun addToWishlist(homeId: Int) {
        withContext(Dispatchers.IO) {
            database.homesDao.insertHomeInWishList(WishListEntity(homeId))
        }
    }

    override suspend fun removeFromWishlist(homeId: Int) {
        withContext(Dispatchers.IO) {
            database.homesDao.removeHomeInWishList(homeId)
        }
    }

    override suspend fun getAllWishList(): Result<Flow<List<WishListDetail>>> {
        return withContext(Dispatchers.IO) {
            Log.d(TAG, "Search for wish list ")
            try {
                return@withContext Result.OnSuccess(
                    database.homesDao.getWishList().map { list ->
                        list.map { entity ->
                            with(entity) {
                                WishListDetail(
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

                        }
                    })
            }catch (e : Exception) {
                Log.d(TAG, e.toString())
                return@withContext Result.OnFailure(e)
            }
        }

    }

    companion object {
        private const val TAG = "WishListRepositoryImpl"
    }
}