package com.leeiidesu.component.host.dagger.fragment;


import com.leeiidesu.component.host.base.DaggerFragment;
import com.leeiidesu.component.host.dagger.activity.ActivityComponent;
import com.leeiidesu.component.host.dagger.fragment.module.FragmentModule;
import com.leeiidesu.component.host.dagger.fragment.module.FragmentPresenterModule;
import com.leeiidesu.lib.base.dagger.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(
        dependencies = ActivityComponent.class,
        modules = {
                FragmentModule.class,
                FragmentPresenterModule.class
        }
)
public interface FragmentComponent extends FragmentComponentInjects, FragmentComponentExposes {

    final class Initializer {

        static public FragmentComponent init(final DaggerFragment fragment, final ActivityComponent activityComponent) {
            return DaggerFragmentComponent.builder()
                                          .activityComponent(activityComponent)
                                          .fragmentModule(new FragmentModule(fragment))
                                          .fragmentPresenterModule(new FragmentPresenterModule(fragment))
                                          .build();
        }

        private Initializer() {
        }
    }
}
