package com.leeiidesu.component.host;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.leeiidesu.component.host.dagger.ComponentFactory;
import com.leeiidesu.component.host.dagger.application.ApplicationComponent;
import com.leeiidesu.lib.component.IApplicationLike;
import com.leeiidesu.lib.component.router.RouterService;

/**
 * Created by iilee on 2018/5/8.
 */
@Route(path = RouterService.Host.APPLICATION_LIKE)
public class HostApplicationLike extends IApplicationLike {

    private ApplicationComponent applicationComponent;

    public ApplicationComponent getApplicationComponent() {
        if (applicationComponent == null) {
            applicationComponent = ComponentFactory.createApplicationComponent(this);
            applicationComponent.inject(this);
        }
        return applicationComponent;
    }

}
