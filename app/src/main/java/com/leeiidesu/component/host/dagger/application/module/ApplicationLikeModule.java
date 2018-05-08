package com.leeiidesu.component.host.dagger.application.module;


import android.app.Application;

import com.leeiidesu.component.host.HostApplicationLike;
import com.leeiidesu.lib.base.dagger.ModuleSingleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dgg on 2017/11/3.
 */
@Module
public class ApplicationLikeModule {

    private HostApplicationLike application;

    public ApplicationLikeModule(HostApplicationLike application) {
        this.application = application;
    }


    @Provides
    @ModuleSingleton
    Application providerApplication() {
        return application.getApplication();
    }

    @Provides
    @ModuleSingleton
    HostApplicationLike providerApplicationLike() {
        return application;
    }

    public interface Exposes {
        HostApplicationLike application();

        Application getApplication();
    }
}
