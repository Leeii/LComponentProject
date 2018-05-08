package com.leeiidesu.component.host.dagger;


import com.leeiidesu.component.host.HostApplicationLike;
import com.leeiidesu.component.host.base.DaggerActivity;
import com.leeiidesu.component.host.base.DaggerFragment;
import com.leeiidesu.component.host.dagger.activity.ActivityComponent;
import com.leeiidesu.component.host.dagger.application.ApplicationComponent;
import com.leeiidesu.component.host.dagger.fragment.FragmentComponent;

/**
 * Created by dgg on 2017/11/2.
 */

public class ComponentFactory {
    private ComponentFactory() {
    }

    public static ApplicationComponent createApplicationComponent(final HostApplicationLike application) {
        return ApplicationComponent.Initializer.init(application);
    }

    public static ActivityComponent createActivityComponent(final DaggerActivity daggerActivity, final HostApplicationLike hostApplicationLike) {
        return ActivityComponent.Initializer.init(daggerActivity, hostApplicationLike.getApplicationComponent());
    }

    public static FragmentComponent createFragmentComponent(final DaggerFragment daggerFragment, final ActivityComponent activityComponent) {
        return FragmentComponent.Initializer.init(daggerFragment, activityComponent);
    }
}
