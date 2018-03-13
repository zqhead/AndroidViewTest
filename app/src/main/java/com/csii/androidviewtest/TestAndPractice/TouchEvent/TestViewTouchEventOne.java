package com.csii.androidviewtest.TestAndPractice.TouchEvent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.widget.ExploreByTouchHelper;
import android.text.method.Touch;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.csii.androidviewtest.Util.LogUtil;

/**
 * Created by zqhead on 2018/2/27.
 */

public class TestViewTouchEventOne extends View {
    public TestViewTouchEventOne(Context context) {
        this(context, null);
    }

    public TestViewTouchEventOne(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestViewTouchEventOne(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GREEN);

        canvas.drawColor(Color.GREEN);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogUtil.i("TestViewTouchEventOne", "dispatchTouchEvent" );
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.i("TestViewTouchEventOne", "onTouchEvent" );
        LogUtil.i("TestViewTouchEventOne", "X:"+event.getX()+ ", RawX:" + event.getRawX());
        LogUtil.i("TestViewTouchEventOne", "Y:"+event.getY()+ ", RawY:" + event.getRawY());
        super.onTouchEvent(event);
        //onTouchEvent中如果actionDown时返回true 才会接受后面move up等事件同时也将接受全部事件
        //返回false则不再接受任何事件
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            return true;
        } else {
            return false;//这里说明即使move或者up返回false，也不会影响消息的传递，只要down反悔了true 整个事件都将由这个View的onTouchEvent消费
        }
//        return false;
    }

    @Override
    public boolean performClick() {
        LogUtil.i("TestViewTouchEventOne", "performClick" );
        return super.performClick();
    }
}
