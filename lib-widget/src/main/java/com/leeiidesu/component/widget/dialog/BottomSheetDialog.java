package com.leeiidesu.component.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.leeiidesu.component.widget.R;
import com.leeiidesu.component.widget.adapter.OnItemClickListener;
import com.leeiidesu.component.widget.adapter.SimpleItemAdapter;
import com.leeiidesu.lib.core.android.UIUtil;
import com.leeiidesu.lib.core.util.Check;



/**
 * Created by dgg on 2017/10/16.
 */

public class BottomSheetDialog extends Dialog implements View.OnClickListener, OnItemClickListener {
    private final String[] labels;
    private RecyclerView mRecycler;
    private OnItemSelectedCallback onItemSelectedCallback;

    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public BottomSheetDialog(@NonNull Context context, String... labels) {
        super(context, R.style.ActionSheetDialogStyleTranslate);
        if (labels == null) {
            labels = new String[0];
        }
        this.labels = labels;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_for_ios_alert_rect);
        mRecycler = findViewById(R.id.recycler);
        findViewById(R.id.cancel).setOnClickListener(this);
        TextView titleText = findViewById(R.id.title);

        if (Check.isEmpty(title)) {
            titleText.setVisibility(View.GONE);
            findViewById(R.id.line).setVisibility(View.GONE);
        } else {
            titleText.setText(title);
        }

        setCancelable(true);
        setCanceledOnTouchOutside(true);

        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        Adapter mAdapter = new Adapter(labels);
        mRecycler.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(this);

        ViewGroup.LayoutParams layoutParams = mRecycler.getLayoutParams();
        if (labels.length > 9) {
            layoutParams.height = UIUtil.dipToPx(getContext(), 360);
        } else {
            layoutParams.height = -2;
        }
        mRecycler.setLayoutParams(layoutParams);
    }

    @Override
    public void show() {
        super.show();
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = -1;
        dialogWindow.setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

    @Override
    public void onItemClick(View itemView, int position) {
        if (onItemSelectedCallback != null) {
            onItemSelectedCallback.onItemSelected(position, labels[position]);
        }
        dismiss();
    }

    public void setOnItemSelectedCallback(OnItemSelectedCallback onItemSelectedCallback) {
        this.onItemSelectedCallback = onItemSelectedCallback;
    }

    private static class Adapter extends SimpleItemAdapter {

        private final String[] labels;

        Adapter(@NonNull String[] labels) {
            super(R.layout.item_dialog_for_ios);
            this.labels = labels;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            TextView text = holder.getViewAs(R.id.text);
            text.setBackgroundResource(R.drawable.sel_item_press_default);
            text.setTextColor(text.getContext().getResources().getColor(R.color.text_black));
            text.setText(labels[position]);
        }

        @Override
        public int getItemCount() {
            return labels.length;
        }
    }
}
