package com.standardListAndDetailApp.di.Activity

import androidx.appcompat.app.AppCompatActivity
import com.standardListAndDetailApp.di.presentation.PresentationComponent
import com.standardListAndDetailApp.navigation.INavigator
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent

@ActivityScope
@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun activity() : AppCompatActivity

    fun navigator() : INavigator

}