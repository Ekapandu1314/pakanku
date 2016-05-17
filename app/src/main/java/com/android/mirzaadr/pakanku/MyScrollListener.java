package com.android.mirzaadr.pakanku;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by Eka Pandu Winata on 5/16/2016.
 */

public abstract class MyScrollListener extends RecyclerView.OnScrollListener {

    private static final float HIDE_THRESHOLD = 25;
    private static final float SHOW_THRESHOLD = 40;

    private int mToolbarOffset = 0;
    private boolean mControlsVisible = true;
    private int mToolbarHeight;

    public MyScrollListener(Context context) {
        mToolbarHeight = Utils.getToolbarHeight(context);
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if(newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (mControlsVisible) {
                if (mToolbarOffset > HIDE_THRESHOLD) {
                    setInvisible();
                } else {
                    setVisible();
                }
            } else {
                if ((mToolbarHeight - mToolbarOffset) > SHOW_THRESHOLD) {
                    setVisible();
                } else {
                    setInvisible();
                }
            }
            Log.d("Scroll 2", String.valueOf(mToolbarOffset) + " " + String.valueOf(mToolbarHeight) + " " + String.valueOf(HIDE_THRESHOLD) + " " + String.valueOf(SHOW_THRESHOLD));
        }

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        clipToolbarOffset();
        onMoved(mToolbarOffset);

        if((mToolbarOffset <mToolbarHeight && dy>0) || (mToolbarOffset >0 && dy<0)) {
            mToolbarOffset += dy;
        }

    }

    private void clipToolbarOffset() {
        if(mToolbarOffset > mToolbarHeight) {
            mToolbarOffset = mToolbarHeight;
        } else if(mToolbarOffset < 0) {
            mToolbarOffset = 0;
        }
    }

    private void setVisible() {
        if(mToolbarOffset > 0) {
            onShow();
            mToolbarOffset = 0;
        }
        mControlsVisible = true;
    }

    private void setInvisible() {
        if(mToolbarOffset < mToolbarHeight) {
            onHide();
            mToolbarOffset = mToolbarHeight;
        }
        mControlsVisible = false;
    }

    public abstract void onMoved(int distance);
    public abstract void onShow();
    public abstract void onHide();
}
