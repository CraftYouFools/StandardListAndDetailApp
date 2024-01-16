package com.standardListAndDetailApp.home_list.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.standardListAndDetailApp.home_list.business.GetAllHomesFromDatabaseUseCase
import com.standardListAndDetailApp.home_list.business.RefreshHomeListUseCase
import com.standardListAndDetailApp.home_list.presentation.HomeListCardViewState
import com.standardListAndDetailApp.home_list.presentation.HomeListViewState
import com.standardListAndDetailApp.shared.business.Result
import com.standardListAndDetailApp.whish_list.business.AddOrRemoveFromWishListUseCase
import com.standardListAndDetailApp.whish_list.business.IsHomeInWishListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val refreshHomeListUseCase: RefreshHomeListUseCase,
    private val getAllHomesFromDatabaseUseCase: GetAllHomesFromDatabaseUseCase,
    private val isHomeInWishListUseCase: IsHomeInWishListUseCase,
    private val addOrRemoveFromWishListUseCase: AddOrRemoveFromWishListUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main) : ViewModel() {

    private var _viewState = MutableLiveData<HomeListViewState>()
    val viewState: LiveData<HomeListViewState>
        get() = _viewState

    init {
        refreshScreen()
    }

    fun onRefresh() {
        refreshScreen()
    }

    private fun refreshScreen() {
        viewModelScope.launch(dispatcher) {
            _viewState.postValue(HomeListViewState.Loading)
            refreshHomeListUseCase.execute()
            refreshDataFromRepository()
        }
    }

    private suspend fun refreshDataFromRepository() {
        try {
            when (val result = getAllHomesFromDatabaseUseCase.execute()) {
                is Result.OnFailure -> {
                    _viewState.postValue(HomeListViewState.Error)
                }

                is Result.OnSuccess -> {
                    result.data.let {
                        _viewState.value = HomeListViewState.Content(it.map { list ->
                            list.map { homeDetails ->
                                with(homeDetails) {
                                    HomeListCardViewState(
                                        id,
                                        city,
                                        area,
                                        url,
                                        price,
                                        homeType,
                                        isHomeInWishListUseCase.execute(id)
                                    )
                                }
                            }
                        })
                    }
                }
            }
        } catch (networkError: IOException) {
            _viewState.postValue(HomeListViewState.NetWorkError)
        }

    }

    fun onFavoriteIconClicked(homeId : Int) {
        viewModelScope.launch(dispatcher) {
            addOrRemoveFromWishListUseCase.execute(homeId)
            viewModelScope.launch(dispatcher) {
                refreshDataFromRepository()
            }
        }
    }

}