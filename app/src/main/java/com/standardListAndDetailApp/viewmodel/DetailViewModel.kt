package com.standardListAndDetailApp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.standardListAndDetailApp.ListAndDetailApplication
import com.standardListAndDetailApp.database.DatabaseHome
import com.standardListAndDetailApp.database.getDatabase
import com.standardListAndDetailApp.repository.HomesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
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