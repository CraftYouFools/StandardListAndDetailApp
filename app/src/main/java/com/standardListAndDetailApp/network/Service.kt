package com.standardListAndDetailApp.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface ListingsService {
    @GET("listings.json")
    suspend fun getHomesListing(): NetWorkHomeItemContainer
}

/**
 * Main entry point for network access. Call like `DevByteNetwork.devbytes.getPlaylist()`
 */
object GslNetwork {

    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://gsl-apps-technical-test.dignp.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val listings = retrofit.create(ListingsService::class.java)
}