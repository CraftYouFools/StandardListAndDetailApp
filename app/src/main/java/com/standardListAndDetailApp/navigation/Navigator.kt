package com.standardListAndDetailApp.navigation

import androidx.fragment.app.FragmentManager
import com.example.standardlistanddetailapplicationcontent.R
import com.standardListAndDetailApp.home_detail.presentation.fragment.DetailFragment
import com.standardListAndDetailApp.home_list.presentation.fragment.ListFragment
import com.standardListAndDetailApp.whish_list.presentation.fragment.WishListFragment
import javax.inject.Inject

class Navigator @Inject constructor(private val fragmentManager: FragmentManager) : INavigator {

    override fun navigateToWishList() {
        fragmentManager.beginTransaction()
            .replace(R.id.container, WishListFragment.newInstance())
            .commit()
    }

    override fun navigateToHomeDetail(homeId: Int) {
        fragmentManager.beginTransaction()
            .replace(R.id.container, DetailFragment.newInstance(homeId))
            .addToBackStack(DetailFragment.FRAGMENT_LIST_NAME)
            .commit()
    }

    override fun attachHomeList() {
        fragmentManager.beginTransaction()
            .add(R.id.container, ListFragment.newInstance())
            .commit()
    }

}