package com.standardListAndDetailApp.di.app

import android.app.Application
import com.standardListAndDetailApp.Constants
import com.standardListAndDetailApp.network.ListingsServiceApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class AppModule(private val application: Application) {

    @Provides
    fun application() = application

    @Provides
    @AppScope
    fun retrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @AppScope
    fun api (retrofit: Retrofit) : ListingsServiceApi {
        return retrofit.create(ListingsServiceApi::class.java)
    }

}