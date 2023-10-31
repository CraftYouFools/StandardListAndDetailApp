package com.standardListAndDetailApp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.standardListAndDetailApp.ListAndDetailApplication
import com.standardListAndDetailApp.database.DatabaseHome
import com.standardListAndDetailApp.database.getDatabase
import com.standardListAndDetailApp.repository.HomesRepository

class DetailViewModel(application: Application) : ViewModel() {


    private val repository: HomesRepository = HomesRepository(getDatabase(application),(application as ListAndDetailApplication).appComponent.api())


    val _home = MutableLiveData<DatabaseHome>()
    val home: LiveData<DatabaseHome> // public and read only.
        get() = _home

    // suspend function to call the api
    suspend fun refreshHome(homeId : Int) {
        //repository.getHome(homeId).observe().

        _home.postValue(repository.getHome(homeId).value)
    }


}