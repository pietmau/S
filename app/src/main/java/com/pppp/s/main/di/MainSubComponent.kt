package com.pppp.s.main.di

import com.pppp.s.main.view.MainFragment
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(MainModule::class))
interface MainSubComponent {

    fun inject(mainFragment: MainFragment)
}
