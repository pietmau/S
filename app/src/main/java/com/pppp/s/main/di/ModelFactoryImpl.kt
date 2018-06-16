package com.pppp.s.main.di

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import com.pppp.s.main.model.CachedRetrofitModel

class ModelFactoryImpl(private val factory: ViewModelProvider.Factory) : ModelFactory {

    override fun getModel(fragment: Fragment) = ViewModelProviders.of(
        fragment, factory
    ).get(CachedRetrofitModel::class.java)

}