package com.standardListAndDetailApp.di.app

import com.standardListAndDetailApp.network.ListingsServiceApi
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun api(): ListingsServiceApi
}