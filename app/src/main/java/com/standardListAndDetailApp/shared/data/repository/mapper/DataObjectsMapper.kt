package com.standardListAndDetailApp.shared.data.repository.mapper

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.standardListAndDetailApp.shared.data.repository.database.HomeEntity
import com.standardListAndDetailApp.shared.data.repository.cloud.CloudHomeItem

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
fun NetWorkHomeListItemContainer.asDatabaseModel(): List<HomeEntity> {
    return homeItemList.map {
        HomeEntity(
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
fun NetWorkHomeItemContainer.asDatabaseModel(): HomeEntity {
    return with(homeItem) {
        HomeEntity(
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

/**
 * Map databaseHome to domain entities
 */
fun List<HomeEntity>.asDomainModel(): List<CloudHomeItem> {
    return map {
        CloudHomeItem(
            it.bedrooms,
            it.city,
            it.id,
            it.area,
            it.url,
            it.price,
            it.professional,
            it.homeType,
            it.offerType,
            it.rooms,)
    }
}


