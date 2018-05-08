package com.leeiidesu.component.host.dagger.application;


import com.leeiidesu.component.host.HostApplicationLike;
import com.leeiidesu.component.host.dagger.application.module.ApplicationLikeModule;
import com.leeiidesu.component.host.dagger.application.module.DataModule;
import com.leeiidesu.component.host.dagger.application.module.SampleRemoteModule;
import com.leeiidesu.component.host.dagger.application.module.UseCaseModule;
import com.leeiidesu.lib.base.dagger.ModuleSingleton;
import com.leeiidesu.lib.component.dagger.component.GlobalComponent;

import dagger.Component;

/**
 * Created by dgg on 2017/11/3.
 */
@ModuleSingleton
@Component(modules = {
        SampleRemoteModule.class,
        UseCaseModule.class,
        DataModule.class,
        ApplicationLikeModule.class
}, dependencies = GlobalComponent.class)
public interface ApplicationComponent extends ApplicationComponentExposes, ApplicationComponentInjects {
    final class Initializer {

        static public ApplicationComponent init(final HostApplicationLike applicationLike) {
            return DaggerApplicationComponent.builder()
                    .globalComponent(applicationLike.getGlobalComponent())
                    .useCaseModule(new UseCaseModule())
                    .applicationLikeModule(new ApplicationLikeModule(applicationLike))
                    .dataModule(new DataModule())
                    .sampleRemoteModule(new SampleRemoteModule())
                    .build();
        }

        private Initializer() {
        }
    }

}
