package com.standardListAndDetailApp.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.standardlistanddetailapplicationcontent.R
import com.standardListAndDetailApp.database.DatabaseHome
import com.standardListAndDetailApp.ui.fragment.DetailFragment
import com.standardListAndDetailApp.ui.fragment.ListFragment
import javax.inject.Inject

class Navigator @Inject constructor(private val fragmentManager: FragmentManager) : INavigator {


    //TODO change to supportFragmentManager provider
    override fun navigateToHomeDetail(home: DatabaseHome) {
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