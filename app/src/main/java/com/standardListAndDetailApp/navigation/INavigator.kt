package com.standardListAndDetailApp.navigation

import com.standardListAndDetailApp.repository.database.DatabaseHome

interface INavigator {
    fun navigateToHomeDetail(home: DatabaseHome)
    fun attachHomeList()
}