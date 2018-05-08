package com.leeiidesu.component.host.domain;

import javax.inject.Singleton;

/**
 * Created by liyi on 2018/4/27.
 */
@Singleton
public interface DataRepository {
    String append(String format);
}
