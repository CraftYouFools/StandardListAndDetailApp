package com.standardListAndDetailApp.shared.presentation.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.standardListAndDetailApp.home_detail.presentation.viewmodel.DetailViewModel
import com.standardListAndDetailApp.home_list.presentation.viewmodel.ListViewModel
import com.standardListAndDetailApp.shared.data.repository.HomesRepositoryImpl
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val repository: HomesRepositoryImpl): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            ListViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            DetailViewModel(repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
