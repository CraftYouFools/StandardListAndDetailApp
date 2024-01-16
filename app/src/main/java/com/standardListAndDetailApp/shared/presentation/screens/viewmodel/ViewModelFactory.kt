package com.standardListAndDetailApp.shared.presentation.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.standardListAndDetailApp.home_detail.presentation.viewmodel.DetailViewModel
import com.standardListAndDetailApp.home_list.presentation.viewmodel.ListViewModel
import com.standardListAndDetailApp.whish_list.presentation.viewmodel.WishListViewModel
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val listViewModelProvider : Provider<ListViewModel>,
    private val detailViewModelProvider: Provider<DetailViewModel>,
    private val wishListViewModelProvider : Provider<WishListViewModel>

): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            listViewModelProvider.get() as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            detailViewModelProvider.get() as T
        } else if (modelClass.isAssignableFrom(WishListViewModel::class.java)) {
            wishListViewModelProvider.get() as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
