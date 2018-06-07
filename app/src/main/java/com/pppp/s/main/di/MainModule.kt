package com.pppp.s.main.di


import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import com.pppp.s.main.api.Api
import com.pppp.s.main.api.RetrofitApi
import com.pppp.s.main.model.MainModel
import com.pppp.s.main.model.RetrofitModel
import com.pppp.s.main.model.RetrofitViewModelFactory
import com.pppp.s.main.presenter.MainPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class MainModule(private val fragment: Fragment) {
    private val BASE_URL = "https://movies-sample.herokuapp.com/"

    @Provides
    fun providePresenter(model: MainModel) =
        MainPresenter(model, Schedulers.io(), AndroidSchedulers.mainThread())

    @Provides
    fun provideModel(factory: ViewModelProvider.Factory): MainModel =
        ViewModelProviders.of(fragment, factory).get(RetrofitModel::class.java)

    @Provides
    fun provideFactory(api: Api): ViewModelProvider.Factory = RetrofitViewModelFactory(api)

    @Provides
    fun provideApi(): Api = RetrofitApi(BASE_URL)

}