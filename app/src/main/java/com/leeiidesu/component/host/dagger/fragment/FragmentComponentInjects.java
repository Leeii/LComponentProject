package com.leeiidesu.component.host.dagger.fragment;


import com.leeiidesu.component.host.ui.login.LoginContract;
import com.leeiidesu.component.host.ui.login.LoginFragment;
import com.leeiidesu.component.host.ui.login.LoginPresenter;
import com.leeiidesu.component.host.ui.main.MainContract;

public interface FragmentComponentInjects {

    void inject(LoginFragment loginFragment);

    void inject(LoginPresenter presenter);

}
