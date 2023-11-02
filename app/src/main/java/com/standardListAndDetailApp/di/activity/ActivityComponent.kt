package com.standardListAndDetailApp.di.activity

import androidx.appcompat.app.AppCompatActivity
import com.standardListAndDetailApp.di.presentation.PresentationComponent
import com.standardListAndDetailApp.di.presentation.PresentationModule
import com.standardListAndDetailApp.navigation.INavigator
import dagger.Component
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent
}