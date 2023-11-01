package com.standardListAndDetailApp.di.presentation

import com.standardListAndDetailApp.ui.Activity.MainActivity
import com.standardListAndDetailApp.ui.fragment.ListFragment
import dagger.Component

@PresentationScope
@Component(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(fragment: ListFragment)

    fun inject(activity: MainActivity)

}