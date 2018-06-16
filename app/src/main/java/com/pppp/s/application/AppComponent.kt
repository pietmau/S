package com.pppp.s.application

import com.pppp.s.main.di.MainModule
import com.pppp.s.main.di.MainSubComponent
import dagger.Component

@Component(modules = arrayOf(AppModule::class))
interface AppComponent : Injector {

    override fun with(mainModule: MainModule): MainSubComponent
}
