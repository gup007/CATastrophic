package com.zalora.catastrophic

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.zalora.catastrophic.home.room.Cat


class DetailActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val cat: Cat? = intent.getParcelableExtra(KEY_CAT_DATA)
        val imageView = findViewById<ImageView>(R.id.detail_image)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.transitionName = cat?.id
        }
        val callback = object : Callback {
            override fun onSuccess() {
                supportStartPostponedEnterTransition()
            }

            override fun onError(e: Exception?) {
                supportStartPostponedEnterTransition()
            }
        }
        Picasso.get().load(cat?.url!!).noFade().into(imageView, callback)
    }

    companion object {
        const val KEY_CAT_DATA = "cat_data"
    }
}