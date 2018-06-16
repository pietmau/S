package com.pppp.s.di

import com.pppp.s.application.AppComponent
import dagger.Component

@Component(modules = arrayOf(MockAppModule::class))
interface MockAppComponent : AppComponent