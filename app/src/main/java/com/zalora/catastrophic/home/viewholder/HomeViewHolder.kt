package com.zalora.catastrophic.home.viewholder

import android.view.View
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zalora.catastrophic.R
import com.zalora.catastrophic.home.room.Cat

class HomeViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    private var ivCatPic: ImageView = itemView.findViewById(R.id.iv_cat_pic)

    fun onBind(response: Cat) {
        response.url?.let {
            ViewCompat.setTransitionName(ivCatPic, response.id);
            Picasso.get().load(it).placeholder(R.mipmap.place_holder).into(ivCatPic)
        }
    }
}
