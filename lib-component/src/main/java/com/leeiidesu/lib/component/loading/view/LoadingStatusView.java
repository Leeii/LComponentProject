package com.leeiidesu.lib.component.loading.view;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.leeiidesu.component.widget.loading.DggLoadingView;
import com.leeiidesu.lib.base.loading.view.IStatusView;
import com.leeiidesu.lib.component.R;

/**
 * Created by dgg on 2017/11/7.
 */

public class LoadingStatusView implements IStatusView {
    private View loadingView;
    private DggLoadingView ivIcon;
    private TextView messageView;
    private int tag;
    private ObjectAnimator animator;


    public LoadingStatusView(Context context) {
        loadingView = LayoutInflater.from(context)
                .inflate(R.layout.layout_loading, null, false);
        messageView = loadingView.findViewById(R.id.tv_message);
        ivIcon = loadingView.findViewById(R.id.iv_icon);
    }

    @Override
    public View getView() {
        return loadingView;
    }

    @Override
    public void setTag(int i) {
        this.tag = i;
    }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public void setMessage(String s) {
        if (s != null)
            messageView.setText(s);
    }

    @Override
    public void setButtonText(String s) {

    }

    @Override
    public void startAnimation() {
        if (animator != null) animator.cancel();
        ivIcon.setStatus(DggLoadingView.Status.SHOWING);
        animator = ObjectAnimator.ofInt(ivIcon, "progress", 0, 40);
        animator.setDuration(500);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animator.cancel();
                ivIcon.setStatus(DggLoadingView.Status.INFINITE);
                animator = ObjectAnimator.ofInt(ivIcon, "progress", 0, 100);
                animator.setInterpolator(new DecelerateInterpolator());
                animator.setDuration(1500);
                animator.setRepeatCount(-1);
                animator.start();
            }
        });
        animator.start();


    }

    @Override
    public void stopAnimation() {
        if (animator != null) {
            animator.removeAllListeners();
            animator.cancel();
            ivIcon.hide();
        }
    }

    @Override
    public void destroy() {
        loadingView = null;
        ivIcon = null;
        messageView = null;
    }
}
