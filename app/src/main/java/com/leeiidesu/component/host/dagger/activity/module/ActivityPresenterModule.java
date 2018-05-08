package com.leeiidesu.component.host.dagger.activity.module;


import com.leeiidesu.component.host.base.DaggerActivity;
import com.leeiidesu.component.host.dagger.activity.ActivityComponent;
import com.leeiidesu.component.host.ui.main.MainContract;
import com.leeiidesu.component.host.ui.main.MainPresenter;
import com.leeiidesu.lib.base.dagger.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leeii on 2017/11/4.
 */
@Module
public class ActivityPresenterModule {
    private final DaggerActivity daggerActivity;

    public ActivityPresenterModule(DaggerActivity daggerActivity) {
        this.daggerActivity = daggerActivity;
    }

    public ActivityComponent getActivityComponent() {
        return daggerActivity.getActivityComponent();
    }

    @Provides
    @ActivityScope
    MainContract.IMainPresenter providerMainPresenter() {
        MainPresenter presenter = new MainPresenter();
        getActivityComponent().inject(presenter);
        return presenter;
    }


    public interface Exposes {
    }
}
