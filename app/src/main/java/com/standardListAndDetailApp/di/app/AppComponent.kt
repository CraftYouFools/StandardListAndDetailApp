package com.standardListAndDetailApp.di.app

import com.standardListAndDetailApp.ListAndDetailApplication
import com.standardListAndDetailApp.di.activity.ActivityComponent
import com.standardListAndDetailApp.di.activity.ActivityModule
import com.standardListAndDetailApp.workers.AppWorkerFactory
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent

    fun inject(listAndDetailApplication: ListAndDetailApplication)
}