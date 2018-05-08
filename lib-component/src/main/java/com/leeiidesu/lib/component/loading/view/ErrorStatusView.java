package com.leeiidesu.lib.component.loading.view;


import android.content.Context;
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

public class ErrorStatusView implements IStatusView {

    private Context context;
    private OnRetryClickListener mListener;

    private View errorView;
    private TextView messageView;
    private Button retry;
    private int tag;

    public ErrorStatusView(Context context, OnRetryClickListener listener) {
        this.context = context;
        this.mListener = listener;

        errorView = LayoutInflater.from(context).inflate(R.layout.layout_error, null, false);
        messageView = errorView.findViewById(R.id.tv_message);
        retry = errorView.findViewById(R.id.btn_retry);
        retry.setOnClickListener(view -> {
            if (mListener != null)
                mListener.onRetry(view);
        });
    }

    @Override
    public View getView() {
        return errorView;
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
