package com.pppp.s.main.model

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.pppp.s.main.api.Api

class RetrofitViewModelFactory(
    val api: Api,
    val cacheExpiryTimeInSeconds: Long = 10 * 60
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        CachedRetrofitModel(api, cacheExpiryTimeInSeconds = cacheExpiryTimeInSeconds) as T
}