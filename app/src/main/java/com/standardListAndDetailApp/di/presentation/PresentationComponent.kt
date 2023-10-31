package com.standardListAndDetailApp.di.presentation

import com.standardListAndDetailApp.di.app.AppModule
import com.standardListAndDetailApp.ui.MainActivity
import com.standardListAndDetailApp.ui.fragment.ListFragment
import dagger.Component
import dagger.Subcomponent

@PresentationScope
@Component(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(fragment: ListFragment)

    fun inject(activity: MainActivity)

}