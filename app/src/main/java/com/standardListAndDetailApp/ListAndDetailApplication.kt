package com.standardListAndDetailApp

import android.app.Application
import androidx.work.Configuration
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.standardListAndDetailApp.di.app.AppComponent
import com.standardListAndDetailApp.di.app.AppModule
import com.standardListAndDetailApp.di.app.DaggerAppComponent
import com.standardListAndDetailApp.workers.AppWorkerFactory
import com.standardListAndDetailApp.workers.RefreshDatabaseWorker
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ListAndDetailApplication : Application() {

    @Inject
    lateinit var appWorkerFactory: AppWorkerFactory

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
    override fun onCreate() {
        appComponent.inject(this)

        super.onCreate()

        val workManagerConfig = Configuration.Builder()
            .setWorkerFactory(appWorkerFactory)
            .build()
        WorkManager.initialize(this, workManagerConfig)

        val myWorkRequest = PeriodicWorkRequestBuilder<RefreshDatabaseWorker>(1,TimeUnit.DAYS).build()
        WorkManager.getInstance(this).enqueue(myWorkRequest)

    }



}