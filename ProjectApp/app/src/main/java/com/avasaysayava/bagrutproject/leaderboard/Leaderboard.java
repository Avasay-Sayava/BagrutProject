package com.avasaysayava.bagrutproject.leaderboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.database.datasource.LevelsDataSource;
import com.avasaysayava.bagrutproject.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard extends LinearLayout {
    private final List<RelativeLayout> rls;
    private RelativeLayout marked;

    public Leaderboard(Context context) {
        this(context, null);
    }

    public Leaderboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Leaderboard(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public Leaderboard(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        this.rls = new ArrayList<>();
        this.marked = null;
    }

    private void add(String timeStr) {
        RelativeLayout container = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams containerParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        container.setLayoutParams(containerParams);

        TextView rank = new TextView(getContext());
        RelativeLayout.LayoutParams rankParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        rankParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        rank.setLayoutParams(rankParams);
        rank.setTypeface(ResourcesCompat.getFont(getContext(), R.font.monospace));
        rank.setPadding(5, 0, 5, 0);
        rank.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        rank.setTextColor(Color.WHITE);
        rank.setTextSize(15);
        container.addView(rank);

        TextView time = new TextView(getContext());
        RelativeLayout.LayoutParams timeParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        timeParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        time.setLayoutParams(timeParams);
        time.setText(timeStr);
        time.setTypeface(ResourcesCompat.getFont(getContext(), R.font.monospace));
        time.setPadding(5, 0, 5, 0);
        time.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        time.setTextColor(Color.WHITE);
        time.setTextSize(15);
        container.addView(time);

        this.rls.add(container);
    }

    public void update() {
        removeAllViews();
        updateRanks();
        for (RelativeLayout rl : this.rls) {
            addView(rl);
        }
    }

    public void clear() {
        removeAllViews();
        this.rls.clear();
    }

    @SuppressLint("SetTextI18n")
    private void updateRanks() {
        int rank = 1;
        for (RelativeLayout rl : this.rls) {
            ((TextView) rl.getChildAt(0)).setText(rank++ + "");
        }
    }

    private void mark(RelativeLayout rl) {
        rl.setBackgroundColor(Color.WHITE);
        ((TextView) rl.getChildAt(0)).setTextColor(Color.BLACK);
        ((TextView) rl.getChildAt(1)).setTextColor(Color.BLACK);
    }

    private void unmark(RelativeLayout rl) {
        rl.setBackgroundColor(Color.TRANSPARENT);
        ((TextView) rl.getChildAt(0)).setTextColor(Color.WHITE);
        ((TextView) rl.getChildAt(1)).setTextColor(Color.WHITE);
    }

    public void markTime(String timeStr) {
        if (this.marked != null)
            unmark(this.marked);
        if (timeStr == null)
            return;
        for (RelativeLayout rl : this.rls) {
            if (((TextView) rl.getChildAt(1))
                    .getText().toString().equals(timeStr)) {
                mark(rl);
                this.marked = rl;
                return;
            }
        }
    }

    public void loadLevel(LevelsDataSource lds, int level) {
        lds.openReadable();
        List<Long> times = lds.getAllTimes(level);
        clear();
        for (long time : times) {
            add(Util.timeToString(time));
        }
    }

    public float getMarkedY() {
        int i = 0;
        if (marked != null) {
            for (RelativeLayout rl : this.rls) {
                if (rl == this.marked)
                    return i * 23.4f;
                i++;
            }
        }
        return 0;
    }
}
