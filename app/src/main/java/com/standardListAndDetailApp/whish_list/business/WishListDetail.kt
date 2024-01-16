package com.standardListAndDetailApp.whish_list.business

data class WishListDetail(
    val id: Int,
    val bedrooms: Int?,
    val city: String?,
    val area: Long?,
    val url: String?,
    val price: Long?,
    val professional: String?,
    val homeType: String?,
    val offerType: Int?,
    val rooms: Int?,
)