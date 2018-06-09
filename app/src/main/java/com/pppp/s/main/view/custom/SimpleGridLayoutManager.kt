package com.pppp.s.main.view.custom

import android.content.Context
import android.support.v7.widget.GridLayoutManager

class SimpleGridLayoutManager(
    context: Context,
    spanCount: Int = 3
) : GridLayoutManager(context, spanCount)