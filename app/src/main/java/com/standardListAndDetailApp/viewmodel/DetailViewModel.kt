package com.standardListAndDetailApp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.standardListAndDetailApp.ListAndDetailApplication
import com.standardListAndDetailApp.database.DatabaseHome
import com.standardListAndDetailApp.database.getDatabase
import com.standardListAndDetailApp.repository.HomesRepository

class DetailViewModel(application: Application) : ViewModel() {


    private val repository: HomesRepository = HomesRepository(getDatabase(application),(application as ListAndDetailApplication).appComponent.api())

    private var _home = MutableLiveData<DatabaseHome>()
    val home: LiveData<DatabaseHome>
        get() = _home

    fun refreshHome(homeId : Int) {
        _home.value = repository.getHome(homeId).value
    }

}