package com.pppp.s.main.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.s.R
import com.pppp.s.application.SApp
import com.pppp.s.main.di.MainModule
import com.pppp.s.main.presenter.MainPresenter
import javax.inject.Inject

class MainFragment : Fragment(), MainView {
    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        (activity?.application as? SApp)?.appComponent?.with(MainModule(this))?.inject(this)
        return inflater.inflate(R.layout.main_fragment, container, false)
        presenter.view = this
    }



    companion object {
        fun newInstance() = MainFragment()
    }
}
