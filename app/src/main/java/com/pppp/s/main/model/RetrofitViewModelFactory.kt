package com.pppp.s.main.model

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.pppp.s.main.api.Api

class RetrofitViewModelFactory(
    val api: Api,
    val cacheExpiryTimeInMinutes: Long = 10
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        CachedRetrofitModel(api, cacheExpiryTimeInMinutes) as T
}