package com.standardListAndDetailApp.home_list.presentation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

sealed class HomeListViewState {
    data object Loading : HomeListViewState()
    data class Content(val homes: Flow<List<HomeListCardViewState>>) : HomeListViewState()
    data object Error : HomeListViewState()
    data object NetWorkError : HomeListViewState()

}

fun HomeListViewState.Content.updateFavoriteHome(
    homeId: Int,
    isInWishList: Boolean
): HomeListViewState.Content {
    return HomeListViewState.Content(homes = this.homes.map { list ->
        list.map { viewState ->
            if (viewState.id == homeId) {
                viewState.copy(isInWishList = isInWishList)
            } else {
                viewState
            }
        }
    }
    )
}