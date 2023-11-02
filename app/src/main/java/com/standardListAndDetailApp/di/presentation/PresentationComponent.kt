package com.standardListAndDetailApp.di.presentation

import com.standardListAndDetailApp.screens.activity.MainActivity
import com.standardListAndDetailApp.screens.fragment.DetailFragment
import com.standardListAndDetailApp.screens.fragment.ListFragment
import dagger.Subcomponent

@PresentationScope
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(fragment: ListFragment)

    fun inject(fragment: DetailFragment)

    fun inject(activity: MainActivity)

}