package com.leeiidesu.component.host.dagger.application.module;

import com.leeiidesu.component.host.domain.DataRepository;
import com.leeiidesu.component.host.domain.LoginUseCase;
import com.leeiidesu.lib.base.dagger.ModuleSingleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dgg on 2017/11/3.
 */
@Module
public class UseCaseModule {

    @Provides
    @ModuleSingleton
    LoginUseCase providerLoginUseCase(DataRepository sr) {
        return new LoginUseCase(sr);
    }


    public interface Exposes {
        LoginUseCase getLoginUseCase();

    }
}
