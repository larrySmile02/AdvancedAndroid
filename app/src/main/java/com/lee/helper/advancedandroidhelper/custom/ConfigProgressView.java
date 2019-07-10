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

import java.util.ArrayList;
import java.util.List;

public class ConfigProgressView extends View {
    private final int RADIUS = CommonUtil.dp2px(getContext(),14);
    private Paint mPaintGrey;
    private Paint mPaintBlue;
    private Paint mPaintWhite;
    private List<Integer> centerList = new ArrayList<>();
    private int ProcessIndex ;

    public ConfigProgressView(Context context) {
        this(context,null);
    }

    public ConfigProgressView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ConfigProgressView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        centerList.clear();
        int cy = getHeight()/2; //所有圆心的y坐标
        generateCx();
        for(int i = 0; i<centerList.size(); i++){
            if(i <= getProcessIndex()){
                drawOval(canvas,mPaintBlue,centerList.get(i),cy);
            }else {
                drawOval(canvas,mPaintGrey,centerList.get(i),cy);
            }

            drawText(canvas,1+i+"",centerList.get(i),cy);
        }

        for(int j = 0; j<centerList.size()-1; j++){
            drawLine(canvas,centerList.get(j),centerList.get(j+1),cy);
        }

    }

    //生成所有圆心的x坐标
    private void generateCx(){
        int cx1 = CommonUtil.dp2px(getContext(),14);
        centerList.add(cx1);
        int totalValidWith = getWidth() - CommonUtil.dp2px(getContext(),28);
        int cx2 = totalValidWith/4 + cx1;
        centerList.add(cx2);
        int cx3 = totalValidWith/4 + cx2;
        centerList.add(cx3);
        int cx4 = totalValidWith/4 + cx3;
        centerList.add(cx4);
        int cx5 = getWidth() - CommonUtil.dp2px(getContext(),14);
        centerList.add(cx5);
    }

    private void drawOval(Canvas canvas, Paint paint,int cx, int cy){
        canvas.drawCircle(cx*1f,cy*1f,CommonUtil.dp2px(getContext(),14),paint);
    }

    private void initPaint(){
        mPaintGrey = new Paint();
        mPaintGrey.setColor(getContext().getResources().getColor(R.color.advance_DDDDDD));
        mPaintBlue = new Paint();
        mPaintBlue.setColor(getContext().getResources().getColor(R.color.advance_5F7DB4));
        mPaintWhite = new Paint();
        mPaintWhite.setColor(getContext().getResources().getColor(R.color.advance_white));
        mPaintWhite.setTextSize(CommonUtil.dp2px(getContext(),14));
    }

    private void drawText(Canvas canvas, String s ,int cx ,int cy){
        Rect mBound = new Rect();
        mPaintWhite.getTextBounds(s,0,s.length(),mBound);
        canvas.drawText(s,cx-mBound.width()/2,cy+mBound.height()/2,mPaintWhite);
    }

    private void drawLine(Canvas canvas,int cxStart ,int cxEnd,int cy){
        canvas.drawRect(cxStart+CommonUtil.dp2px(getContext(),14)+3,cy - CommonUtil.dp2px(getContext(),1),
                cxEnd - CommonUtil.dp2px(getContext(),14)-3,cy + CommonUtil.dp2px(getContext(),1),mPaintGrey);
    }

    public int getProcessIndex() {
        return ProcessIndex;
    }

    public void setProcessIndex(int processIndex) {
        if(processIndex >= 0 && processIndex <= 4){
            ProcessIndex = processIndex;
            invalidate();
        }
    }
}
