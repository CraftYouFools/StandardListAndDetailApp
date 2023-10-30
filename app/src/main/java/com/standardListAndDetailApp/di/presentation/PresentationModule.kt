package com.standardListAndDetailApp.di.presentation

import com.standardListAndDetailApp.di.Activity.ActivityComponent
import com.standardListAndDetailApp.navigation.INavigator
import com.standardListAndDetailApp.navigation.Navigator
import dagger.Module
import dagger.Provides


@Module
class PresentationModule(private val activityComponent: ActivityComponent) {
    @Provides
    fun navigator() : INavigator = activityComponent.navigator()

}