package com.zalora.catastrophic.home.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.zalora.catastrophic.R
import com.zalora.catastrophic.databinding.LoadingGridItemBinding

class LoadingGridStateViewHolder(
    private val binding: LoadingGridItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState) {
        binding.progressBar.visibility = if (loadState is LoadState.Loading) View.VISIBLE else View.GONE
    }

    companion object {
        fun create(parent: ViewGroup): LoadingGridStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.loading_grid_item, parent, false)

            val binding = LoadingGridItemBinding.bind(view)

            return LoadingGridStateViewHolder(
                binding
            )
        }
    }
}