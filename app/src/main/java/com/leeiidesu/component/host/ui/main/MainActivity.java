package com.leeiidesu.component.host.ui.main;


import android.os.Bundle;
import android.widget.TextView;

import com.leeiidesu.component.host.R;
import com.leeiidesu.component.host.base.DaggerActivity;
import com.leeiidesu.component.host.dagger.activity.ActivityComponent;
import com.leeiidesu.component.host.ui.login.LoginFragment;
import com.leeiidesu.lib.base.dagger.ActivityScope;

import javax.inject.Inject;

/**
 * Created by liyi on 2018/4/27.
 */
public class MainActivity extends DaggerActivity implements MainContract.IMainView {
    @Inject
    MainContract.IMainPresenter mPresenter;

    @Override
    protected void trySetupData(Bundle savedInstanceState) {
        String string = mPresenter.getString();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.content, new LoginFragment())
                .commit();


        TextView text = findViewById(R.id.text);
        text.setText(string);
    }

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_main;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }
}
