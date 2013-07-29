package com.bonesdev.newstoday.Library;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import com.bonesdev.newstoday.R;

/**
 * Created by jackey on 7/26/13.
 */
public class SampleListView extends ListView implements AbsListView.OnScrollListener{
    private String TAG = this.getClass().getSimpleName();
    ListView listView = this;
    View refreshheader;
    Context context;
    boolean isFreshing = false;
    OnListViewRefreshListener onRefreshListener = null;
    public SampleListView(Context context) {
        super(context);
        init(context);
    }

    public SampleListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void setOnRefreshListener(OnListViewRefreshListener listener) {
        this.onRefreshListener = listener;
    }

    public SampleListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.refreshheader = inflater.inflate(R.layout.pull_to_refresh_header, this, false);
        refreshheader.setOnClickListener(null);
        this.addHeaderView(refreshheader);
        refreshheader.findViewById(R.id.pull_to_refresh_text).setVisibility(View.GONE);
        this.setSelection(1);
        this.setOnScrollListener(this);
    }

    public void cleanHeader() {
        refreshheader.findViewById(R.id.pull_to_refresh_progress).setVisibility(View.GONE);
        refreshheader.findViewById(R.id.pull_to_refresh_text).setVisibility(View.GONE);
        isFreshing = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Log.d(TAG, "On touch");
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "ACTION UP");
                if (getFirstVisiblePosition() == 0 && isFreshing == false) {
                    Log.d(TAG, "It is first visible position" );
                    Log.d(TAG, "Add padding");
                    this.isFreshing = true;
                    refreshheader.findViewById(R.id.pull_to_refresh_text).setVisibility(View.GONE);
                    refreshheader.findViewById(R.id.pull_to_refresh_progress).setVisibility(View.VISIBLE);
                }
                if (this.isFreshing == true && this.onRefreshListener != null) {
                    this.onRefreshListener.onRefresh(listView);
                }
            break;
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "ACTION DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "ACTION MOVE");
                if (getFirstVisiblePosition() == 0 && isFreshing == false) {
                   refreshheader.findViewById(R.id.pull_to_refresh_text).setVisibility(View.VISIBLE);
                   refreshheader.findViewById(R.id.pull_to_refresh_progress).setVisibility(View.GONE);
                }
                applyPaddingHead(event);
                break;
        }
        return super.onTouchEvent(event);
    }

    public void applyPaddingHead(MotionEvent event) {
        int pointCounter = event.getHistorySize();
        Log.d(TAG, String.valueOf(pointCounter));
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        Log.d(TAG, "on scroll state change");
    }

    @Override
    public void onScroll(AbsListView view, int i, int i2, int i3) {
        Log.d(TAG, "on scroll");
    }

    public interface  OnListViewRefreshListener {
        abstract void onRefresh(ListView view);
    }
}
