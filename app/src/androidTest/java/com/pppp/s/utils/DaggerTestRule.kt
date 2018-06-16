package com.pppp.s.utils

import android.support.test.InstrumentationRegistry
import com.pppp.s.application.QGoApplication
import com.pppp.s.di.DaggerMockAppComponent
import com.pppp.s.di.MockAppModule
import com.pppp.s.main.model.pokos.Movie
import io.reactivex.Observable
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class DaggerTestRule(private val observable: Observable<List<Movie>>) : TestRule {

    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                val instrumentation = InstrumentationRegistry.getInstrumentation()
                val app = instrumentation.targetContext.applicationContext as QGoApplication
                val builder = DaggerMockAppComponent.builder()
                app.appComponent = builder.mockAppModule(MockAppModule(observable)).build()
            }
        }
    }
}