package com.standardListAndDetailApp

import android.app.Application
import com.standardListAndDetailApp.di.app.AppComponent
import com.standardListAndDetailApp.di.app.AppModule
import com.standardListAndDetailApp.di.app.DaggerAppComponent

class ListAndDetailApplication : Application() {


    public val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
    override fun onCreate() {
        super.onCreate()
    }



}