package com.zalora.catastrophic.home

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zalora.catastrophic.R
import com.zalora.catastrophic.common.AdapterSpaceDecoration
import com.zalora.catastrophic.common.BaseFragment
import com.zalora.catastrophic.common.RecyclerItemClickListener
import com.zalora.catastrophic.databinding.FragmentNewsBinding


class HomeFragment : BaseFragment<FragmentNewsBinding>(), RecyclerItemClickListener<Cat> {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentNewsBinding

    override fun onCreateView(instance: Bundle?, binding: FragmentNewsBinding) {
        val storeViewModel = getActivityViewModel(HomeViewModel::class.java)
        binding.viewModel = storeViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        this.binding = binding
        viewModel = binding.viewModel!!
        initNewsList()
        viewModel.fetchNewsList("bitcoin").observe(viewLifecycleOwner, Observer {
            when (it) {
                is CatResponse.Success -> {
                    viewModel.newsList.postValue(it.newsData)
                }
                is CatResponse.Error -> {
                    Log.d("TAG", it.error)
                }
            }
        })
    }

    private fun initNewsList() {
        val linearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.newsList.layoutManager = linearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(
            context,
            linearLayoutManager.orientation
        )
        val dimension: Float? = context?.resources?.getDimension(R.dimen.dimen_24dp)
        dimension?.let {
            binding.newsList.addItemDecoration(AdapterSpaceDecoration(dimension.toInt()))
        }
        binding.newsList.addItemDecoration(dividerItemDecoration)
        binding.newsList.adapter = HomeContentAdapter(binding.newsList, this)
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_news
    }

    override fun onItemClick(news: Cat) {
//        val intent = Intent(context, DetailActivity::class.java)
//        intent.putExtra(DetailActivity.KEY_NEWS_DATA, news)
//        startActivity(intent)
    }
}