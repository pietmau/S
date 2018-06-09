package com.pppp.s.application

import com.pppp.s.main.MainActivity
import com.pppp.s.main.di.MainSubComponent
import dagger.Component
import com.pppp.s.main.di.MainModule

@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun with(mainModule: MainModule): MainSubComponent
}
