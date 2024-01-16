package com.standardListAndDetailApp.home_detail.presentation

sealed class HomeDetailViewState {
    data object Loading : HomeDetailViewState()
    data class Content(val home: HomeDetailsCardViewState) : HomeDetailViewState()
    data object Error : HomeDetailViewState()
}

fun HomeDetailViewState.Content.updateFavoriteHome(
    homeId: Int,
    isInWishList: Boolean
): HomeDetailViewState.Content {
    return HomeDetailViewState.Content(
        home =
        if (home.id == homeId) {
            home.copy(isInWishList = isInWishList)
        } else {
            home
        }
    )
}