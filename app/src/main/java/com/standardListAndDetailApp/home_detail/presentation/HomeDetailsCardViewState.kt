package com.standardListAndDetailApp.home_detail.presentation

data class HomeDetailsCardViewState(
    val id : Int,
    val bedrooms: Int?,
    val city: String?,
    val area: Long?,
    val url: String?,
    val price: Long?,
    val professional: String?,
    val homeType: String?,
    val offerType: Int?,
    val rooms: Int?,
    val isInWishList: Boolean?
)