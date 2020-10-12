package com.zalora.catastrophic.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zalora.catastrophic.R
import com.zalora.catastrophic.common.BaseViewModel
import com.zalora.catastrophic.common.RecyclerItemClickListener
import com.zalora.catastrophic.rest.APIResponse


class HomeContentAdapter(
    private val recyclerView: RecyclerView,
    private val listener: RecyclerItemClickListener<Cat>
) :
    RecyclerView.Adapter<BaseStoreViewHolder>(),
    BaseViewModel.BindableAdapter<List<Cat>>, View.OnClickListener {

    private var responseList: ArrayList<Cat> = ArrayList()

    init {
        setHasStableIds(true)
    }

    override fun setData(data: List<Cat>?) {
        responseList.clear()
        if (data != null) {
            responseList.addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseStoreViewHolder {
        if (viewType == APIResponse.VIEW_TYPE_NORMAL) {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.view_cat_item, parent, false
            )
            view.setOnClickListener(this)
            return HomeViewHolder(view)
        }
        return BaseStoreViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_cat_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return responseList.size
    }

    override fun getItemViewType(position: Int): Int {
        return APIResponse.VIEW_TYPE_NORMAL
    }

    override fun onBindViewHolder(holder: BaseStoreViewHolder, position: Int) {
        holder.onBind(responseList[position])
    }

    override fun onClick(view: View) {
        val childAdapterPosition = recyclerView.getChildAdapterPosition(view)
        if (childAdapterPosition < 0 || childAdapterPosition >= responseList.size) {
            return
        }
        listener.onItemClick(view, responseList[childAdapterPosition])
    }
}