package com.leeiidesu.lib.component;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leeiidesu.component.widget.drawable.PlaceholderDrawable;
import com.leeiidesu.imageloader.glide.GlideImageEngine;
import com.leeiidesu.lib.base.ActivityManager;
import com.leeiidesu.lib.base.imageloader.ImageConfiguration;
import com.leeiidesu.lib.base.imageloader.ImageLoader;
import com.leeiidesu.lib.base.loading.LoadingHelperViewCreator;
import com.leeiidesu.lib.component.dagger.component.DaggerGlobalComponent;
import com.leeiidesu.lib.component.dagger.component.GlobalComponent;
import com.leeiidesu.lib.component.dagger.module.ApplicationModule;
import com.leeiidesu.lib.component.dagger.module.RemoteModule;
import com.leeiidesu.lib.component.domain.model.ModuleVersion;
import com.leeiidesu.lib.component.loading.DefaultStatusViewCreator;
import com.leeiidesu.lib.core.android.Logger;
import com.leeiidesu.lib.core.android.PreferencesHelper;
import com.leeiidesu.lib.core.util.Check;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组件化Application
 * Created by iilee on 2018/5/7.
 */

public abstract class ComponentApplication extends Application {
    private Map<String, IApplicationLike> lifecycleMap = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        LoadingHelperViewCreator.setDefaultStatusViewCreator(new DefaultStatusViewCreator());
        if (isDebug()) {
            ARouter.openLog();
            //线上需关闭 调试时可用，否则不能使用Instant run
            ARouter.openDebug();
        }
        ARouter.init(this);

        ImageLoader.getInstance().init(new GlideImageEngine(), new ImageConfiguration.Builder().placeholder(new PlaceholderDrawable()).build());


        PreferencesHelper.init(this);
        Logger.isPrint = isDebug();

        GlobalComponent mGlobalComponent = DaggerGlobalComponent.builder()
                .applicationModule(new ApplicationModule(this, getGlobalBaseUrl()))
                .remoteModule(new RemoteModule())
                .build();

        registerCallbacks(getActivityLifecycleCallbacks());

        //加载module中的appLike
        loadProjectApplicationLike();


        if (!Check.isEmpty(lifecycleMap)) {
            for (String name : lifecycleMap.keySet()) {
                lifecycleMap.get(name)
                        .onCreate(this, mGlobalComponent);
            }
        }
    }

    /**
     * 获取全局唯一baseURL
     *
     * @return
     */
    protected abstract String getGlobalBaseUrl();

    /**
     * 是否为调试模式
     *
     * @return isDebug
     */
    protected boolean isDebug() {
        return false;
    }

    /**
     * 加载所有ApplicationLike
     */
    private void loadProjectApplicationLike() {
        List<IApplicationLike> iApplicationLikes = fetchAllApplicationLike();

        for (IApplicationLike applicationLike : iApplicationLikes) {
            register(applicationLike);
        }
    }

    /**
     * 注册模块的ApplicationLike
     *
     * @param applicationLike 模块
     */
    private void register(IApplicationLike applicationLike) {
        if (applicationLike == null) {
            throw new IllegalArgumentException("application must not be null");
        }

        String canonicalName = applicationLike.getClass().getCanonicalName();

        lifecycleMap.put(canonicalName, applicationLike);
    }


    /**
     * 获取所有的ApplicationLike
     *
     * @return
     */
    private List<IApplicationLike> fetchAllApplicationLike() {
        List<IApplicationLike> applicationLikes = new ArrayList<>();

        BufferedReader reader = null;
        try {
            InputStream open = getAssets().open("library.json");
            reader = new BufferedReader(new InputStreamReader(open));
            StringBuilder builder = new StringBuilder();
            String temp;
            while ((temp = reader.readLine()) != null) {
                builder.append(temp);
            }

            JSONObject object = JSON.parseObject(builder.toString());

            List<ModuleVersion> library = JSON.parseArray(object.getString("library"), ModuleVersion.class);
            for (ModuleVersion mv :
                    library) {
                IApplicationLike like = (IApplicationLike) ARouter.getInstance().build(mv.getPath()).navigation();
                if (like != null) {
                    applicationLikes.add(like);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return applicationLikes;
    }

    /**
     * 创建一个Activity生命周期监听
     *
     * @return
     */
    protected ActivityLifecycleCallbacks getActivityLifecycleCallbacks() {
        return new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                ActivityManager.getInstance().push(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ActivityManager.getInstance().remove(activity);
            }
        };
    }

    /**
     * 注册 Activity 生命周期监听
     *
     * @param activityLifecycleCallbacks 生命周期监听
     */
    private void registerCallbacks(ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }


    public <T extends IApplicationLike> T fetchApplicationLike(Class<T> aClass) {
        if (aClass == null) {
            return null;
        }
        String className = aClass.getCanonicalName();
        return (T) lifecycleMap.get(className);
    }
}
