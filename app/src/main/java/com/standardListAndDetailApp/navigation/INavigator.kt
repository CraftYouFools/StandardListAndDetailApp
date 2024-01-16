package com.standardListAndDetailApp.navigation

interface INavigator {

    fun navigateToWishList()
    fun navigateToHomeDetail(homeId: Int)
    fun attachHomeList()
}