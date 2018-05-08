package com.leeiidesu.component.host.dagger.fragment.module;


import com.leeiidesu.component.host.base.DaggerFragment;
import com.leeiidesu.component.host.ui.login.LoginContract;
import com.leeiidesu.component.host.ui.main.MainContract;
import com.leeiidesu.lib.base.dagger.ActivityScope;
import com.leeiidesu.lib.base.dagger.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private final DaggerFragment fragment;

    public FragmentModule(final DaggerFragment fragment) {
        this.fragment = fragment;
    }

    public DaggerFragment getDaggerFragment() {
        return fragment;
    }


    @Provides
    @FragmentScope
    LoginContract.ILoginView providerLoginView() {
        return (LoginContract.ILoginView) getDaggerFragment();
    }

}
