package com.leeiidesu.component.host.dagger.fragment.module;


import com.leeiidesu.component.host.base.DaggerFragment;
import com.leeiidesu.component.host.dagger.fragment.FragmentComponent;
import com.leeiidesu.component.host.ui.login.LoginContract;
import com.leeiidesu.component.host.ui.login.LoginPresenter;
import com.leeiidesu.component.host.ui.main.MainContract;
import com.leeiidesu.component.host.ui.main.MainPresenter;
import com.leeiidesu.lib.base.dagger.ActivityScope;
import com.leeiidesu.lib.base.dagger.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public final class FragmentPresenterModule {

    private final DaggerFragment daggerFragment;

    public FragmentPresenterModule(final DaggerFragment daggerFragment) {
        this.daggerFragment = daggerFragment;
    }

    @FragmentScope
    private FragmentComponent getFragmentComponent() {
        return daggerFragment.getFragmentComponent();
    }

    private DaggerFragment getDaggerFragment() {
        return daggerFragment;
    }

    @Provides
    @FragmentScope
    LoginContract.ILoginPresenter providerMainPresenter() {
        LoginPresenter presenter = new LoginPresenter();
        getFragmentComponent().inject(presenter);
        return presenter;
    }
}
