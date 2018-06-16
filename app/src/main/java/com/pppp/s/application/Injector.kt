package com.pppp.s.application

import com.pppp.s.main.di.MainModule
import com.pppp.s.main.di.MainSubComponent

interface Injector {

    fun with(mainModule: MainModule): MainSubComponent
}
