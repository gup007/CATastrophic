package com.zalora.catastrophic.common

import android.view.View

interface RecyclerItemClickListener<T> {

    fun onItemClick(view: View, item: T)

}
