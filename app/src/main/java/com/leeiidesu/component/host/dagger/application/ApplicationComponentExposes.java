package com.leeiidesu.component.host.dagger.application;


import com.leeiidesu.component.host.dagger.application.module.ApplicationLikeModule;
import com.leeiidesu.component.host.dagger.application.module.DataModule;
import com.leeiidesu.component.host.dagger.application.module.SampleRemoteModule;
import com.leeiidesu.component.host.dagger.application.module.UseCaseModule;

public interface ApplicationComponentExposes extends
        UseCaseModule.Exposes,
        DataModule.Exposes,
        SampleRemoteModule.Exposes,
        ApplicationLikeModule.Exposes {

}
