package com.standardListAndDetailApp.di.presentation

import com.standardListAndDetailApp.home_detail.presentation.fragment.DetailFragment
import com.standardListAndDetailApp.shared.presentation.activity.MainActivity
import com.standardListAndDetailApp.home_list.presentation.fragment.ListFragment
import com.standardListAndDetailApp.whish_list.presentation.fragment.WishListFragment
import dagger.Subcomponent

@PresentationScope
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(fragment: ListFragment)

    fun inject(fragment: DetailFragment)

    fun inject(fragment: WishListFragment)

    fun inject(activity: MainActivity)

}