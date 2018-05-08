package com.leeiidesu.component.host.dagger.activity;


import com.leeiidesu.component.host.ui.main.MainActivity;
import com.leeiidesu.component.host.ui.main.MainPresenter;

public interface ActivityComponentInjects {

    void inject(MainActivity mainActivity);

    void inject(MainPresenter presenter);
}
