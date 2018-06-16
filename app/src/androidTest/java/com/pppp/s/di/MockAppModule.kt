package com.pppp.s.di

import android.arch.lifecycle.ViewModelProvider
import android.support.v4.app.Fragment
import com.pppp.s.application.AppModule
import com.pppp.s.main.api.Api
import com.pppp.s.main.di.ModelFactory
import com.pppp.s.main.model.CachedModel
import com.pppp.s.main.model.RetrofitViewModelFactory
import com.pppp.s.main.model.pokos.Movie
import com.pppp.s.main.model.pokos.MovieResponse
import com.pppp.s.mocks.MockModel
import dagger.Module
import dagger.Provides
import io.reactivex.Observable

@Module
class MockAppModule(private val observable: Observable<List<Movie>>) {

    @Provides
    fun provideModelFactory(
        api: Api,
        viewModelProviderFactory: ViewModelProvider.Factory
    ): ModelFactory = MockModelFactory(observable)

    @Provides
    fun provideViewModelProviderFactory(api: Api): ViewModelProvider.Factory =
        RetrofitViewModelFactory(api, AppModule.CACHE_TIMEOUT_IN_SECONDS)

    @Provides
    fun provideApi(): Api = MockApi()

    class MockApi : Api {
        override fun getMovies(): Observable<MovieResponse> = TODO()
    }

    class MockModelFactory(val observable: Observable<List<Movie>>) : ModelFactory {
        override fun getModel(fragment: Fragment): CachedModel = MockModel(observable)
    }
}