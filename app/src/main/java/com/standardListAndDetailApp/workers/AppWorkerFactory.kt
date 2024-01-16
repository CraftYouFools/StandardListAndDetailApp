package com.standardListAndDetailApp.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject
import javax.inject.Provider

class AppWorkerFactory @Inject constructor(
    private val refreshDatabaseWorkerFactory: RefreshDatabaseWorker.Factory,
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when (workerClassName) {
            RefreshDatabaseWorker::class.java.name -> {
                refreshDatabaseWorkerFactory.create(appContext, workerParameters)
            }

            else -> null
        }
    }
}

interface ChildWorkerFactory {
    fun create(appContext: Context, params: WorkerParameters): CoroutineWorker
}