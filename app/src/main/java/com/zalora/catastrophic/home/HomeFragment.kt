package com.zalora.catastrophic.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import com.zalora.catastrophic.DetailActivity
import com.zalora.catastrophic.R
import com.zalora.catastrophic.common.AdapterSpaceDecoration
import com.zalora.catastrophic.common.BaseFragment
import com.zalora.catastrophic.common.RecyclerItemClickListener
import com.zalora.catastrophic.databinding.FragmentHomeBinding
import com.zalora.catastrophic.home.room.Cat
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(), RecyclerItemClickListener<Cat> {


    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeContentAdapter
    private var fetchJob: Job? = null

    @ExperimentalPagingApi
    override fun onCreateView(instance: Bundle?, binding: FragmentHomeBinding) {
        viewModel = getActivityViewModel(HomeViewModel::class.java)!!
        binding.lifecycleOwner = viewLifecycleOwner
        this.binding = binding
        initNewsList()
        fetchCatImages()
    }

    @ExperimentalPagingApi
    private fun fetchCatImages() {
        viewModel.fetchCatImages().observe(viewLifecycleOwner, {
            fetchJob?.cancel()
            fetchJob = lifecycleScope.launch {
                adapter.submitData(it)
            }
        })
    }

    private fun initNewsList() {
        val gridLayoutManager = GridLayoutManager(activity, 3)
        binding.homeRecycler.layoutManager = gridLayoutManager
        val dimension: Float? = context?.resources?.getDimension(R.dimen.dimen_4dp)
        dimension?.let {
            binding.homeRecycler.addItemDecoration(AdapterSpaceDecoration(dimension.toInt()))
        }
        adapter = HomeContentAdapter(binding.homeRecycler, this)
        binding.homeRecycler.adapter = adapter

//        binding.homeRecycler.adapter = adapter.withLoadStateFooter(
//            footer = LoadingStateAdapter()
//        )
//        adapter.addLoadStateListener { loadState ->
//            val errorState = loadState.source.append as? LoadState.Error
//                ?: loadState.source.prepend as? LoadState.Error
//                ?: loadState.append as? LoadState.Error
//                ?: loadState.prepend as? LoadState.Error
//
//            errorState?.let {
//                AlertDialog.Builder(view?.context)
//                    .setTitle(R.string.error)
//                    .setMessage(it.error.localizedMessage)
//                    .setNegativeButton(R.string.cancel) { dialog, _ ->
//                        dialog.dismiss()
//                    }
//                    .setPositiveButton(R.string.retry) { _, _ ->
//                        adapter.retry()
//                    }
//                    .show()
//            }
//        }
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
    }
}