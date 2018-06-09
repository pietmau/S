package com.pppp.s.main.model

import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.pppp.s.main.api.Api
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CachedRetrofitModelTest {
    @Mock
    lateinit var api: Api
    private var cachedModel: CachedModel? = null

    @Before
    fun setUp() {
        whenever(api.getMovies()).thenReturn(Observable.empty())
        cachedModel = CachedRetrofitModel(api, 5)
    }

    @Test
    fun whenSubscribesThenGetsObservable() {
        subscribe()
        verifyApiIsCalled()
    }

    @Test
    fun whenSubscribesTwiceThenGetsObservableOnlyOnce() {
        subscribe()
        reset(api)
        subscribe()
        verify(api, never()).getMovies()
    }

    @Test
    fun whenExpiresThenApiIsCalledAgain() {
        cachedModel = CachedRetrofitModel(api, -500)
        subscribe()
        reset(api)
        whenever(api.getMovies()).thenReturn(Observable.empty())
        subscribe()
        verifyApiIsCalled()
    }

    private fun verifyApiIsCalled() {
        verify(api).getMovies()
    }

    private fun subscribe() {
        cachedModel?.subscribe()?.subscribe(TestObserver())
    }
}
