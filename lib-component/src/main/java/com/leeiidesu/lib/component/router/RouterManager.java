package com.leeiidesu.lib.component.router;

import com.leeiidesu.lib.router.DRouter;

/**
 * Created by iilee on 2018/5/8.
 */

public class RouterManager {


    private RouterService oaRouterService;

    private RouterManager() {
    }

    private static class Holder {
        private final static RouterManager INSTANCE = new RouterManager();
    }

    /**
     * 获取路由服务
     *
     * @return 路由服务
     */
    public static RouterService getService() {
        if (Holder.INSTANCE.oaRouterService == null) {
            synchronized (Holder.INSTANCE) {
                if (Holder.INSTANCE.oaRouterService == null) {
                    Holder.INSTANCE.oaRouterService = new DRouter().create(RouterService.class);
                }
            }
        }

        return Holder.INSTANCE.oaRouterService;
    }
}
