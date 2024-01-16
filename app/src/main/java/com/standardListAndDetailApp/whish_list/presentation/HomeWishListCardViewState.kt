package com.standardListAndDetailApp.whish_list.presentation

import com.standardListAndDetailApp.shared.presentation.state.ListState

data class HomeWishListCardViewState(
    val id:Int,
    val city: String?,
    val area: Long?,
    val url: String?,
    val price: Long?,
    val homeType: String?,
): ListState()