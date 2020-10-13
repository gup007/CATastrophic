package com.zalora.catastrophic.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zalora.catastrophic.R
import com.zalora.catastrophic.common.RecyclerItemClickListener
import com.zalora.catastrophic.home.room.Cat
import com.zalora.catastrophic.home.viewholder.HomeViewHolder


class HomeContentAdapter(
    private val recyclerView: RecyclerView,
    private val listener: RecyclerItemClickListener<Cat>
) :
    PagingDataAdapter<Cat, HomeViewHolder>(REPO_COMPARATOR), View.OnClickListener {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Cat>() {
            override fun areItemsTheSame(oldItem: Cat, newItem: Cat) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Cat, newItem: Cat) =
                true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.view_cat_item, parent, false
        )
        view.setOnClickListener(this)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val data = getItem(position)
        data?.let { holder.onBind(it) }
    }

    override fun onClick(view: View) {
        val childAdapterPosition = recyclerView.getChildAdapterPosition(view)
        if (childAdapterPosition < 0 || childAdapterPosition >= itemCount) {
            return
        }
        listener.onItemClick(view, getItem(childAdapterPosition)!!)
    }

}