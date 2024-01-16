package com.standardListAndDetailApp.whish_list.presentation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

sealed class HomeWishListViewState {
    data object Loading : HomeWishListViewState()
    data class Content(val homes: Flow<List<HomeWishListCardViewState>>) : HomeWishListViewState()
    data object Error : HomeWishListViewState()
    data object NetWorkError : HomeWishListViewState()

}