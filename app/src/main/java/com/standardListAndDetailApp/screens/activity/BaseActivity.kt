package com.standardListAndDetailApp.screens.activity

import androidx.appcompat.app.AppCompatActivity
import com.standardListAndDetailApp.ListAndDetailApplication
import com.standardListAndDetailApp.di.activity.ActivityModule
import com.standardListAndDetailApp.di.presentation.PresentationComponent
import com.standardListAndDetailApp.di.presentation.PresentationModule

open class BaseActivity: AppCompatActivity() {

    private val appComponent get() = (application as ListAndDetailApplication).appComponent


    val activityComponent by lazy {
        appComponent.newActivityComponent(ActivityModule(this))
    }

    private val presentationComponent by lazy {
        activityComponent.newPresentationComponent(PresentationModule())
    }

    protected val injector: PresentationComponent get() = presentationComponent



    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}