package com.pppp.s.di

import com.pppp.s.application.Injector
import dagger.Component

@Component(modules = arrayOf(MockAppModule::class))
interface MockAppComponent : Injector