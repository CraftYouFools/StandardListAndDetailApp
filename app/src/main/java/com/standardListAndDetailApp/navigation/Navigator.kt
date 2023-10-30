package com.standardListAndDetailApp.navigation

import androidx.appcompat.app.AppCompatActivity
import com.example.standardlistanddetailapplicationcontent.R
import com.standardListAndDetailApp.database.DatabaseHome
import com.standardListAndDetailApp.ui.fragment.DetailFragment
import javax.inject.Inject

class Navigator @Inject constructor(private val activity: AppCompatActivity) : INavigator {

    override fun navigateToHomeDetail(home: DatabaseHome) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.container, DetailFragment.newInstance(home.id))
            .commit()
    }

    override fun navigateBack() {
        activity.onBackPressedDispatcher.onBackPressed()
    }


}