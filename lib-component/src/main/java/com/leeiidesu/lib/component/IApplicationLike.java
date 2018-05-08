package com.leeiidesu.lib.component;

import android.content.Context;
import android.support.annotation.CallSuper;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.leeiidesu.lib.component.dagger.component.GlobalComponent;

/**
 * module application生命周期
 * Created by iilee on 2018/5/7.
 */

public abstract class IApplicationLike implements IProvider {

    private ComponentApplication application;
    private GlobalComponent globalComponent;

    @CallSuper
    protected void onCreate(ComponentApplication application, GlobalComponent globalComponent) {
        this.application = application;
        this.globalComponent = globalComponent;
    }

    public final ComponentApplication getApplication() {
        return application;
    }

    public final GlobalComponent getGlobalComponent() {
        return globalComponent;
    }

    @Override
    public void init(Context context) {
    }

}
