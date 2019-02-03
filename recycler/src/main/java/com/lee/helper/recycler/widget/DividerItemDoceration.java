package com.lee.helper.recycler.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.lee.helper.recycler.R;


public class DividerItemDoceration extends RecyclerView.ItemDecoration
{
    private final int DEFEAUT_HEIGHT = 1; //默认高度是1px
    private final int DEFEUT_COLOR = R.color.recycler_bfbfbf;
    private Context mContext;
    private int mHeight = DEFEAUT_HEIGHT;
    private int mColorRes = DEFEUT_COLOR;
    private boolean isShowBl; //是否显示底部分割线
    private int dividerCount; //分割线数量

    public DividerItemDoceration(Context mContext, int height , int colorRes ,boolean isShowBL){
        this.mContext = mContext;
        if(height > 0)this.mHeight = height;
        this.mColorRes = colorRes;
        this.isShowBl = isShowBL;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int count = parent.getChildCount();
        drawVerticalDecor(parent,c,left,right,count);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = mHeight;
    }

    //left ,right , top, bottom都是item坐标
    private void drawVerticalDecor(RecyclerView parent,Canvas canvas,int left ,int right , int count){
        Paint mPaint = new Paint();
        mPaint.setColor(mContext.getResources().getColor(mColorRes));
        if(isShowBl) dividerCount = count;
        else dividerCount = count -1;
        for (int i=0; i< dividerCount; i++){
            View view = parent.getChildAt(i);
            int top = view.getBottom();
            int bottom = view.getBottom()+ mHeight;
            canvas.drawRect(left,top,right,bottom,mPaint);
        }

    }


}
