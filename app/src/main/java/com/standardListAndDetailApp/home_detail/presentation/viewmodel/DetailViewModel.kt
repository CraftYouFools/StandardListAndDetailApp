package com.standardListAndDetailApp.home_detail.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.standardListAndDetailApp.home_detail.business.GetHomeFromDatabaseUseCase
import com.standardListAndDetailApp.home_detail.business.RefreshHomeItemUseCase
import com.standardListAndDetailApp.home_detail.presentation.HomeDetailViewState
import com.standardListAndDetailApp.home_detail.presentation.HomeDetailsCardViewState
import com.standardListAndDetailApp.shared.business.Result
import com.standardListAndDetailApp.whish_list.business.AddOrRemoveFromWishListUseCase
import com.standardListAndDetailApp.whish_list.business.IsHomeInWishListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val refreshHomeItemUseCase: RefreshHomeItemUseCase,
    private val getHomeFromDatabaseUseCase: GetHomeFromDatabaseUseCase,
    private val isHomeInWishListUseCase: IsHomeInWishListUseCase,
    private val addOrRemoveFromWishListUseCase: AddOrRemoveFromWishListUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private var _viewState = MutableLiveData<HomeDetailViewState>()
    val viewState: LiveData<HomeDetailViewState>
        get() = _viewState

    fun getHome(homeId: Int) {
        viewModelScope.launch(dispatcher) {
            _viewState.postValue(HomeDetailViewState.Loading)
            refreshHomeItemUseCase.execute(homeId)
            refreshDataFromRepository(homeId)
        }
    }

    private suspend fun refreshDataFromRepository(homeId: Int) {
        try {
            when (val result = getHomeFromDatabaseUseCase.execute(homeId)) {
                is Result.OnFailure -> {
                    _viewState.postValue(HomeDetailViewState.Error)
                }

                is Result.OnSuccess -> {
                    result.data.let {
                        _viewState.value = HomeDetailViewState.Content(with(it){
                            HomeDetailsCardViewState(
                                id,
                                bedrooms,
                                city,
                                area,
                                url,
                                price,
                                professional,
                                homeType,
                                offerType,
                                rooms,
                                isHomeInWishListUseCase.execute(id)
                            )
                        })
                    }
                }
            }
        } catch (networkError: IOException) {
            _viewState.postValue(HomeDetailViewState.Error)
        }
    }


    fun onFavoriteIconClicked(homeId : Int) {
        viewModelScope.launch(dispatcher) {
            addOrRemoveFromWishListUseCase.execute(homeId)
            refreshDataFromRepository(homeId)
        }
    }

    companion object {
        private const val TAG = "DetailViewModel"
    }

}