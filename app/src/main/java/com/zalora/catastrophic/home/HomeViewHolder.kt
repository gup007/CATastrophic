package com.zalora.catastrophic.home

import android.view.View
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.zalora.catastrophic.R
import com.zalora.catastrophic.common.ImageCaching

class HomeViewHolder(itemView: View) :
    BaseStoreViewHolder(itemView) {

    var ivCatPic: ImageView = itemView.findViewById(R.id.iv_cat_pic)

    override fun onBind(response: Cat) {
        super.onBind(response)
        response.url?.let {
            ViewCompat.setTransitionName(ivCatPic, response.id);
            ImageCaching(itemView.context).load(it).into(ivCatPic)
        }
    }
}

open class BaseStoreViewHolder(
    itemView: View
) :
    RecyclerView.ViewHolder(itemView) {

    open fun onBind(response: Cat) {}
}
