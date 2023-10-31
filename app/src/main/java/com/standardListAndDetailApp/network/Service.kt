package com.standardListAndDetailApp.network

import retrofit2.http.GET
import retrofit2.http.Path

interface ListingsServiceApi {
    @GET("listings.json")
    suspend fun getHomesListing(): NetWorkHomeListItemContainer

    @GET("listings/{homeId}.json")
    suspend fun getHomeDetail(@Path("homeId") homeId: String?): NetWorkHomeItemContainer

}