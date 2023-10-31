package com.standardListAndDetailApp.ui.Activity

import android.window.OnBackInvokedDispatcher
import androidx.appcompat.app.AppCompatActivity
import com.standardListAndDetailApp.ListAndDetailApplication
import com.standardListAndDetailApp.di.Activity.ActivityComponent
import com.standardListAndDetailApp.di.Activity.ActivityModule
import com.standardListAndDetailApp.di.Activity.DaggerActivityComponent
import com.standardListAndDetailApp.di.presentation.DaggerPresentationComponent
import com.standardListAndDetailApp.di.presentation.PresentationComponent
import com.standardListAndDetailApp.di.presentation.PresentationModule

open class BaseActivity: AppCompatActivity() {

    private val appComponent get() = (application as ListAndDetailApplication).appComponent


    val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()
    }

    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder().presentationModule(PresentationModule(this.activityComponent)).build()
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