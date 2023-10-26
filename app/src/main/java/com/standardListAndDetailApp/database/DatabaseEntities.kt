package com.standardListAndDetailApp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.standardListAndDetailApp.domain.CloudHomeItem

/**
 * databaseHome represents a home entity in the database.
 */
@Entity
data class databaseHome constructor(
    val bedrooms: Int,
    val city: String,
    @PrimaryKey
    val id: Int,
    val area: Long,
    val url: String,
    val price: Long,
    val professional: String,
    val propertyType: String,
    val offerType: Int,
    val rooms: Int,
)


/**
 * Map databaseHome to domain entities
 */
fun List<databaseHome>.asDomainModel(): List<CloudHomeItem> {
    return map {
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
            it.rooms,)
    }
}