package com.leeiidesu.component.host.ui.login;


import com.leeiidesu.component.host.domain.LoginUseCase;
import com.leeiidesu.lib.base.dagger.FragmentScope;

import javax.inject.Inject;

/**
 * Created by liyi on 2018/4/27.
 */
public class LoginPresenter implements LoginContract.ILoginPresenter {

    @Inject
    LoginContract.ILoginView mView;
    @Inject
    LoginUseCase loginUseCase;

    public LoginPresenter() {
    }

    @Override
    public void login(String string, String string1) {

        mView.showToast(loginUseCase.execute(string, string1));
    }
}
