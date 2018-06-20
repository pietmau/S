package com.pppp.s.main.di

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import com.pppp.s.main.api.Api
import com.pppp.s.main.model.CachedModel
import com.pppp.s.main.model.ModelWithRepository
import com.pppp.s.main.model.cache.Cache
import com.pppp.s.main.model.cache.CompositeCache
import com.pppp.s.main.model.cache.file.FileCache
import com.pppp.s.main.model.cache.memory.SimpleMemoryCache
import com.pppp.s.main.model.repository.Repository
import com.pppp.s.main.model.repository.RetrofitCachedRepository
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
    fun provideModel(
        factory: ModelFactory,
        repo: Repository
    ): CachedModel = ModelWithRepository(repo)

    @Provides
    fun provideRepository(api: Api, cache: Cache): Repository =
        RetrofitCachedRepository(api, cache)

    @Provides
    fun provideCache(factory: CacheFactory): Cache =
        CompositeCache(
            10 * 60 * 1000,
            SimpleMemoryCache(),
            FileCache(fragment.requireContext())
        )

    @Provides
    fun providefactory(): CacheFactory = CacheFactory(10 * 60 * 1000)

    private fun getSimpleMemoryCache(factory: CacheFactory): SimpleMemoryCache =
        ViewModelProviders.of(fragment, factory)
            .get<SimpleMemoryCache>(SimpleMemoryCache::class.java)

}
