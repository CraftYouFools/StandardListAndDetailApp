package com.standardListAndDetailApp.di.presentation

import android.app.Application
import com.standardListAndDetailApp.di.activity.ActivityComponent
import com.standardListAndDetailApp.di.app.AppScope
import com.standardListAndDetailApp.di.app.MainDispatcher
import com.standardListAndDetailApp.home_detail.business.GetHomeFromDatabaseUseCase
import com.standardListAndDetailApp.home_detail.business.RefreshHomeItemUseCase
import com.standardListAndDetailApp.home_detail.presentation.viewmodel.DetailViewModel
import com.standardListAndDetailApp.home_list.business.GetAllHomesFromDatabaseUseCase
import com.standardListAndDetailApp.home_list.business.RefreshHomeListUseCase
import com.standardListAndDetailApp.home_list.presentation.viewmodel.ListViewModel
import com.standardListAndDetailApp.navigation.INavigator
import com.standardListAndDetailApp.shared.business.HomesRepository
import com.standardListAndDetailApp.shared.data.repository.database.HomesDatabase
import com.standardListAndDetailApp.shared.data.repository.database.getDatabase
import com.standardListAndDetailApp.whish_list.business.AddOrRemoveFromWishListUseCase
import com.standardListAndDetailApp.whish_list.business.GetAllWishListUseCase
import com.standardListAndDetailApp.whish_list.business.IsHomeInWishListUseCase
import com.standardListAndDetailApp.whish_list.presentation.viewmodel.WishListViewModel
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


@Module
class PresentationModule {
    @Provides
    @PresentationScope
    fun provideListViewModel(
        refreshHomeListUseCase: RefreshHomeListUseCase,
        getAllHomesFromDatabaseUseCase: GetAllHomesFromDatabaseUseCase,
        isHomeInWishListUseCase: IsHomeInWishListUseCase,
        addOrRemoveFromWishListUseCase: AddOrRemoveFromWishListUseCase,
        @MainDispatcher dispatcher: CoroutineDispatcher
    ): ListViewModel {
        return ListViewModel(
            refreshHomeListUseCase,
            getAllHomesFromDatabaseUseCase,
            isHomeInWishListUseCase,
            addOrRemoveFromWishListUseCase,
            dispatcher
        )
    }

    @Provides
    @PresentationScope
    fun provideWishListViewModel(
        getAllWishListUseCase : GetAllWishListUseCase,
        @MainDispatcher dispatcher: CoroutineDispatcher
    ): WishListViewModel {
        return WishListViewModel(
            getAllWishListUseCase,
            dispatcher
        )
    }

    @Provides
    @PresentationScope
    fun provideDetailViewModel(
        getHomeFromDatabaseUseCase: GetHomeFromDatabaseUseCase,
        refreshHomeItemUseCase: RefreshHomeItemUseCase,
        isHomeInWishListUseCase: IsHomeInWishListUseCase,
        addOrRemoveFromWishListUseCase: AddOrRemoveFromWishListUseCase,
        @MainDispatcher dispatcher: CoroutineDispatcher
    ): DetailViewModel {
        return DetailViewModel(
            refreshHomeItemUseCase,
            getHomeFromDatabaseUseCase,
            isHomeInWishListUseCase,
            addOrRemoveFromWishListUseCase,
            dispatcher
        )
    }
}