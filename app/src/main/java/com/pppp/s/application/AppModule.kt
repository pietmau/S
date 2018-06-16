package com.pppp.s.application

import android.arch.lifecycle.ViewModelProvider
import com.pppp.s.main.api.Api
import com.pppp.s.main.api.RetrofitApi
import com.pppp.s.main.di.ModelFactory
import com.pppp.s.main.di.ModelFactoryImpl
import com.pppp.s.main.model.RetrofitViewModelFactory
import dagger.Module
import dagger.Provides

@Module
open class AppModule {

    @Provides
    fun provideViewModelProviderFactory(api: Api): ViewModelProvider.Factory =
        RetrofitViewModelFactory(api, CACHE_TIMEOUT_IN_SECONDS)

    @Provides
    fun provideApi(): Api = RetrofitApi(BASE_URL, API_TIMEOUT_IN_SECONDS)

    @Provides
    open fun provideModelFactory(
        api: Api,
        viewModelProviderFactory: ViewModelProvider.Factory
    ): ModelFactory =
        ModelFactoryImpl(viewModelProviderFactory)

    companion object {
        const val CACHE_TIMEOUT_IN_SECONDS = 10 * 60L
        private const val BASE_URL = "https://movies-sample.herokuapp.com/"
        private const val API_TIMEOUT_IN_SECONDS = 60L
    }
}
