package com.csii.androidviewtest.TestAndPractice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zqhead on 2018/1/22.
 */

public class TestDrawPicture extends View {
    Picture mPicture;
    public TestDrawPicture(Context context) {
        this(context, null);
    }

    public TestDrawPicture(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestDrawPicture(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private void initData(){

        mPicture = new Picture();
        recording();
    }

    private void recording(){
        Canvas canvas = mPicture.beginRecording(100, 100);//个人理解，这里的width和height只是提供一个画布的大小的依据，为以后缩放提供基数，二不会影响超出范围的画布操作的执行和显示

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color. BLUE);
        paint.setStyle(Paint.Style.FILL);

        canvas.translate(100, 100);
        canvas.skew(1, 0);
        canvas.drawCircle(100, 100, 100, paint);

        mPicture.endRecording();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);//继承自view的onMeasure 所以wrap_context和 match_parent的效果相同，使用的都是MeasureSpec中size的尺寸
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //picture 绘制的三种方法
        //1,使用picture的draw方法绘制，就是把picture录制的东西画出来, 这个方法可能会改变canvas的状态，所以一般不会直接使用
        //mPicture.draw(canvas);

        //2,使用canvas的drawPicture进行绘制
        RectF rectF = new RectF(0,200,200,300);
        /*传入的recf并不是显示的区域，而是对draw的canvas进行的缩放变换
        * 前两个参数 用于将canvas平移的参数
        * 后两个参数会根据整个矩形width和height 与之前picture开始录制时设定的width 和height 进行相除
        *     得到的除数作为canvas缩放的依据
        * 在缩放后执行的还是 执行一个参数的drawPicture 在drawPicture里面执行的其实是传入的picture的draw方法，即上面的mPicture.draw(canvas);
        * 并且如果使用rectf，则在canvas前会进行save 使用后会restore进行还原
        * */
        canvas.drawPicture(mPicture,rectF);

        //3,将picture包装成PictureDrawable 使用PictureDrawable的draw进行绘制
        PictureDrawable drawable = new PictureDrawable(mPicture);
        //设置绘制范围，这个范围是mPicture的绘制完后显示的范围，这个方法不会对显示进行缩放，
        // 而是如果bounds的范围小于mPicture的范围，会对最后的显示进行裁剪，只显示bounds规定那部分范围大小的绘制
        drawable.setBounds(0, 0 ,200, 200);
        //此处setBounds是设置在画布上的绘制区域，并非根据该区域进行缩放，也不是剪裁Picture，每次都从Picture的左上角开始绘制
        drawable.draw(canvas);
    }
}
