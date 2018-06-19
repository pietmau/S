package com.pppp.s.application

import android.app.Application
import com.squareup.leakcanary.LeakCanary

class QGoApplication : Application() {
    lateinit var appComponent: Injector

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}
