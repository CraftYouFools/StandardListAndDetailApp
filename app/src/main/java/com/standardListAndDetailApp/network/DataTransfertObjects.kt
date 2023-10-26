package com.standardListAndDetailApp.network

import com.squareup.moshi.JsonClass
import com.standardListAndDetailApp.database.databaseHome
import com.standardListAndDetailApp.domain.CloudHomeItem

@JsonClass(generateAdapter = true)
data class NetWorkHomeItemContainer(val homeItemList: List<HomeItem>)

@JsonClass(generateAdapter = true)
data class HomeItem(
    val bedrooms: Int,
    val city: String,
    val id: Int,
    val area: Long,
    val url: String,
    val price: Long,
    val professional: String,
    val propertyType: String,
    val offerType: Int,
    val rooms : Int,
)

/**
 * Convert Network results to database objects
 */
fun NetWorkHomeItemContainer.asDomainModel(): List<CloudHomeItem> {
    return homeItemList.map {
        CloudHomeItem(
            it.bedrooms,
            it.city,
            it.id,
            it.area,
            it.url,
            it.price,
            it.professional,
            it.propertyType,
            it.offerType,
            it.rooms,
        )
    }
}


/**
 * Convert Network results to database objects
 */
fun NetWorkHomeItemContainer.asDatabaseModel(): List<databaseHome> {
    return homeItemList.map {
        databaseHome(
            it.bedrooms,
            it.city,
            it.id,
            it.area,
            it.url,
            it.price,
            it.professional,
            it.propertyType,
            it.offerType,
            it.rooms,
        )
    }
}