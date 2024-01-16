package com.standardListAndDetailApp.whish_list.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.standardListAndDetailApp.shared.business.Result
import com.standardListAndDetailApp.whish_list.business.GetAllWishListUseCase
import com.standardListAndDetailApp.whish_list.presentation.HomeWishListCardViewState
import com.standardListAndDetailApp.whish_list.presentation.HomeWishListViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class WishListViewModel @Inject constructor(
    private val getAllWishListUseCase : GetAllWishListUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main) : ViewModel() {

    private var _viewState = MutableLiveData<HomeWishListViewState>()
    val viewState: LiveData<HomeWishListViewState>
        get() = _viewState

    init {
        refreshScreen()
    }

    fun refreshScreen() {
        viewModelScope.launch(dispatcher) {
            _viewState.postValue(HomeWishListViewState.Loading)
            refreshDataFromRepository()
        }
    }

    private suspend fun refreshDataFromRepository() {
        try {
            when (val result = getAllWishListUseCase.execute()) {
                is Result.OnFailure -> {
                    _viewState.postValue(HomeWishListViewState.Error)
                }

                is Result.OnSuccess -> {
                    result.data.let {
                        _viewState.value = HomeWishListViewState.Content(it.map { list ->
                            list.map { wishlistDetail ->
                                with(wishlistDetail) {
                                    HomeWishListCardViewState(
                                        id,
                                        city,
                                        area,
                                        url,
                                        price,
                                        homeType,
                                    )
                                }
                            }
                        })
                    }
                }
            }
        } catch (networkError: IOException) {
            _viewState.postValue(HomeWishListViewState.NetWorkError)
        }

    }

    companion object {
        private const val TAG = "WishListViewModel"
    }
}