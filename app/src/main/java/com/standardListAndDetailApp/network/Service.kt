package com.standardListAndDetailApp.network

import retrofit2.http.GET

interface ListingsServiceApi {
    @GET("listings.json")
    suspend fun getHomesListing(): NetWorkHomeItemContainer
}