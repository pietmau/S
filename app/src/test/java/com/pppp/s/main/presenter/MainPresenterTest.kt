package com.pppp.s.main.presenter

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.whenever
import com.pppp.s.main.model.CachedModel
import com.pppp.s.main.model.pokos.Movie
import com.pppp.s.main.view.MainView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.anyList
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {
    private val list: MutableList<Movie> = mutableListOf()
    private val scheduler = Schedulers.trampoline()
    private lateinit var presenter: MainPresenter
    @Mock
    lateinit var model: CachedModel
    @Mock
    lateinit var view: MainView
    @Mock
    lateinit var compositeDisposable: CompositeDisposable
    @Mock
    lateinit var filterer: Filterer

    @Before
    fun setUp() {
        presenter = MainPresenter(model, scheduler, scheduler, compositeDisposable, filterer)
        whenever(model.subscribe()).thenReturn(Observable.just(emptyList()))
    }

    @Test
    fun whenSubscribesThenSubscribesToModel() {
        presenterBinds()
        verifyGetsMovies()
    }

    @Test
    fun whenSubscribesThenAddsSubscription() {
        presenterBinds()
        verifyDisposableIsAdded()
    }

    @Test
    fun whenUnsubscribesThenClearsSubscription() {
        presenter.unbind()
        verify(compositeDisposable).clear()
    }

    @Test
    fun whenGetsMoviesThenMoviesAreSet() {
        whenever(model.subscribe()).thenReturn(Observable.just(list))
        presenterBinds()
        verify(view).onNewData(list)
    }

    @Test
    fun whenThereIsAnErrorThenErrorIsShown() {
        val exception = Exception(ERROR_MESSAGE)
        whenever(model.subscribe()).thenReturn(Observable.error(exception))
        presenterBinds()
        verify(view).onError(exception)
    }

    @Test
    fun whenQueriesThenFilters() {
        onQueryTextChange()
        verify(filterer).filter(anyList(), eq(QUERY), eq(presenter))
    }

    @Test
    fun whenQueriesThenGetsTheMovies() {
        onQueryTextChange()
        verifyGetsMovies()
    }

    @Test
    fun whenQueriesThenGetsAddsTheDisposable() {
        onQueryTextChange()
        verifyDisposableIsAdded()
    }

    private fun onQueryTextChange() {
        presenter.onQueryTextChange(QUERY)
    }

    private fun presenterBinds() {
        presenter.bind(view)
    }

    private fun verifyGetsMovies() {
        verify(model).subscribe()
    }

    private fun verifyDisposableIsAdded() {
        verify(compositeDisposable).add(any())
    }

    companion object {
        private const val ERROR_MESSAGE = "error"
        private const val QUERY = "query"
    }
}
