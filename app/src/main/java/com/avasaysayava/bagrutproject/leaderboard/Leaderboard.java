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
import com.avasaysayava.bagrutproject.database.datasource.TimesDataSource;
import com.avasaysayava.bagrutproject.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard extends LinearLayout {
    private final List<RelativeLayout> rows;
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

        this.rows = new ArrayList<>();
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

        this.rows.add(container);
    }

    private void offer(String timeStr, int index) {
        if (index >= this.rows.size()) {
            add(timeStr);
            addView(this.rows.get(this.rows.size() - 1));
        } else {
            ((TextView) this.rows.get(index).getChildAt(1)).setText(timeStr);
        }
    }

    public void update() {
        updateRanks();
    }

    public void clear() {
        removeAllViews();
        this.rows.clear();
    }

    @SuppressLint("SetTextI18n")
    private void updateRanks() {
        int place = 1;
        for (RelativeLayout rl : this.rows) {
            TextView rank = (TextView) rl.getChildAt(0);
            if (rank.getText().toString().isEmpty() ||
                    !rank.getText().toString().equals(place + ""))
                rank.setText(place + "");
            place++;
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
        for (RelativeLayout rl : this.rows) {
            if (((TextView) rl.getChildAt(1))
                    .getText().toString().equals(timeStr)) {
                mark(rl);
                this.marked = rl;
                return;
            }
        }
    }

    public void loadLevel(TimesDataSource lds, int level) {
        lds.openReadable();
        List<Long> times = lds.getAllTimes(level);
        for (int i = 0; i < times.size(); i++) {
            offer(Util.timeToString(times.get(i)), i);
        }
        if (times.size() < this.rows.size()) {
            for (int i = times.size(); i < this.rows.size(); i++)
                removeView(this.rows.get(i));
            this.rows.removeAll(this.rows.subList(times.size(), this.rows.size()));
        }
    }

    public int getMarkedY() {
        int sum = 0;
        for (RelativeLayout rl : this.rows) {
            if (rl == this.marked)
                break;
            sum += rl.getMeasuredHeight();
        }
        return sum;
    }

    public boolean isLoaded() {
        return this.rows.size() == getChildCount();
    }
}
