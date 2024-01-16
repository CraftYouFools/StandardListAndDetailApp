package com.standardListAndDetailApp.shared.data.repository.network

import com.standardListAndDetailApp.shared.data.repository.mapper.HomeItem
import com.standardListAndDetailApp.shared.data.repository.mapper.NetWorkHomeListItemContainer
import retrofit2.http.GET
import retrofit2.http.Path

interface ListingsServiceApi {
    @GET("listings.json")
    suspend fun getHomesListing(): NetWorkHomeListItemContainer

    @GET("listings/{homeId}.json")
    suspend fun getHomeDetail(@Path("homeId") homeId: String?): HomeItem

}