package com.standardListAndDetailApp.shared.business

import androidx.lifecycle.LiveData
import com.standardListAndDetailApp.home_detail.business.HomeDetails
import com.standardListAndDetailApp.shared.data.repository.database.HomeEntity

interface HomesRepository {
    suspend fun refreshList()
    suspend fun refreshHomeItem(homeId: Int)

    fun getHomes(): LiveData<List<HomeEntity>>

    fun getHome(homeId: Int): Result<LiveData<HomeDetails>>
}