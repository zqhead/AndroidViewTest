package com.csii.androidviewtest.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.widget.EditText;

import com.csii.androidviewtest.Util.DimensionUtil;

/**
 * Created by zqhead on 2018/1/16.
 */

public class CellEditText extends EditText {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int textLength;
    public CellEditText(Context context) {
        this(context, null);
    }

    public CellEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public CellEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    public void initData(){
        setMinWidth(DimensionUtil.dip2px(getContext(), 300));
        textLength = 0;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec) < DimensionUtil.dip2px(getContext(), 300) ?
                DimensionUtil.dip2px(getContext(), 300) : MeasureSpec.getSize(widthMeasureSpec);
        int height = width / 6;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        //外边框
        RectF rect = new RectF(0, 0, getWidth(), getHeight());
        mPaint.setColor(Color.BLUE);
        mPaint.setAlpha(128);
        canvas.drawRoundRect(rect, 5, 5, mPaint);

        //内框
        rect.set(DimensionUtil.dip2px(getContext(), 10),
                DimensionUtil.dip2px(getContext(), 10),
                getWidth() - DimensionUtil.dip2px(getContext(), 10),
                getHeight() - DimensionUtil.dip2px(getContext(), 10));
        mPaint.setColor(Color.WHITE);
        mPaint.setAlpha(255);
        canvas.drawRoundRect(rect, 5, 5, mPaint);

        //分割线
        mPaint.setColor(Color.BLUE);
        mPaint.setAlpha(128);
        for (int i = 1; i < 6 ; i++){
            canvas.drawLine(getWidth() * i / 6, 0, getWidth() * i / 6, getHeight(),mPaint);
        }


    }

    @Override
    public void setMinWidth(@Px int minWidth) {
        int widthDp = DimensionUtil.px2dip(getContext(),minWidth);
        widthDp = widthDp < 300 ? 300 : widthDp;
        super.setMinWidth(DimensionUtil.dip2px(getContext(),widthDp));
    }
}
