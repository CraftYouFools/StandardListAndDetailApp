package com.standardListAndDetailApp.shared.data.repository.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HomeEntity constructor(
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