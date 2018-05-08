package com.leeiidesu.component.widget.loading;


import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.leeiidesu.component.widget.R;

/**
 * 统一加载View
 * Created by liyi on 2018/1/18.
 */

public class DggLoadingView extends View {
    private Paint bgPaint;
    private Paint progressPaint;
    private Paint shadowPaint;

    private int progress;

    private float dp44;
    private float dp64;

    private RectF forceRectF;


    private Status status;
    private ObjectAnimator mAnimator;

    private Drawable drawable;
    private Rect drawableRect;

    public DggLoadingView(Context context) {
        this(context, null);
    }

    public DggLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        float density = getContext().getResources().getDisplayMetrics().density;
        dp64 = density * 64;
        dp44 = density * 44;
        drawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_shuaxin_blue);

        bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bgPaint.setColor(Color.WHITE);
        shadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        RadialGradient shader = new RadialGradient(dp64 / 2, dp64 / 2, dp64 / 2, Color.parseColor("#40000000"),
                Color.TRANSPARENT, Shader.TileMode.CLAMP);
        shadowPaint.setShader(shader);

        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setAntiAlias(true);
        progressPaint.setColor(Color.WHITE);
        int left = (int) ((dp64 - dp44) / 2);
        forceRectF = new RectF(left, left, left + dp44, left + dp44);

        int drawableStart = (int) ((dp64 - density * 36) / 2);
        int drawableEnd = (int) ((dp64 + density * 36) / 2);
        drawableRect = new Rect(drawableStart, drawableStart, drawableEnd, drawableEnd);
        drawable.setBounds(drawableRect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int i = MeasureSpec.makeMeasureSpec((int) dp64, MeasureSpec.EXACTLY);
        super.onMeasure(i, i);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        //画背景
        canvas.drawCircle(centerX, centerY, dp64 / 2, shadowPaint);
        canvas.drawCircle(centerX, centerY, dp44 / 2, bgPaint);


        //出现过程
        if (status == Status.SHOWING) {
            setScaleX(1);
            setScaleY(1);
            if (progress <= 40) {
                float input = progress / 40f;
                int rAngle = (int) (input * 360);
                canvas.save();
                canvas.rotate(rAngle, centerX, centerY);

                drawable.setAlpha((int) (255 * input));
                drawable.draw(canvas);
                float forceAngle = (input * 180) - 90;
                canvas.drawArc(forceRectF, forceAngle, 90 - forceAngle, true, progressPaint);
                canvas.drawArc(forceRectF, forceAngle + 180, 90 - forceAngle, true, progressPaint);
                canvas.restore();
            } else {
                float input = (progress - 40) / 60f;
                int rAngle = (int) (input * 180);
                canvas.save();
                canvas.rotate(rAngle, centerX, centerY);
                drawable.setAlpha(255);
                drawable.draw(canvas);
                canvas.restore();
            }
        } else if (status == Status.INFINITE) {
            setScaleX(1);
            setScaleY(1);
            float rAngle = progress * 360f * 2 / 100;
            canvas.save();
            canvas.rotate(rAngle, centerX, centerY);
            drawable.setAlpha(255);
            drawable.draw(canvas);
            canvas.restore();
        } else {
            canvas.save();
            if (progress <= 50) {
                float i = progress / 50f;//0-1
                canvas.scale(1 - i * 0.5f, 1 - i * 0.5f, centerX, centerY);
                setScaleX(1);
                setScaleY(1);
            } else {
                canvas.scale(1 - 0.5f, 1 - 0.5f, centerX, centerY);
            }

            float rAngle = progress * 168f / 50;
            canvas.rotate(rAngle, centerX, centerY);
            drawable.setAlpha(255);
            drawable.draw(canvas);
            canvas.restore();
            if (progress > 50) {
                float i = (100 - progress) / 50f;
                setScaleX(i);
                setScaleY(i);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setProgressColor(@ColorInt int color) {
        if (drawable != null) {
            drawable.setTint(color);
        }
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
        this.drawable.setBounds(drawableRect);
        postInvalidate();
    }

    public void setStatus(Status status) {
        this.status = status;
        setProgress(0);
    }

    public void setProgress(int progress) {
        this.progress = progress;
        postInvalidate();
    }

    public void hide() {
        setStatus(Status.HIDING);
        if (mAnimator != null) {
            mAnimator.cancel();
        }
        mAnimator = ObjectAnimator.ofInt(this, "progress", 0, 100);
        mAnimator.setRepeatCount(0);
        mAnimator.setDuration(350);
        mAnimator.start();
    }

    public void start() {
        setStatus(Status.INFINITE);
        if (mAnimator != null) {
            mAnimator.cancel();
        }
        mAnimator = ObjectAnimator.ofInt(this, "progress", 0, 100);
        mAnimator.setInterpolator(new DecelerateInterpolator());
        mAnimator.setRepeatCount(-1);
        mAnimator.setDuration(1500);

        mAnimator.start();
    }

    public boolean isShowing() {
        return status == Status.SHOWING;
    }

    public enum Status {
        SHOWING,
        INFINITE,
        HIDING
    }
}
