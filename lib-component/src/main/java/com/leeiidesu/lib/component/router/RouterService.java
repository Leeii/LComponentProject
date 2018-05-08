package com.leeiidesu.lib.component.router;

import com.leeiidesu.lib.router.anno.Path;

import static com.leeiidesu.lib.component.router.RouterService.Task.TASK_LIST;

/**
 * Created by iilee on 2018/5/8.
 */

public interface RouterService {
    interface Host {
        String APPLICATION_LIKE = "/host/application/like";
    }

    interface Task {
        String APPLICATION_LIKE = "/task/application/like";
        String TASK_LIST = "/task/list";
    }

    @Path(TASK_LIST)
    void routeToTaskListActivity();
}
