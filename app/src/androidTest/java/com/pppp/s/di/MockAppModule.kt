package com.pppp.s.di

import com.pppp.s.main.api.Api
import com.pppp.s.main.api.RetrofitApi
import com.pppp.s.main.model.CachedModel
import com.pppp.s.main.presenter.MainPresenter
import com.pppp.s.mocks.MockModel
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class MockAppModule {
    @Provides
    fun providePresenter(model: CachedModel) =
        MainPresenter(
            model,
            Schedulers.io(),
            AndroidSchedulers.mainThread()
        )

    @Provides
    fun provideModel(): CachedModel = MockModel()

    @Provides
    fun provideApi(): Api = RetrofitApi(
        BASE_URL,
        API_TIMEOUT_IN_SECONDS
    )

    companion object {
        private const val BASE_URL = "https://movies-sample.herokuapp.com/"
        private const val API_TIMEOUT_IN_SECONDS = 60L
    }
}