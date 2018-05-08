package com.leeiidesu.lib.component.loading.view;


import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.leeiidesu.lib.base.loading.callback.OnRetryClickListener;
import com.leeiidesu.lib.base.loading.view.IStatusView;
import com.leeiidesu.lib.component.R;

/**
 * Created by Administrator on 2017/11/8.
 */

public class NoNetworkStatusView implements IStatusView {

    private Context mContext;
    private OnRetryClickListener mListener;

    private View noNetworkView;
    private TextView messageView;
    private int tag;
    private Button retry;

    public NoNetworkStatusView(Context context, OnRetryClickListener listener) {
        this.mContext = context;
        this.mListener = listener;

        noNetworkView = LayoutInflater.from(context).inflate(R.layout.layout_no_network, null, false);
        messageView = noNetworkView.findViewById(R.id.tv_message);
        retry = noNetworkView.findViewById(R.id.btn_retry);
        retry.setOnClickListener(view -> {
            if (mListener != null)
                mListener.onRetry(view);
        });
        noNetworkView.findViewById(R.id.btn_setting).setOnClickListener(view -> mContext.startActivity(new Intent(Settings.ACTION_SETTINGS)));
    }

    @Override
    public View getView() {
        return noNetworkView;
    }

    @Override
    public void setTag(int tag) {
        this.tag = tag;
    }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public void setMessage(String message) {
        if (message != null)
            messageView.setText(message);
    }

    @Override
    public void setButtonText(String s) {
        if (s != null) retry.setText(s);
    }

    @Override
    public void startAnimation() {

    }

    @Override
    public void stopAnimation() {

    }

    @Override
    public void destroy() {

    }
}
