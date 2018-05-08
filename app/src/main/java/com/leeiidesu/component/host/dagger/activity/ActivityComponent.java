package com.leeiidesu.component.host.dagger.activity;


import com.leeiidesu.component.host.base.DaggerActivity;
import com.leeiidesu.component.host.dagger.activity.module.ActivityModule;
import com.leeiidesu.component.host.dagger.activity.module.ActivityPresenterModule;
import com.leeiidesu.component.host.dagger.application.ApplicationComponent;
import com.leeiidesu.lib.base.dagger.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                ActivityModule.class,
                ActivityPresenterModule.class
        }
)
public interface ActivityComponent extends ActivityComponentInjects, ActivityComponentExposes {

    final class Initializer {

        static public ActivityComponent init(final DaggerActivity daggerActivity, final ApplicationComponent applicationComponent) {
            return DaggerActivityComponent.builder()
                    .applicationComponent(applicationComponent)
                    .activityPresenterModule(new ActivityPresenterModule(daggerActivity))
                    .activityModule(new ActivityModule(daggerActivity))
                    .build();
        }

        private Initializer() {
        }
    }
}
