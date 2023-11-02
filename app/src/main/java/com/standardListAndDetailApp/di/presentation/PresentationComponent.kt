package com.standardListAndDetailApp.di.presentation

import com.standardListAndDetailApp.ui.activity.MainActivity
import com.standardListAndDetailApp.ui.fragment.DetailFragment
import com.standardListAndDetailApp.ui.fragment.ListFragment
import dagger.Component
import dagger.Subcomponent

@PresentationScope
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(fragment: ListFragment)

    fun inject(fragment: DetailFragment)

    fun inject(activity: MainActivity)

}