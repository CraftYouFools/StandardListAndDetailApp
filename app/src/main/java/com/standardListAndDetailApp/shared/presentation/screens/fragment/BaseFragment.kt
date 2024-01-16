package com.standardListAndDetailApp.shared.presentation.screens.fragment

import androidx.fragment.app.Fragment
import com.standardListAndDetailApp.di.presentation.PresentationModule
import com.standardListAndDetailApp.shared.presentation.activity.BaseActivity


open class BaseFragment : Fragment() {

    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent(PresentationModule())
    }

    protected val injector get() = presentationComponent

}