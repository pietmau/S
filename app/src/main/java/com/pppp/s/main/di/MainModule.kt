package com.pppp.s.main.di

import android.support.v4.app.Fragment
import com.pppp.s.main.model.CachedModel
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
    fun provideModel(factory: ModelFactory): CachedModel = factory.getModel(fragment)
}
