package com.pppp.s.main.model

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class RetrofitViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = RetrofitModel() as T
}