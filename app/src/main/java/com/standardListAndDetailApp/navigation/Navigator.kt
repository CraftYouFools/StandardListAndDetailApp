package com.standardListAndDetailApp.navigation

import androidx.fragment.app.FragmentManager
import com.example.standardlistanddetailapplicationcontent.R
import com.standardListAndDetailApp.home_detail.presentation.fragment.DetailFragment
import com.standardListAndDetailApp.shared.data.repository.database.HomeEntity
import com.standardListAndDetailApp.home_list.presentation.fragment.ListFragment
import javax.inject.Inject

class Navigator @Inject constructor(private val fragmentManager: FragmentManager) : INavigator {


    //TODO change to supportFragmentManager provider
    override fun navigateToHomeDetail(home: HomeEntity) {
        fragmentManager.beginTransaction()
            .replace(R.id.container, DetailFragment.newInstance(home.id))
            .addToBackStack(DetailFragment.FRAGMENT_LIST_NAME)
            .commit()
    }

    override fun attachHomeList() {
        fragmentManager.beginTransaction()
            .add(R.id.container, ListFragment.newInstance())
            .commit()
    }

}