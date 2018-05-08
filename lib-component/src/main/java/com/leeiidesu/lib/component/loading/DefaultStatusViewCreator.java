package com.leeiidesu.lib.component.loading;


import android.content.Context;
import android.support.annotation.Nullable;

import com.leeiidesu.lib.base.loading.Status;
import com.leeiidesu.lib.base.loading.StatusViewCreator;
import com.leeiidesu.lib.base.loading.callback.OnRetryClickListener;
import com.leeiidesu.lib.base.loading.view.IStatusView;
import com.leeiidesu.lib.component.loading.view.EmptyStatusView;
import com.leeiidesu.lib.component.loading.view.ErrorStatusView;
import com.leeiidesu.lib.component.loading.view.LoadingStatusView;
import com.leeiidesu.lib.component.loading.view.NoNetworkStatusView;
import com.leeiidesu.lib.component.loading.view.TimeOutStatusView;

/**
 * Created by dgg on 2017/11/7.
 */

public class DefaultStatusViewCreator implements StatusViewCreator {
    @Override
    public IStatusView onCreateStatusView(Context context,
                                          Status status,
                                          @Nullable OnRetryClickListener onRetryClickListener) {

        IStatusView statusView = null;
        switch (status) {
            case ERROR:
                statusView = new ErrorStatusView(context, onRetryClickListener);
                break;
            case EMPTY:
                statusView = new EmptyStatusView(context, onRetryClickListener);
                break;
            case CUSTOM:
                statusView = new TimeOutStatusView(context, onRetryClickListener);
                break;
            case LOADING:
                statusView = new LoadingStatusView(context);
                break;
            case NETWORK:
                statusView = new NoNetworkStatusView(context, onRetryClickListener);
                break;
        }
        return statusView;
    }
}
