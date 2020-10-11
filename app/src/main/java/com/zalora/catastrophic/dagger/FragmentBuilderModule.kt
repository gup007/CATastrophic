package com.zalora.catastrophic.dagger

import androidx.databinding.ViewDataBinding
import com.zalora.catastrophic.common.BaseFragment
import com.zalora.catastrophic.common.VMBaseFragment
import dagger.Binds
import dagger.Module

@Module
abstract class FragmentBuilderModule {

    @ModuleScope
    @Binds
    internal abstract fun bindBaseActivity(fragment: BaseFragment<ViewDataBinding>): BaseFragment<ViewDataBinding>

    @ModuleScope
    @Binds
    internal abstract fun bindVMBaseActivity(fragment: VMBaseFragment<*, *>): VMBaseFragment<*, *>

}
