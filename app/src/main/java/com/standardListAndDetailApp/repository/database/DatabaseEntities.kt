package com.standardListAndDetailApp.repository.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.standardListAndDetailApp.repository.domain.CloudHomeItem

@Entity
data class DatabaseHome constructor(
    val bedrooms: Int?,
    val city: String?,
    @PrimaryKey
    val id: Int,
    val area: Long?,
    val url: String?,
    val price: Long?,
    val professional: String?,
    val homeType: String?,
    val offerType: Int?,
    val rooms: Int?,
)


/**
 * Map databaseHome to domain entities
 */
fun List<DatabaseHome>.asDomainModel(): List<CloudHomeItem> {
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