package com.standardListAndDetailApp.shared.business

import com.standardListAndDetailApp.home_detail.business.HomeDetails
import com.standardListAndDetailApp.home_list.business.HomeListDetails
import com.standardListAndDetailApp.whish_list.business.WishListDetail
import kotlinx.coroutines.flow.Flow

interface HomesRepository {
    suspend fun refreshList()
    suspend fun refreshHomeItem(homeId: Int)
    suspend fun getHomes(): Result<Flow<List<HomeListDetails>>>
    suspend fun getHome(homeId: Int): Result<HomeDetails>
}