package com.leeiidesu.component.host.dagger.application.module;


import com.leeiidesu.component.host.data.DataRepositoryImpl;
import com.leeiidesu.component.host.data.api.APIService;
import com.leeiidesu.component.host.domain.DataRepository;
import com.leeiidesu.lib.base.dagger.ModuleSingleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dgg on 2017/11/3.
 */
@Module
public class DataModule {
    @ModuleSingleton
    @Provides
    DataRepository providerRepository(APIService apiService) {
        return new DataRepositoryImpl(apiService);
    }

    public interface Exposes {
        DataRepository getRepository();
    }
}
