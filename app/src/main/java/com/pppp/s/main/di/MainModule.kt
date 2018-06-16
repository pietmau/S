package com.pppp.s.main.di

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import com.pppp.s.main.api.Api
import com.pppp.s.main.api.RetrofitApi
import com.pppp.s.main.model.CachedModel
import com.pppp.s.main.model.CachedRetrofitModel
import com.pppp.s.main.model.RetrofitViewModelFactory
import com.pppp.s.main.presenter.MainPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class MainModule(private val fragment: Fragment) {

    @Provides
    fun providePresenter(model: CachedModel) =
        MainPresenter(model, Schedulers.io(), AndroidSchedulers.mainThread())

    @Provides
    fun provideModel(factory: ViewModelProvider.Factory): CachedModel =
        ViewModelProviders.of(fragment, factory).get(CachedRetrofitModel::class.java)

    @Provides
    fun provideFactory(api: Api): ViewModelProvider.Factory =
        RetrofitViewModelFactory(api, CACHE_TIMEOUT_IN_SECONDS)

    @Provides
    fun provideApi(): Api = RetrofitApi(BASE_URL, API_TIMEOUT_IN_SECONDS)

    companion object {
        private const val BASE_URL = "https://movies-sample.herokuapp.com/"
        private const val CACHE_TIMEOUT_IN_SECONDS = 10 * 60L
        private const val API_TIMEOUT_IN_SECONDS = 60L
    }
}
