package com.pppp.s.main.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.s.R
import com.pppp.s.application.SApp
import com.pppp.s.main.di.MainModule

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        (activity?.application as? SApp)?.appComponent?.with(MainModule())?.inject(this)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }


}
