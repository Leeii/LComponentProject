package com.leeiidesu.component.host.dagger.application.module;



import com.leeiidesu.component.host.data.api.APIService;
import com.leeiidesu.lib.base.dagger.ModuleSingleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Leeii on 2017/11/3.
 */
@Module
public class SampleRemoteModule {
    @Provides
    @ModuleSingleton
    APIService providerApiService(Retrofit retrofit) {
        return retrofit
                .create(APIService.class);
    }

    public interface Exposes {
        APIService getApiService();
    }
}
