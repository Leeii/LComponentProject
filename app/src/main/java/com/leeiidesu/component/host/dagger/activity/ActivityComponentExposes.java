package com.leeiidesu.component.host.dagger.activity;


import com.leeiidesu.component.host.dagger.activity.module.ActivityModule;
import com.leeiidesu.component.host.dagger.activity.module.ActivityPresenterModule;
import com.leeiidesu.component.host.dagger.application.ApplicationComponentExposes;

public interface ActivityComponentExposes extends ApplicationComponentExposes,
        ActivityModule.Exposes,
        ActivityPresenterModule.Exposes {


}
