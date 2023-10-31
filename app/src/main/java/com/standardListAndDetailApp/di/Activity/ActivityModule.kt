package com.standardListAndDetailApp.di.Activity

import androidx.appcompat.app.AppCompatActivity
import com.standardListAndDetailApp.Constants
import com.standardListAndDetailApp.navigation.INavigator
import com.standardListAndDetailApp.navigation.Navigator
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    fun activity() = activity

    @Provides
    fun navigator(activity: AppCompatActivity) : INavigator = Navigator(activity)


}