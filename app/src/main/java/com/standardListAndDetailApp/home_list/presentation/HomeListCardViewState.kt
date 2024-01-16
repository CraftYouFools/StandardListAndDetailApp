package com.standardListAndDetailApp.home_list.presentation

import com.standardListAndDetailApp.shared.presentation.state.ListState

data class HomeListCardViewState(
    val id: Int,
    val city: String?,
    val area: Long?,
    val url: String?,
    val price: Long?,
    val homeType: String?,
    val isInWishList : Boolean?,
): ListState()