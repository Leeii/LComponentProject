package com.leeiidesu.component.host.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.leeiidesu.component.host.dagger.ComponentFactory;
import com.leeiidesu.component.host.dagger.fragment.FragmentComponent;
import com.leeiidesu.lib.base.common.BaseFragment;


/**
 * Created by Leeii on 2017/11/4.
 */

public abstract class DaggerFragment extends BaseFragment {
    private FragmentComponent fragmentComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(getFragmentComponent());
    }

    protected abstract void inject(FragmentComponent fragmentComponent);

    public FragmentComponent getFragmentComponent() {
        if (fragmentComponent == null) {
            fragmentComponent = ComponentFactory.createFragmentComponent(this, getDaggerActivity().getActivityComponent());
        }

        return fragmentComponent;
    }

    private DaggerActivity getDaggerActivity() {
        return ((DaggerActivity) getActivity());
    }
}
