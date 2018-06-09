package com.pppp.s.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pppp.s.R
import com.pppp.s.main.view.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.replace(R.id.container, MainFragment.newInstance()).commitNow()
        }
    }
}
