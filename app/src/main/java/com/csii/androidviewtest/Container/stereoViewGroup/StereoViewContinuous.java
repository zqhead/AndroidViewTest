package com.csii.androidviewtest.Container.stereoViewGroup;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;

import com.csii.androidviewtest.Util.LogUtil;

/**
 *
 * 连续滚动使用一个新的类来实现
 * Created by zqhead on 2018/3/28.
 */

public class StereoViewContinuous extends ViewGroup {
    private int StartIndex;//起始状态显示第几个子控件

    private Camera mCamera;
    private Matrix mMatrix;
    private Scroller mScroller;
    /**
     * 触发滑动的最小距离
     * */
    private int minTouchSlop;

    /**
     * ACTION_DOWN时的X坐标
     * */
    private float mXDown;
    /**
     * ACTION_DOWN时的Y坐标
     * */
    private float mYDown;

    private int mWidth;
    private int mHeight;
    private int mCurrentItem;
    private VelocityTracker mVelocityTracker;
    private final int viewScrollTime = 800;
    private int flingSpeed = 2000;
    private int alreadyAdd = 0;

    private int standardSpeed = 2000;
    private boolean initializeMeasure = false;
    private boolean isAdding = false;
    private state mCurrState= state.NORMAL;
    private enum state {
        NORMAL,
        TOPRE,
        TONEXT
    }

    /**
     * 两个控件之间的夹角
     * */
    private int mAngle;

    /**
     * 表示touch动作是否被拦截的标识
     * */
    private boolean isCanFling;
    private int addCount;

    public StereoViewContinuous(Context context) {
        this(context, null);
    }

    public StereoViewContinuous(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StereoViewContinuous(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        minTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();// ViewConfiguration.get(context).getScaledTouchSlop() 用于获取滑动距离 它获得的是触发移动事件的最短距离
        mMatrix = new Matrix();
        mCamera = new Camera();
        mScroller = new Scroller(context, new AccelerateDecelerateInterpolator());

        StartIndex = 0;
        mCurrentItem = 0;
        mAngle = 90;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //移动到初始显示的控件
        if (!initializeMeasure) {
            scrollTo(0, StartIndex * getMeasuredHeight());
            initializeMeasure = true;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //if (changed) { //有坑！！如果只是变换顺序 changed还是false，会导致layout不发生变化
            int Top = 0;
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                child.layout(0, Top, getMeasuredWidth(), Top + child.getMeasuredHeight());
                Top += child.getHeight();
            }
        //}
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                isCanFling = false;
//                LogUtil.i("111111");
//                mXDown = ev.getX();
//                mYDown = ev.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                LogUtil.i("22222222");
//                float mXMove = ev.getX();
//                float mYMove = ev.getY();
//                isCanFling = isCanScrolling(mXMove, mYMove);
//                break;
//
//        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtil.i("111111");
                isCanFling = false;
                //记录down动作的坐标
                mXDown = ev.getX();
                mYDown = ev.getY();
                if (!mScroller.isFinished()) {
                    //当上一次滑动没有结束时，再次点击，强制滑动在点击位置结束
                    mScroller.setFinalY(mScroller.getCurrY());
                    mScroller.abortAnimation();
                    scrollTo(0, getScrollY());
                }
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.i("22222222");
                //记录move动作的坐标
                float mXMove = ev.getX();
                float mYMove = ev.getY();

                //计算viewGroup是否拦截事件
                isCanFling = isCanScrolling(mXMove, mYMove);
                if (isCanFling == true){
                    return isCanFling;
                }
                break;
        }

        return super.onInterceptTouchEvent(ev);
        //return isCanFling;
    }

    private boolean isCanScrolling(float mXMove, float mYMove) {
        //如果y方向的移动大于最小触发移动事件距离，并且Y轴距离大于x轴距离时，返回true
        if ((Math.abs(mYMove - mYDown) > minTouchSlop) && (Math.abs(mYMove - mYDown) > Math.abs(mXMove - mXDown))) {
            return true;
        } else return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //这里返回true，可以让事件能够正确被stereoView接收，即使子view不接受事件
                return true;
            case MotionEvent.ACTION_MOVE:
                //计算Y移动方向，然后根据方向，进行滚动
                float y = event.getY();
                float realDelta = mYDown - y;//scrollTo 和scrollBy y轴方向都是正数向上移动 复数向下移动，和屏幕坐标系正好相反，所以需要用起始点去减终点，这样才能适配
                mYDown = y;
                //进行滚动
                recycleYMove(realDelta);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                isCanFling = false;
                mVelocityTracker.computeCurrentVelocity(1000);

                //速度关系，x正表示向右，负表示向左， y正表示向下，复试表示向上
                float yCurrentSpeed = mVelocityTracker.getYVelocity();
                //速度大于标准速度，或者上一个显示的VIew显示的高度大于1/2，则表示向下滚动
                //if( yCurrentSpeed > standardSpeed || getScrollY() % mHeight < mHeight / 2) {((getScrollY() + mHeight / 2) / mHeight < mStartScreen)
                /**
                 * 这里使用startIndex的目的是，让现实的view适中处于StartIndex的位置，
                 * 例如：
                 * 如果startIndex为1，则当前现实的子view永远位于队列中1的位置，只要他的位置计算下来不是1，就进行topre或者tonext操作，保证永远是队列中位置为1的子view现实
                 * 同时startIndex相当于是控制Up操作的判定值
                 * */
                if( yCurrentSpeed > standardSpeed || ((getScrollY() + mHeight / 2) / mHeight < StartIndex)) {
                    mCurrState = state.TOPRE;
                    LogUtil.i("TOPRE",getScrollY() + "," + yCurrentSpeed );
                    //toPreAction(yCurrentSpeed);
                    toPreAction1(yCurrentSpeed);
                    //速度小于标准负速度,或者下一个显示的View显示的高度大于1/2，则表示向上滚动
                } else if(yCurrentSpeed < -standardSpeed || ((getScrollY() + mHeight / 2) / mHeight > StartIndex)) {
                    mCurrState = state.TONEXT;
                    LogUtil.i("TONEXT",getScrollY() + "," + yCurrentSpeed );
                    //toNextAction(yCurrentSpeed);
                    toNextAction1(yCurrentSpeed);
                } else {
                    //抬手时，根据现实的两个子VIew的位置就行 滑动复位
                    mCurrState = state.NORMAL;
                    int Y = getScrollY() % mHeight;
                    int dy = Y <= mHeight / 2 ? - Y : mHeight - Y;
                    LogUtil.i("NORMAL",getScrollY() + "," + Y + ","+ dy);
                    mScroller.startScroll(0, getScrollY(), 0, dy);
                    invalidate();
                }

                //当MVelocity使用完毕后，要进行回收处理，方便后续使用
                if(mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                break;

        }

        return super.onTouchEvent(event);
    }

    private void toPreAction1(float yVelocity) {
        int startY;
        int delta;
        int duration;
        addPre();//增加新的页面
        //计算松手后滑动的item个数
        int flingSpeedCount = (yVelocity - standardSpeed) > 0 ? (int) (yVelocity - standardSpeed) : 0;
        addCount = flingSpeedCount / flingSpeed + 1;
        //mScroller开始的坐标
        startY = getScrollY() + mHeight;
        setScrollY(startY);
        //mScroller移动的距离
        delta = -(startY - StartIndex * mHeight) - (addCount - 1) * mHeight;
        duration = viewScrollTime * addCount;
        mScroller.startScroll(0, startY, 0, delta, duration);
        addCount--;
    }

//    private void toPreAction(float yVelocity) {
//        addPre();
////        int flingSpeedCount = (yVelocity - standardSpeed) > 0 ? (int)(yVelocity - standardSpeed) : 0;
////        addCount = flingSpeedCount / flingSpeed + 1;
//        addCount = (int)yVelocity / standardSpeed + 1;//根据滑动的速度决定向下滚动的控件个数
//
//        int duration = viewScrollTime * addCount + 1;//计算滑动的持续时间
//        //int startY = getScrollY();
//        int startY = getScrollY() + mHeight;
//        setScrollY(startY);
//        int yDistance = -(startY % mHeight + mHeight * (addCount - 1));//计算滑动的距离
//
//        LogUtil.i("toPreAction",addCount + "," + duration + "," + yDistance);
//        mScroller.startScroll(0, startY, 0, yDistance, duration);
//        addCount--;
//        invalidate();
//    }

    private void toNextAction1(float yVelocity) {
        int startY;
        int delta;
        int duration;
        addNext();
        int flingSpeedCount = (Math.abs(yVelocity) - standardSpeed) > 0 ? (int) (Math.abs(yVelocity) - standardSpeed) : 0;
        addCount = flingSpeedCount / flingSpeed + 1;
        startY = getScrollY() - mHeight;
        setScrollY(startY);
        delta = mHeight * StartIndex - startY + (addCount - 1) * mHeight;
        LogUtil.m("后一页startY " + startY + " yVelocity " + yVelocity + " delta " + delta + "  getScrollY() " + getScrollY() + " addCount " + addCount);
        duration = viewScrollTime * addCount;
        mScroller.startScroll(0, startY, 0, delta, duration);
        addCount--;
    }

//    private void toNextAction(float yVelocity) {
//        addNext();
//        addCount = (int)(- yVelocity) / standardSpeed + 1;//根据滑动的速度决定向上滚动的控件个数
//
//        int duration = viewScrollTime * addCount;
//        int startY = getScrollY() - mHeight;
//        setScrollY(startY);
//        int yDistance = (mHeight - (startY % mHeight) + mHeight * (addCount - 1));
//
//        LogUtil.i("toNextAction",addCount + "," + duration + "," + yDistance);
//        mScroller.startScroll(0, startY, 0, yDistance, duration);
//        addCount--;
//        invalidate();
//    }

    private void recycleYMove(float realDelta) {
        int yDelta = (int)realDelta % mHeight;
        scrollBy(0, (int)yDelta);
        if (getScrollY() < 0) {
            LogUtil.i("22222222");
            addPre();
            scrollBy(0, mHeight);
        } else if( getScrollY() > mHeight *(getChildCount()- 1)) {
            LogUtil.i("3333333333");
            addNext();
            scrollBy(0, -mHeight);
        }
    }

    private void addPre() {
        int childCount = getChildCount();
        View lastChild = getChildAt(childCount - 1);
        removeViewAt(childCount - 1 );
        addView(lastChild, 0);
        mCurrentItem = ((mCurrentItem - 1) + getChildCount()) % getChildCount();

    }

    private void addNext() {
        int childCount = getChildCount();
        View lastChild = getChildAt(0);
        removeViewAt(0 );
        addView(lastChild, childCount - 1);
        mCurrentItem = (mCurrentItem + 1) % getChildCount();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (!isAdding) {
            for (int i = 0; i < getChildCount(); i++) {
                drawChildScreen(canvas, i , getDrawingTime());
            }
        } else {
            isAdding = false;
            super.dispatchDraw(canvas);
        }

    }


    private void drawChildScreen(Canvas canvas, int i , long drawingTime) {
        int childCurrentY = i * mHeight;
        //当子View的控件上边沿不在这两个范围之内，则说明子VIew不在可视范围之内，则不用绘制
        if (getScrollY() - mHeight >  childCurrentY) { //上一个child的上边沿边界最低值
            return;
        } else if (childCurrentY > getScrollY() + mHeight) { //下一个child的上边沿的最大值
            return;
        }
        //根据高度差值计算子VIew的旋转角度
        // 上一个子view的上边沿高于可视范围上沿（childCurrentY < getScrollY()），则角度为正 旋转中心为上一个子下边沿中点
        // 下一个子view的上沿该与可视范围的下沿（childCurrentY > getScrollY()），则角度为负 旋转中心为下一个子上边沿中点

        //计算两个子View的旋转角度 两个子View的旋转中心其实是相同的
        int currAngle = mAngle * (getScrollY() - childCurrentY) / mHeight;
        if (currAngle > 90 || currAngle < -90) {
            return;
        }
        //计算旋转中心
        int centreX = mWidth / 2;
        int centreY = childCurrentY < getScrollY() ? childCurrentY + mHeight : childCurrentY;

        //进行旋转
        mCamera.save();
        mCamera.rotateX(currAngle);
        mCamera.getMatrix(mMatrix);
        mCamera.restore();

        //移动旋转中心
        mMatrix.preTranslate( -centreX, -centreY);
        mMatrix.postTranslate( centreX,  centreY);

        //相乘计算好后的矩阵 然后绘制
        canvas.save();
        canvas.concat(mMatrix);
        drawChild(canvas, getChildAt(i), drawingTime);
        canvas.restore();

    }

        @Override
        public void computeScroll() {
        //滑动没有结束时，进行的操作
        if (mScroller.computeScrollOffset()) {
            if (mCurrState == state.TOPRE) {
                scrollTo(mScroller.getCurrX(), mScroller.getCurrY() + mHeight * alreadyAdd);
                if (getScrollY() < (mHeight + 2) && addCount > 0) {
                    isAdding = true;
                    addPre();
                    alreadyAdd++;
                    addCount--;
                }
            } else if (mCurrState == state.TONEXT) {
                scrollTo(mScroller.getCurrX(), mScroller.getCurrY() - mHeight * alreadyAdd);
                if (getScrollY() > (mHeight) && addCount > 0) {
                    isAdding = true;
                    addNext();
                    addCount--;
                    alreadyAdd++;
                }
            } else {
                //mState == State.Normal状态
                scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            }
            postInvalidate();
        }
        //滑动结束时相关用于计数变量复位
        if (mScroller.isFinished()) {
            addCount = 0;
            alreadyAdd = 0;
        }
    }

//    @Override
//    public void computeScroll() {
//        if (mScroller.computeScrollOffset()) {
//            if (mCurrState == state.TOPRE) {
//                scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
//                if (getScrollY() < mHeight && addCount > 0) {
//                    addPre();
//                    isAdding = true;
//                    addCount--;
//                }
//            } else if (mCurrState == state.TONEXT) {
//                scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
//                if (getScrollY() > mHeight && addCount > 0) {
//                    addNext();
//                    isAdding = true;
//                    addCount--;
//                }
//            } else {
//                scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
//            }
//            postInvalidate();
//        }
//        if (mScroller.isFinished()) {
//            addCount = 0;
//        }
//    }

    public void toPre() {

    }

    public void toNext() {

    }

    public void toSpecificItems (int items) {

    }
}
