package com.standardListAndDetailApp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.standardlistanddetailapplicationcontent.R
import com.standardListAndDetailApp.ui.fragment.ListFragment

class MainActivity : AppCompatActivity() {

    private var mFragment: ListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {

            /** TODO change this */
            mFragment = ListFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.container, ListFragment.newInstance())
                .commit();
        }
    }
}