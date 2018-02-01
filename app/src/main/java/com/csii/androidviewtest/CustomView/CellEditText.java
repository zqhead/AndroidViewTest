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
import android.view.MotionEvent;
import android.widget.EditText;

import com.csii.androidviewtest.Util.DimensionUtil;

/**
 * 自定义点格输入框
 * Created by zqhead on 2018/1/16.
 */

public class CellEditText extends EditText {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int maxLength;
    private int textLength;

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
        textLength = 0;
        maxLength = 6;
        setMinWidth(DimensionUtil.dip2px(getContext(), 300));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec) < DimensionUtil.dip2px(getContext(), 300) ?
                DimensionUtil.dip2px(getContext(), 300) :
        MeasureSpec.getSize(widthMeasureSpec);
        int height = width / maxLength;
        setMeasuredDimension(width, height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //外边框
        RectF rect = new RectF(0, 0, getWidth(), getHeight());
        mPaint.setColor(Color.GRAY);
        mPaint.setAlpha(128);
        canvas.drawRoundRect(rect,
                DimensionUtil.dip2px(getContext(), 5),
                DimensionUtil.dip2px(getContext(), 5),
                mPaint);

        //内框
        rect.set(DimensionUtil.dip2px(getContext(), 3),
                DimensionUtil.dip2px(getContext(), 3),
                getWidth() - DimensionUtil.dip2px(getContext(), 3),
                getHeight() - DimensionUtil.dip2px(getContext(), 3));
        mPaint.setColor(Color.WHITE);
        mPaint.setAlpha(255);
        canvas.drawRoundRect(rect,
                DimensionUtil.dip2px(getContext(), 5),
                DimensionUtil.dip2px(getContext(), 5),
                mPaint);

        //分割线
        mPaint.setColor(Color.GRAY);
        mPaint.setAlpha(128);
        mPaint.setStrokeWidth(DimensionUtil.dip2px(getContext(), 2));
        for (int i = 1; i < maxLength ; i++){
            canvas.drawLine(getWidth() * i / maxLength, 0,
                    getWidth() * i / maxLength, getHeight(),
                    mPaint);
        }

        //画点
        mPaint.setStyle(Paint.Style.FILL);
        for (int i = 1; i <= textLength ; i++){
            canvas.drawCircle(getWidth() * i / maxLength - getWidth()/(maxLength * 2),
                    getHeight() / 2,
                    getHeight() / 8,
                    mPaint);
        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        setTextLength(lengthAfter);
    }

    /**
     * 设置输入最大长度
     *
     * @param length 最大输入长度(最小值4)
     * */
    public void setMaxLength(int length){
        maxLength = length < 4 ? 4 : length;
    }

    /**
     * 设置输入长度
     *
     * @param length 设置输入长度 ，取值范围[0, maxLength]
     * */
    public void setTextLength(int length) {
        textLength = length > maxLength ? maxLength: (length < 0 ? 0 : length);
        invalidate();
    }

    /**
     * 获取当前text长度
     *
     * @return 返回当前textLength
     * */
    public int getTextLength() {
        return textLength;
    }

    /**
     * 设置最小宽度
     *
     * @param minWidth 最小宽度（不得小于300dp，否则强行修正）
     * */
    @Override
    public void setMinWidth(@Px int minWidth) {
        int widthDp = DimensionUtil.px2dip(getContext(), minWidth);
        widthDp = widthDp < 300 ? 300 : widthDp;
        super.setMinWidth(DimensionUtil.dip2px(getContext(), widthDp));
    }
}
