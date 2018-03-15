package com.csii.androidviewtest.TestAndPractice.Matrix;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.csii.androidviewtest.R;
import com.csii.androidviewtest.Util.LogUtil;

/**
 * 测试camera的旋转 rotate方法
 * Created by zqhead on 2018/2/27.
 */

public class TestMatrixCameraTwo extends View {
    private Matrix mMatrix;
    private Camera mCamera;
    private Bitmap mBitmap;

    private float degree;

    private int mViewWidth;
    private int mViewHeight;

    private ValueAnimator mValueAnimator = ValueAnimator.ofFloat(0.0f, 360.0f);
    public TestMatrixCameraTwo(Context context) {
        super(context);
        init();
    }

    public TestMatrixCameraTwo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestMatrixCameraTwo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mCamera = new Camera();
        mMatrix = new Matrix();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.linglong);

        degree = 0;
        mValueAnimator.setDuration(5000);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float angle = (Float)animation.getAnimatedValue();
                degree = angle;
                invalidate();
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /**
         * Camera测试中的一些实践总结(只是根据现象做出一些总结，不一定正确)
         * 1，实践中发现，不管canvas或者camera怎么变换，只要当将camera进行融合时（camera的矩阵去乘canvas的matrix或者使用applyToCanvas）,
         *    都会将Camera的执行的变换对现有canvas再执行一遍，融合后canvas的原点讲和camera重合
         * 2，camera的translate和rotate都是对camera的本体进行操作 然后坐标系自动适应camera本体的变化，而产生变化（要形成空间概念来理解，这块的理解可以抽象为空间内物体不懂，视角的变换,但是效果上，还是Camera不懂，物体动）
         * 3，只要想要canvas的旋转中心相对于canvas的原点数值和 camera的坐标中操作的点数值相同， 融合后就能的到根据旋转中心旋转的效果，如本view的下面的例子
         * */
        //canvas.translate(mViewWidth / 2, mViewHeight / 2);
        //mMatrix = canvas.getMatrix();


        Matrix cMatrix = new Matrix();
        canvas.save();
        //camera是全局变量，所以他的状态如果不用save-restore这种方式保存，还原，则它每次调用的状态是上一次变换的状态，而非原始状态
        mCamera.save();
        //mCamera.translate(- mBitmap.getWidth() / 2,mBitmap.getHeight() / 2,0);
        LogUtil.i(mCamera.getLocationX() + "," + mCamera.getLocationY() + "," + mCamera.getLocationY());
        mCamera.rotateZ(degree);//感觉这个旋转和其他旋转有本质的区别
        mCamera.getMatrix(cMatrix);
        mCamera.restore();


        //这两行的目的是先前乘让旋转中心与原点重合，这样旋转才能以旋转中心为点进行旋转，之后再后乘让旋转中心回到原有位置
        cMatrix.preTranslate( -mViewWidth / 2 , - mViewHeight / 2);
        cMatrix.postTranslate(mViewWidth / 2 ,  mViewHeight / 2 );

        //canvas.setMatrix(mMatrix);
        canvas.concat(cMatrix);//用参数的矩阵去成canvas的矩阵 相当于将camera的之前的操作

        canvas.drawBitmap(mBitmap,  mViewWidth / 2 - mBitmap.getWidth() / 2, mViewHeight / 2 - mBitmap.getHeight() / 2 , new Paint());//实际的旋转中心为图片的中心点
        canvas.restore();
    }

    public void doAnimator(){
        mValueAnimator.start();
    }

}
