package com.leeiidesu.component.host.ui.main;


import javax.inject.Inject;

/**
 * Created by liyi on 2018/4/27.
 */
public class MainPresenter implements MainContract.IMainPresenter {
    @Inject
    MainContract.IMainView mView;

    @Override
    public String getString() {
        mView.showToast("hhhh");

        return "jjjj";
    }
}
