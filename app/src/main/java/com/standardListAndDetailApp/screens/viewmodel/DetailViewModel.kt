package com.standardListAndDetailApp.screens.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.standardListAndDetailApp.repository.database.DatabaseHome
import com.standardListAndDetailApp.repository.HomesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val repository: HomesRepository) : ViewModel() {

    private var _home = MutableLiveData<DatabaseHome>()
    val home: LiveData<DatabaseHome>
        get() = _home

    fun getHome(homeId: Int) {
        viewModelScope.launch {
            repository.getHome(homeId).asFlow().collect {
                _home.value = it
            }
        }
    }

}