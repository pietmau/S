package com.pppp.s.main.view.custom

import android.support.v7.widget.SearchView


open class SimpleOnQueryTextListener : SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(p0: String?): Boolean = false

    override fun onQueryTextChange(p0: String?): Boolean = true
}