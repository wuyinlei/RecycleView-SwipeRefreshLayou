package com.example.ruolan.recycleview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ruolan on 2015/11/11.
 */
public class MyItemDecoration extends RecyclerView.ItemDecoration{
    /**
     * 渲染itme的之前调用
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    /**
     * 渲染完成后调用
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    /**
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }
}
