package com.standardListAndDetailApp.navigation

import com.standardListAndDetailApp.shared.data.repository.database.HomeEntity

interface INavigator {
    fun navigateToHomeDetail(home: HomeEntity)
    fun attachHomeList()
}