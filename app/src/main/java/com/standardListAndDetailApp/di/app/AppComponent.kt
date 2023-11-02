package com.standardListAndDetailApp.di.app

import com.standardListAndDetailApp.di.activity.ActivityComponent
import com.standardListAndDetailApp.di.activity.ActivityModule
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent
}