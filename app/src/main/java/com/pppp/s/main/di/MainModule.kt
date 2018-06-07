package com.pppp.s.main.di


import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import com.pppp.s.main.model.MainModel
import com.pppp.s.main.model.RetrofitModel
import com.pppp.s.main.model.RetrofitViewModelFactory
import com.pppp.s.main.presenter.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val fragment: Fragment) {

    @Provides
    fun providePresenter(model: MainModel) = MainPresenter(model)

    @Provides
    fun provideModel(factory: ViewModelProvider.Factory): MainModel =
        ViewModelProviders.of(fragment, factory).get(RetrofitModel::class.java)

    @Provides
    fun provideFactory(): ViewModelProvider.Factory = RetrofitViewModelFactory()

}