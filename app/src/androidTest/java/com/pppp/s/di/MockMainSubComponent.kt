package com.pppp.s.di

import com.pppp.s.main.di.MainSubComponent
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(MockMainModule::class))
interface MockMainSubComponent : MainSubComponent
