package com.standardListAndDetailApp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.standardlistanddetailapplicationcontent.R
import com.standardListAndDetailApp.navigation.INavigator
import com.standardListAndDetailApp.ui.Activity.BaseActivity
import com.standardListAndDetailApp.ui.fragment.DetailFragment
import com.standardListAndDetailApp.ui.fragment.ListFragment
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private var mFragment: ListFragment? = null
    private var mFragmentDetail:DetailFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mFragment = ListFragment()
        mFragmentDetail = DetailFragment()

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            /** TODO change this */


            supportFragmentManager.beginTransaction()
                .add(R.id.container, ListFragment.newInstance())
                .commit();
        }
    }
}