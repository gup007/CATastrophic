package com.zalora.catastrophic.dagger;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.zalora.catastrophic.common.BaseActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ActivityModule {

    @Binds
    abstract BaseActivity provideActivity(BaseActivity activity);

    @Provides
    static Context provideContext(BaseActivity activity) {
        return activity.getApplicationContext();
    }

    @Provides
    static FragmentManager provideFragmentManager(BaseActivity activity) {
        return activity.getSupportFragmentManager();
    }
}
