package com.leeiidesu.lib.component.loading.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.leeiidesu.lib.base.loading.callback.OnRetryClickListener;
import com.leeiidesu.lib.base.loading.view.IStatusView;
import com.leeiidesu.lib.component.R;


/**
 * Created by liyi on 2018/1/23.
 */

public class TimeOutStatusView implements IStatusView {

    private OnRetryClickListener mListener;

    private View timeoutView;
    private TextView messageView;
    private int tag;

    public TimeOutStatusView(Context context, OnRetryClickListener listener) {
        this.mListener = listener;

        timeoutView = LayoutInflater.from(context).inflate(R.layout.layout_time_out, null, false);
        messageView = timeoutView.findViewById(R.id.tv_message);
        timeoutView.findViewById(R.id.btn_retry).setOnClickListener(view -> {
            if (mListener != null)
                mListener.onRetry(view);
        });
    }

    @Override
    public View getView() {
        return timeoutView;
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
