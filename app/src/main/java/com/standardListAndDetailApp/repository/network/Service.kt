package com.standardListAndDetailApp.repository.network

import com.standardListAndDetailApp.repository.mapper.NetWorkHomeItemContainer
import com.standardListAndDetailApp.repository.mapper.NetWorkHomeListItemContainer
import retrofit2.http.GET
import retrofit2.http.Path

interface ListingsServiceApi {
    @GET("listings.json")
    suspend fun getHomesListing(): NetWorkHomeListItemContainer

    @GET("listings/{homeId}.json")
    suspend fun getHomeDetail(@Path("homeId") homeId: String?): NetWorkHomeItemContainer

}