package com.standardListAndDetailApp.whish_list.data.repository.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WishListEntity constructor(
    @PrimaryKey
    val homeId: Int
)