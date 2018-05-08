package com.leeiidesu.component.host.data;


import com.leeiidesu.component.host.data.api.APIService;
import com.leeiidesu.component.host.domain.DataRepository;

/**
 * Created by liyi on 2018/4/27.
 */

public class DataRepositoryImpl implements DataRepository {
    private APIService apiService;

    public DataRepositoryImpl(APIService apiService) {
        this.apiService = apiService;
    }


    @Override
    public String append(String format) {
        return " DataRepositoryImpl - >" + format;
    }
}
