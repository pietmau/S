package com.pppp.s.main.model

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.pppp.s.main.api.Api

class RetrofitViewModelFactory(val api: Api) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = CachedRetrofitModel(api,10) as T
}