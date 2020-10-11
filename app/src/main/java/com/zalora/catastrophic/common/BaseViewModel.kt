package com.zalora.catastrophic.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.zalora.catastrophic.rest.exception.APIException
import com.zalora.catastrophic.R
import javax.inject.Inject


open class BaseViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var mResourceProvider: ResourceProvider

    val apiException = MutableLiveData<APIException>()

    interface BindableAdapter<T> {
        fun setData(data: T?)
    }

    companion object {

        @JvmStatic
        @BindingAdapter("data")
        fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, data: T) {
            if (recyclerView.adapter is BindableAdapter<*>) {
                (recyclerView.adapter as BindableAdapter<T>).setData(data)
            }
        }

        @JvmStatic
        @BindingAdapter("data")
        fun <T> setViewPagerProperties(viewPager: ViewPager, data: T) {
            if (viewPager.adapter is BindableAdapter<*>) {
                (viewPager.adapter as BindableAdapter<T>).setData(data)
            }
        }

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, imageUrl: String?) {
            if (imageUrl == null) {
                return
            }
            ImageCaching(view.context)
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher).into(view)
        }

        @JvmStatic
        @BindingAdapter("visibility")
        fun loadImage(view: View, visibility: Int?) {
            if (visibility == null) {
                return
            }
            view.visibility = visibility
        }

    }
}