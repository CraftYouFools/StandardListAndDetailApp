package com.standardListAndDetailApp.di.presentation

import com.standardListAndDetailApp.home_detail.presentation.fragment.DetailFragment
import com.standardListAndDetailApp.shared.presentation.activity.MainActivity
import com.standardListAndDetailApp.home_list.presentation.fragment.ListFragment
import dagger.Subcomponent

@PresentationScope
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(fragment: ListFragment)

    fun inject(fragment: DetailFragment)

    fun inject(activity: MainActivity)

}