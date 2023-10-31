package com.standardListAndDetailApp.navigation

import com.standardListAndDetailApp.database.DatabaseHome

interface INavigator {
    fun navigateToHomeDetail(home: DatabaseHome)
    fun attachHomeList()
}