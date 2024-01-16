package com.standardListAndDetailApp.di.app

import android.app.Application
import com.standardListAndDetailApp.Constants
import com.standardListAndDetailApp.shared.business.HomesRepository
import com.standardListAndDetailApp.shared.data.repository.HomesRepositoryImpl
import com.standardListAndDetailApp.shared.data.repository.database.HomesDatabase
import com.standardListAndDetailApp.shared.data.repository.database.getDatabase
import com.standardListAndDetailApp.shared.data.repository.network.ListingsServiceApi
import com.standardListAndDetailApp.whish_list.business.WishListRepository
import com.standardListAndDetailApp.whish_list.data.repository.WishListRepositoryImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
class AppModule(private val application: Application) {

    @Provides
    fun application() = application


    @Provides
    @AppScope
    fun provideHttpClient (logging : HttpLoggingInterceptor) : OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        return httpClient.build()
    }

    @Provides
    @AppScope
    fun provideHttpLoggingInterceptor () : HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }

    @Provides
    @AppScope
    fun retrofit(client : OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @AppScope
    fun api (retrofit: Retrofit) : ListingsServiceApi {
        return retrofit.create(ListingsServiceApi::class.java)
    }

    @Provides
    @AppScope
    fun database(application: Application): HomesDatabase {
        return getDatabase(application)
    }

    @Provides
    @AppScope
    @MainDispatcher
    fun provideMainDispatcher(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    @Provides
    @AppScope
    fun homeRepository(homesRepositoryImpl: HomesRepositoryImpl): HomesRepository {
        return homesRepositoryImpl
    }

    @Provides
    @AppScope
    fun wishListRepository(wishListRepositoryImpl: WishListRepositoryImpl): WishListRepository {
        return wishListRepositoryImpl
    }


}