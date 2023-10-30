package com.standardListAndDetailApp.ui.Activity

import androidx.appcompat.app.AppCompatActivity
import com.standardListAndDetailApp.ListAndDetailApplication
import com.standardListAndDetailApp.di.Activity.ActivityModule
import com.standardListAndDetailApp.di.Activity.DaggerActivityComponent
import com.standardListAndDetailApp.di.app.AppModule
import com.standardListAndDetailApp.di.app.DaggerAppComponent

open class BaseActivity: AppCompatActivity() {

    private val appComponent get() = (application as ListAndDetailApplication).appComponent


    val activityComponent by lazy {
       /* appComponent.newActivityComponentBuilder()
            .activity(this)
            .build()*/

        DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

    }

    /*
    private val presentationComponent by lazy {
        activityComponent.newPresentationComponent()
    }

    protected val injector get() = presentationComponent */
}