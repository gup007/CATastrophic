package com.zalora.catastrophic.common

import android.os.Bundle
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.zalora.catastrophic.dagger.ModuleActivityComponent
import com.zalora.catastrophic.dagger.ModuleRootActivity
import com.zalora.catastrophic.R
import com.zalora.catastrophic.coreComponent
import com.zalora.catastrophic.dagger.DaggerAppComponent
import com.zalora.catastrophic.databinding.ActivityBaseBinding
import com.zalora.catastrophic.rest.exception.APIException


abstract class BaseActivity : ModuleRootActivity() {

    val fragmentContainerId: Int
        get() = getContainerId()

    lateinit var baseBinding: ActivityBaseBinding

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseBinding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_base, null, false)
        setChildContentView(savedInstanceState, baseBinding.mainContainer, true)
        setContentView(baseBinding.root)
        supportActionBar?.hide()
        create(savedInstanceState)
    }

    override val moduleComponent: ModuleActivityComponent
        get() = DaggerAppComponent.builder().coreComponent(coreComponent()).build()

    abstract fun create(savedInstanceState: Bundle?)

    abstract fun setChildContentView(
        savedInstanceState: Bundle?,
        mainContainer: LinearLayout,
        addToParent: Boolean
    )

    open fun getContainerId(): Int {
        return -1
    }

    open var onError = Observer<APIException> {
        if (getContainerId() > 0) {
            // Handle Error
        }
    }

    open fun refreshTopFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(getContainerId())
        if (currentFragment != null) {
            supportFragmentManager.beginTransaction().detach(currentFragment)
                .attach(currentFragment).commitNow()
        }
    }

    fun replaceFragment(fragment: BaseFragment<*>, addToBackStack: Boolean) {
        if (getContainerId() == -1) {
            return
        }
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(getContainerId(), fragment, fragment.javaClass.simpleName)
        if (addToBackStack) {
            ft.addToBackStack(null)
        }
        ft.commit()
    }

    private fun isDestroyingActivity(): Boolean {
        return isFinishing && isDestroyed
    }
}