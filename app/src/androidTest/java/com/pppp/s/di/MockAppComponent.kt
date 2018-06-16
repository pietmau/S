package com.pppp.s.di

import com.pppp.s.application.AppComponent
import com.pppp.s.main.di.MainModule
import dagger.Component

@Component(modules = arrayOf(MockAppModule::class))
interface MockAppComponent : AppComponent {
    override fun with(mainModule: MainModule): MockMainSubComponent
}
