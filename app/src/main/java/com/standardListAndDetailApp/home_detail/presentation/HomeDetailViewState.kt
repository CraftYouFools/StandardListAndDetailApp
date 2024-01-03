package com.standardListAndDetailApp.home_detail.presentation

import com.standardListAndDetailApp.home_detail.business.HomeDetails

sealed class HomeDetailViewState {
    data object Loading : HomeDetailViewState()
    data class Content(val home: HomeDetails) : HomeDetailViewState()
    data object Error : HomeDetailViewState()
}