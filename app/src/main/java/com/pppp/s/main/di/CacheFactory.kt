package com.pppp.s.main.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.pppp.s.main.model.cache.SimpleMemoryCache

class CacheFactory(private val expiry: Long) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = SimpleMemoryCache(expiry) as T

}
