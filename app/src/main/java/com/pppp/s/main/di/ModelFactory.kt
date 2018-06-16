package com.pppp.s.main.di

import android.support.v4.app.Fragment
import com.pppp.s.main.model.CachedModel

interface ModelFactory {
    fun getModel(fragment: Fragment): CachedModel
}
