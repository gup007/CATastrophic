package com.zalora.catastrophic.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zalora.catastrophic.R
import com.zalora.catastrophic.common.ImageCaching

class HomeViewHolder(itemView: View) :
    BaseStoreViewHolder(itemView) {

    private var tvNewsTitle: TextView = itemView.findViewById(R.id.tv_news_title)
    private var tvNewsSource: TextView = itemView.findViewById(R.id.tv_news_source)
    var ivNewsPic: ImageView = itemView.findViewById(R.id.iv_news_pic)


    override fun onBind(response: Cat) {
        super.onBind(response)
        response.urlToImage?.let {
            ImageCaching(itemView.context).load(it).into(ivNewsPic)
        }
        tvNewsTitle.text = response.title
        tvNewsSource.text = response.author
    }
}

open class BaseStoreViewHolder(
    itemView: View
) :
    RecyclerView.ViewHolder(itemView) {

    open fun onBind(response: Cat) {}
}
