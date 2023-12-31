package com.standardListAndDetailApp.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.standardListAndDetailApp.shared.data.repository.HomesRepositoryImpl
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject


class RefreshDatabaseWorker @AssistedInject constructor(
    private val repository: HomesRepositoryImpl,
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend  fun doWork(): Result {
        Log.d(TAG, "refreshList()")
        return try {
            repository.refreshList()
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(appContext: Context, params: WorkerParameters): RefreshDatabaseWorker
    }

    companion object{
        private const val TAG = "RefreshDatabaseWorker"

    }

}