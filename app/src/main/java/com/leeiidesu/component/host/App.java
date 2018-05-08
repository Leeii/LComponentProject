package com.leeiidesu.component.host;


import com.leeiidesu.lib.component.ComponentApplication;

/**
 * Created by liyi on 2018/4/27.
 */

public class App extends ComponentApplication {

    @Override
    protected String getGlobalBaseUrl() {
        return "http://www.baidu.com";
    }

    @Override
    protected boolean isDebug() {
        return true;
    }
}
