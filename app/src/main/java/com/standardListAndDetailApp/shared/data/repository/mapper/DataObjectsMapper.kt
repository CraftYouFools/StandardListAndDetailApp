package com.standardListAndDetailApp.shared.data.repository.mapper

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.standardListAndDetailApp.shared.data.repository.database.HomeEntity
import com.standardListAndDetailApp.shared.data.repository.cloud.CloudHomeItem

@JsonClass(generateAdapter = true)
data class NetWorkHomeListItemContainer(@Json(name = "items") val homeItemList: List<HomeItem>)


@JsonClass(generateAdapter = true)
data class HomeItem(
    @Json(name = "bedrooms")
    val bedrooms: Int?,
    @Json(name = "city")
    val city: String?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "area")
    val area: Long?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "price")
    val price: Long?,
    @Json(name = "professional")
    val professional: String?,
    @Json(name = "propertyType")
    val propertyType: String?,
    @Json(name = "offerType")
    val offerType: Int?,
    @Json(name = "rooms")
    val rooms : Int?
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
fun HomeItem.asDatabaseModel(): HomeEntity {
    return with(this) {
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


