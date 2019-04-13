package com.lee.helper.advancedandroidhelper.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lee.helper.advancedandroidhelper.R;
import com.lee.helper.advancedandroidhelper.utils.CommonUtil;

public class HorizotalScrollTab extends HorizontalScrollView
{
    private String TAG = HorizotalScrollTab.class.getSimpleName();
    private ViewPager viewPager;
    private int lineWidth;//下滑线宽
    private int lineColor;//下滑线颜色


    private int DEFAUT_LINE_HEIGHT = 5;//默认线宽
    private LinearLayout viewContainer;
    private float globeOffset;//滑动率
    private int glodeselectedPos; //当前选项卡的位置

    private Paint linePaint;

    public HorizotalScrollTab(Context context) {
        this(context,null);
    }

    public HorizotalScrollTab(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HorizotalScrollTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.HorizotalScrollTab,defStyleAttr,0);
        int totalCount = a.getIndexCount();
        for(int index = 0; index < totalCount; index++){
            int attr = a.getIndex(index);
            switch (attr){
                case R.styleable.HorizotalScrollTab_custom_line_height:
                    lineWidth = a.getDimensionPixelSize(attr,DEFAUT_LINE_HEIGHT);
                    break;
                case R.styleable.HorizotalScrollTab_custom_line_color:
                    lineColor = a.getColor(attr, Color.RED);
                    break;
            }
        }
        a.recycle();
        linePaint = new Paint();
        lineColor = context.getResources().getColor(R.color.advance_red_little);
        linePaint.setColor(lineColor);
        viewContainer = new LinearLayout(context);
        viewContainer.setOrientation(LinearLayout.HORIZONTAL);
        viewContainer.setGravity(Gravity.CENTER_VERTICAL);
        FrameLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
       params.leftMargin = CommonUtil.dp2px(context,15);
       params.rightMargin = CommonUtil.dp2px(context,15);
        viewContainer.setLayoutParams(params);
        addView(viewContainer);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        View selectedView = viewContainer.getChildAt(glodeselectedPos);
        int lineStartX = (int) (selectedView.getLeft() + selectedView.getWidth() * globeOffset) + CommonUtil.dp2px(HorizotalScrollTab.this.getContext(),15);
        int lineLeftTop = getBottom() - lineWidth;
        int lineEndX = (int) (selectedView.getRight() + selectedView.getWidth() * globeOffset) +CommonUtil.dp2px(HorizotalScrollTab.this.getContext(),15);
        int lineBtm = getBottom();
        canvas.drawRect(lineStartX,lineLeftTop,lineEndX,lineBtm,linePaint);

    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        addChildrenView();
        registerListener();

    }

    private void addChildrenView(){
        if(viewPager != null){
            int childrenCount = viewPager.getAdapter().getCount();
           for(int j = 0; j< childrenCount; j++){
               TextView tv = new TextView(HorizotalScrollTab.this.getContext());
               String title = (String) viewPager.getAdapter().getPageTitle(j);
               tv.setText(title);
               tv.setTextSize(16);
               LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
               params.leftMargin = CommonUtil.dp2px(HorizotalScrollTab.this.getContext(),5);
               params.rightMargin = CommonUtil.dp2px(HorizotalScrollTab.this.getContext(),5);
               tv.setGravity(Gravity.CENTER);
               tv.setLayoutParams(params);
               viewContainer.addView(tv);
           }
        }
    }


    private void registerListener(){
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int pos, float offset, int offsetPixel) {
                globeOffset = offset;
                glodeselectedPos = pos;
                if(viewContainer.getChildCount() >= pos){
                    scrollToChild(pos,offset);
                    invalidate();
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void scrollToChild(int pos, float offset){
        View selectedView = viewContainer.getChildAt(pos);
        int selectedWidth = selectedView.getWidth();
        View nextView = null;
        int nextWidth = 0;
        if(viewContainer.getChildCount() > pos){
             nextView = viewContainer.getChildAt(pos);
             nextWidth = nextView.getWidth();
        }
        int startScrollX = selectedView.getLeft() + (selectedWidth / 2)- (HorizotalScrollTab.this.getWidth() / 2);
        int newScrollX = (int) ((selectedWidth + nextWidth)*offset /2 + startScrollX);
        scrollTo(startScrollX,0);

    }
}
