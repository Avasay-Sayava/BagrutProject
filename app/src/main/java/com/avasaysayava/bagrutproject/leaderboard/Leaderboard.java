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

        // init all parameters
        this.rows = new ArrayList<>();
        this.marked = null;
    }

    // adds a row to the leaderboard
    private void add(String timeStr) {
        // create the relative layout that contains all of the time info
        RelativeLayout container = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams containerParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        container.setLayoutParams(containerParams);

        // create the rank box that shows the rank of the time
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

        // create the time box that contains the given time
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

        // add the row to be added
        this.rows.add(container);
    }

    // try to add a time in a given index to the leaderboard.
    // if it can't, it adds it to the end of it
    private void offer(String timeStr, int index) {
        if (index >= this.rows.size()) {
            add(timeStr);
            addView(this.rows.get(this.rows.size() - 1));
        } else {
            ((TextView) this.rows.get(index).getChildAt(1)).setText(timeStr);
        }
    }

    // update the rank num of the rows in the leaderboard
    public void update() {
        updateRanks();
    }

    // clear the leaderboard
    public void clear() {
        removeAllViews();
        this.rows.clear();
    }

    // updates all the ranks in the leaderboard according to their index
    private void updateRanks() {
        int place = 1;
        for (RelativeLayout rl : this.rows) {
            TextView rank = (TextView) rl.getChildAt(0);
            if (rank.getText().toString().isEmpty() ||
                    !rank.getText().toString().equals(String.valueOf(place)))
                rank.setText(String.valueOf(place));
            place++;
        }
    }

    // mark the given relative layout in white
    private void mark(RelativeLayout rl) {
        rl.setBackgroundColor(Color.WHITE);
        ((TextView) rl.getChildAt(0)).setTextColor(Color.BLACK);
        ((TextView) rl.getChildAt(1)).setTextColor(Color.BLACK);
    }

    // unmark the given relative layout
    private void unmark(RelativeLayout rl) {
        rl.setBackgroundColor(Color.TRANSPARENT);
        ((TextView) rl.getChildAt(0)).setTextColor(Color.WHITE);
        ((TextView) rl.getChildAt(1)).setTextColor(Color.WHITE);
    }

    // mark the given time in the leaderboard and unmark the last that has been marked
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

    // load the given level to the leaderboard
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

    // returns the marked row's y
    public int getMarkedY() {
        int sum = 0;
        for (RelativeLayout rl : this.rows) {
            if (rl == this.marked)
                break;
            sum += rl.getMeasuredHeight();
        }
        return sum;
    }

    // returns if the leaderboard is fully loaded by checking if
    // the real row count is equal to the child count
    public boolean isLoaded() {
        return this.rows.size() == getChildCount();
    }
}
