package com.leeiidesu.lib.component.dagger.component;


import com.leeiidesu.lib.component.dagger.module.ApplicationModule;
import com.leeiidesu.lib.component.dagger.module.RemoteModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dgg on 2017/11/10.
 */
@Singleton
@Component(modules = {
        RemoteModule.class,
        ApplicationModule.class
})
public interface GlobalComponent extends RemoteModule.Exposes {

}
