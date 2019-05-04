package com.lee.helper.advancedandroidhelper.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.lee.helper.advancedandroidhelper.R;
import com.lee.helper.advancedandroidhelper.utils.CommonUtil;

public class MyCircle extends View
{
    private final int RADIUS = 20; //半径20dp

    private Paint mPaint;

    private Paint textPaint;

    private int myColor = 0xffff0000;

    private int showCount=-1 ;

    private Rect mBound;

    private String[]name = new String[]{"我","的","晨","chen"};

    public MyCircle(Context context) {
        this(context,null);
    }

    public MyCircle(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        textPaint = new Paint();
        textPaint.setColor(context.getResources().getColor(R.color.advance_black));
        textPaint.setTextSize(CommonUtil.dp2px(getContext(),10));
        mBound = new Rect();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width  = 2 * CommonUtil.dp2px(getContext(),RADIUS);
        int height = 2 * CommonUtil.dp2px(getContext(),RADIUS);
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int cx = getWidth()/2; //相对坐标
        int cy = getHeight()/2;
        mPaint.setColor(myColor);
        canvas.drawCircle(cx,cy, CommonUtil.dp2px(getContext(),RADIUS),mPaint);
        String currentTxt = "C";

        if(showCount < 4 && showCount > -1) {
            currentTxt = name[showCount];
            textPaint.getTextBounds(currentTxt,0,currentTxt.length(),mBound);
            canvas.drawText(currentTxt, cx - mBound.width() / 2, cy + mBound.height() / 2, textPaint);
        }
    }

    public void setMyColor(int color){
        this.myColor = color;
        invalidate();
    }


    public void setShowCount(int showCount){
        this.showCount = showCount;
        invalidate();
    }
}
