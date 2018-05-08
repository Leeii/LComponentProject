package com.leeiidesu.component.host.dagger.activity.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;


import com.leeiidesu.component.host.base.DaggerActivity;
import com.leeiidesu.component.host.ui.main.MainContract;
import com.leeiidesu.lib.base.dagger.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final DaggerActivity daggerActivity;

    public ActivityModule(final DaggerActivity daggerActivity) {
        this.daggerActivity = daggerActivity;
    }

    @Provides
    @ActivityScope
    Context provideActivityContext() {
        return daggerActivity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity() {
        return daggerActivity;
    }

    private DaggerActivity getDaggerActivity() {
        return daggerActivity;
    }

    @Provides
    @ActivityScope
    FragmentManager provideFragmentManager() {
        return daggerActivity.getSupportFragmentManager();
    }


    @Provides
    @ActivityScope
    MainContract.IMainView providerMainView() {
        return (MainContract.IMainView) getDaggerActivity();
    }


    public interface Exposes {

        Activity activity();

        Context context();

        FragmentManager fragmentManager();
    }
}
