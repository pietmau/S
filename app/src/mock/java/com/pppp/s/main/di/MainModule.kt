package com.pppp.s.main.di

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import com.pppp.s.main.api.Api
import com.pppp.s.main.api.NotCachedRetrofitApi
import com.pppp.s.main.model.CachedRetrofitModel
import com.pppp.s.main.model.CahedModel
import com.pppp.s.main.model.RetrofitViewModelFactory
import com.pppp.s.main.presenter.MainPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class MainModule(private val fragment: Fragment) {
    private val BASE_URL = "https://movies-sample.herokuapp.com/"
    private val CACHE_TIMEOUT_IN_MINUTES = 10L
    private val API_TIMEOUT_IN_SECONDS = 60L

    @Provides
    fun providePresenter(model: CahedModel) =
        MainPresenter(model, Schedulers.io(), AndroidSchedulers.mainThread())

    @Provides
    fun provideModel(factory: ViewModelProvider.Factory): CahedModel =
        ViewModelProviders.of(fragment, factory).get(CachedRetrofitModel::class.java)

    @Provides
    fun provideFactory(api: Api): ViewModelProvider.Factory =
        RetrofitViewModelFactory(api, CACHE_TIMEOUT_IN_MINUTES)

    @Provides
    fun provideApi(): Api = NotCachedRetrofitApi(BASE_URL, API_TIMEOUT_IN_SECONDS)

}