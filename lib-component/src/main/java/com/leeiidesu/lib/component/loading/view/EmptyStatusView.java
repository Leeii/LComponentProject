package com.leeiidesu.lib.component.loading.view;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.leeiidesu.lib.base.loading.callback.OnRetryClickListener;
import com.leeiidesu.lib.base.loading.view.IStatusView;
import com.leeiidesu.lib.component.R;

/**
 * Created by Administrator on 2017/11/8.
 */

public class EmptyStatusView implements IStatusView {
    private OnRetryClickListener mListener;

    private View emptyView;
    private TextView messageView;
    private int tag;

    public EmptyStatusView(Context context, OnRetryClickListener listener) {
        this.mListener = listener;

        emptyView = LayoutInflater.from(context).inflate(R.layout.layout_empty, null, false);
        messageView = emptyView.findViewById(R.id.tv_message);
        emptyView.findViewById(R.id.btn_refresh).setOnClickListener(view -> {
            if (mListener != null)
                mListener.onRetry(view);
        });
    }

    @Override
    public View getView() {
        return emptyView;
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
