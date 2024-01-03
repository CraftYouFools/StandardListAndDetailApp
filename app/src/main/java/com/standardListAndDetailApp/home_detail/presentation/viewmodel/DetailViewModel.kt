package com.standardListAndDetailApp.home_detail.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.standardListAndDetailApp.home_detail.presentation.HomeDetailViewState
import com.standardListAndDetailApp.shared.business.HomesRepository
import com.standardListAndDetailApp.shared.business.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val repository: HomesRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private var _viewState = MutableLiveData<HomeDetailViewState>()
    val viewState: LiveData<HomeDetailViewState>
        get() = _viewState

    fun getHome(homeId: Int) {
        viewModelScope.launch(dispatcher) {
            _viewState.postValue(HomeDetailViewState.Loading)
            when (val result = repository.getHome(homeId)) {
                is Result.OnFailure -> {
                    _viewState.postValue(HomeDetailViewState.Error)
                }

                is Result.OnSuccess -> {
                    result.data.asFlow().collect {
                        _viewState.value = HomeDetailViewState.Content(it)
                    }
                }
            }
        }
    }

}