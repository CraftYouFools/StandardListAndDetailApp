package com.standardListAndDetailApp.di.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.standardListAndDetailApp.navigation.INavigator
import com.standardListAndDetailApp.navigation.Navigator
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    fun activity() = activity

    @Provides
    fun fragmentManager() = activity.supportFragmentManager

    @Provides
    @ActivityScope
    fun navigator(fragmentManager: FragmentManager) : INavigator = Navigator(fragmentManager)



}