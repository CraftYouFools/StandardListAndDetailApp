package com.standardListAndDetailApp.ui.Activity

import android.os.Bundle
import com.example.standardlistanddetailapplicationcontent.R
import com.standardListAndDetailApp.navigation.INavigator
import com.standardListAndDetailApp.ui.Activity.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var navigator: INavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)

        setContentView(R.layout.activity_main)
        navigator.attachHomeList()
    }
}