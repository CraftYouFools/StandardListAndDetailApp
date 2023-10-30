package com.standardListAndDetailApp.ui.fragment

import androidx.fragment.app.Fragment
import com.standardListAndDetailApp.di.presentation.DaggerPresentationComponent
import com.standardListAndDetailApp.di.presentation.PresentationModule
import com.standardListAndDetailApp.ui.Activity.BaseActivity

open class BaseFragment : Fragment() {


  /*  private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent()
    }*/

    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder().presentationModule(PresentationModule((requireActivity() as BaseActivity).activityComponent)).build()
    }

    protected val injector get() = presentationComponent

}