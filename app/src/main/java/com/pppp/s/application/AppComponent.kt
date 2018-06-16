package com.pppp.s.application

import com.pppp.s.main.MainActivity
import com.pppp.s.main.di.MainModule
import com.pppp.s.main.view.MainFragment
import dagger.Component

@Component(modules = arrayOf(AppModule::class, MainModule::class))
interface AppComponent : Injector {

    fun inject(mainActivity: MainActivity)

    fun inject(mainFragment: MainFragment)

}
