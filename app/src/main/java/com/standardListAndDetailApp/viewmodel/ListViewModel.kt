package com.standardListAndDetailApp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.standardListAndDetailApp.ListAndDetailApplication
import com.standardListAndDetailApp.repository.database.getDatabase
import com.standardListAndDetailApp.repository.HomesRepository
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class ListViewModel @Inject constructor(private val repository: HomesRepository) : ViewModel() {

    val homeList = repository.homes

    private var _eventNetworkError = MutableLiveData(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        refreshDataFromRepository()
    }

    fun onRefresh() {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        _eventNetworkError.value = false
        viewModelScope.launch {
            try {
                repository.refreshList()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
                _isLoading.value = false

            } catch (networkError: IOException) {
                _eventNetworkError.value = true
                _isLoading.value = false
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

}