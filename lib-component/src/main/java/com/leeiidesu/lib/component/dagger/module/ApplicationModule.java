package com.leeiidesu.lib.component.dagger.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dgg on 2017/11/10.
 */
@Module
public class ApplicationModule {
    private Application application;
    private String globalBaseUrl;

    public ApplicationModule(Application application, String globalBaseUrl) {
        this.application = application;
        this.globalBaseUrl = globalBaseUrl;
    }

    @Singleton
    @Provides
    Application providerApplication() {
        return application;
    }

    @Singleton
    @Provides
    String providerBaseUrl() {
        return globalBaseUrl;
    }
}
