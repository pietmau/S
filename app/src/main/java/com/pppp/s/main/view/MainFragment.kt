package com.pppp.s.main.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.s.R
import com.pppp.s.application.QGoApplication
import com.pppp.s.main.di.MainModule
import com.pppp.s.main.model.pokos.Movie
import com.pppp.s.main.presenter.MainPresenter
import com.pppp.s.main.view.custom.SimpleOnQueryTextListener
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class MainFragment : Fragment(), MainView {
    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val appComponent = (activity?.application as? QGoApplication)?.appComponent
        appComponent?.with(MainModule(this))?.inject(this)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchView.setOnQueryTextListener(object : SimpleOnQueryTextListener() {
            override fun onQueryTextChange(query: String?): Boolean {
                return presenter.onQueryTextChange(query)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        recycler.bind()
        presenter.bind(this)
    }

    override fun onPause() {
        super.onPause()
        recycler.unbind()
        presenter.unbind()
    }

    override fun onNewData(movies: List<Movie>) {
        recycler.onNewData(movies)
    }

    override fun onError(throwable: Throwable) {
        Snackbar.make(root, throwable.localizedMessage, Snackbar.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
