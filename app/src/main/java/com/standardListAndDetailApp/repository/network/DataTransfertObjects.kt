package com.standardListAndDetailApp.repository.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.standardListAndDetailApp.repository.database.DatabaseHome
import com.standardListAndDetailApp.domain.CloudHomeItem

@JsonClass(generateAdapter = true)
data class NetWorkHomeListItemContainer(@Json(name = "items") val homeItemList: List<HomeItem>)

@JsonClass(generateAdapter = true)
data class NetWorkHomeItemContainer(val homeItem: HomeItem)

@JsonClass(generateAdapter = true)
data class HomeItem(
    val bedrooms: Int?,
    val city: String?,
    val id: Int,
    val area: Long?,
    val url: String?,
    val price: Long?,
    val professional: String?,
    val propertyType: String?,
    val offerType: Int?,
    val rooms : Int?,
)

/**
 * Convert Network results to database objects
 */
fun NetWorkHomeListItemContainer.asDomainModel(): List<CloudHomeItem> {
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
fun NetWorkHomeListItemContainer.asDatabaseModel(): List<DatabaseHome> {
    return homeItemList.map {
        DatabaseHome(
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
fun NetWorkHomeItemContainer.asDatabaseModel(): DatabaseHome {
    return with(homeItem) {
        DatabaseHome(
            bedrooms,
            city,
            id,
            area,
            url,
            price,
            professional,
            propertyType,
            offerType,
            rooms,
        )
    }
}

