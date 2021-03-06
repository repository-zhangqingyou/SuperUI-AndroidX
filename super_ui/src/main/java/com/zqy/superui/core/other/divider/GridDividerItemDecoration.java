package com.zqy.superui.core.other.divider;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * 作者: zhangqingyou
 * 时间: 2020/12/11 14:27
 * 描述: GridLayoutManager 专用
 */
public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {
    private int spaceRL;//指定左右间隙宽度
    private int spaceTB;//指定上下间隙宽度
    private int color;
    private Paint mPaint;

    /**
     * 默认的，垂直方向 横纵1px 的分割线 颜色透明
     */
    public GridDividerItemDecoration() {
        this(1);
    }

    /**
     * 自定义宽度的透明分割线
     *
     * @param space 指定左右间隙宽度
     */
    public GridDividerItemDecoration(int space) {
        this(space, space, Color.TRANSPARENT);
    }


    /**
     * 自定义宽度，并指定颜色的分割线
     *
     * @param spaceRL 指定左右间隙宽度
     * @param spaceTB 指定上下间隙宽度
     */

    public GridDividerItemDecoration(int spaceRL, int spaceTB) {
        this(spaceRL, spaceTB, Color.TRANSPARENT);
    }

    /**
     * 自定义宽度，并指定颜色的分割线
     *
     * @param spaceRL 指定左右间隙宽度
     * @param spaceTB 指定上下间隙宽度
     * @param color   指定颜色
     */

    public GridDividerItemDecoration(int spaceRL, int spaceTB, int color) {
        this.spaceRL = spaceRL;
        this.spaceTB = spaceTB;
        this.color = color;
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(spaceRL);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        GridLayoutManager manager = (GridLayoutManager) parent.getLayoutManager();
        int childSize = parent.getChildCount();
        int span = manager.getSpanCount();
        //为了Item大小均匀，将设定分割线平均分给左右两边Item各一半
        int offset = spaceRL / 2;
        //得到View的位置
        int childPosition = parent.getChildAdapterPosition(view);
        //第一排，顶部不画
        if (childPosition < span) {
            //最左边的，左边不画
            if (childPosition % span == 0) {
                outRect.set(0, 0, offset, 0);
                //最右边，右边不画
            } else if (childPosition % span == span - 1) {
                outRect.set(offset, 0, 0, 0);
            } else {
                outRect.set(offset, 0, offset, 0);
            }
        } else {
            //上下的分割线，就从第二排开始，每个区域的顶部直接添加设定大小，不用再均分了
            if (childPosition % span == 0) {
                outRect.set(0, spaceTB, offset, 0);
            } else if (childPosition % span == span - 1) {
                outRect.set(offset, spaceTB, 0, 0);
            } else {
                outRect.set(offset, spaceTB, offset, 0);
            }
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }
}