package com.zalora.catastrophic.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.zalora.catastrophic.DetailActivity
import com.zalora.catastrophic.R
import com.zalora.catastrophic.common.AdapterSpaceDecoration
import com.zalora.catastrophic.common.BaseFragment
import com.zalora.catastrophic.common.RecyclerItemClickListener
import com.zalora.catastrophic.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding>(), RecyclerItemClickListener<Cat> {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(instance: Bundle?, binding: FragmentHomeBinding) {
        val storeViewModel = getActivityViewModel(HomeViewModel::class.java)
        binding.viewModel = storeViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        this.binding = binding
        viewModel = binding.viewModel!!
        initNewsList()
        viewModel.fetchNewsList().observe(viewLifecycleOwner, {
            when (it) {
                is CatResponse.Success -> {
                    viewModel.catList.postValue(it.catData)
                }
                is CatResponse.Error -> {
                    Log.d("TAG", it.error)
                }
            }
        })
    }

    private fun initNewsList() {
        val linearLayoutManager = GridLayoutManager(activity, 3)
        binding.homeRecycler.layoutManager = linearLayoutManager
        val dimension: Float? = context?.resources?.getDimension(R.dimen.dimen_4dp)
        dimension?.let {
            binding.homeRecycler.addItemDecoration(AdapterSpaceDecoration(dimension.toInt()))
        }
        binding.homeRecycler.adapter = HomeContentAdapter(binding.homeRecycler, this)
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_home
    }

    override fun onItemClick(view: View, cat: Cat) {
        val imageView = view.findViewById<ImageView>(R.id.iv_cat_pic)
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.KEY_CAT_DATA, cat)

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            activity!!,
            imageView,
            ViewCompat.getTransitionName(imageView)!!
        )
        startActivity(intent, options.toBundle())
//        startActivity(intent)
    }
}