package com.csii.androidviewtest.Container.stereoViewGroup;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.csii.androidviewtest.Util.LogUtil;

/**
 * Created by zqhead on 2018/3/20.
 */

public class StereoView extends ViewGroup {
    private int index;

    private Camera mCamera;
    private Matrix mMatrix;
    private Scroller mScroller;

    private int minTouchSlop;

    private float mXDown;
    private float mYDown;

    private int mWidth;
    private int mHeight;
    private int mCurrentItem;
    private VelocityTracker mVelocityTracker;

    private boolean isCanFling;

    public StereoView(Context context) {
        this(context, null);
    }

    public StereoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StereoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        minTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();// ViewConfiguration.get(context).getScaledTouchSlop() 用于获取滑动 它获得的是触发移动事件的最短距离
        mMatrix = new Matrix();
        mCamera = new Camera();
        mScroller = new Scroller(context);

        index = 1;
        mCurrentItem = 0;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //scrollTo(0, index * getMeasuredHeight());
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
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isCanFling = false;
                LogUtil.i("111111");
                mXDown = ev.getX();
                mYDown = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                float mXMove = ev.getX();
                float mYMove = ev.getY();
                isCanFling = isCanScrolling(mXMove, mYMove);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                LogUtil.i("111111");
//                mXDown = ev.getX();
//                mYDown = ev.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                LogUtil.i("22222222");
//                float mXMove = ev.getX();
//                float mYMove = ev.getY();
//                return isCanScrolling(mXMove, mYMove);
//        }
//        return super.onInterceptTouchEvent(ev);
        return isCanFling;
    }

    private boolean isCanScrolling(float mXMove, float mYMove) {
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
            case MotionEvent.ACTION_MOVE:

                float y = event.getY();
                float realDelta = mYDown - y;//scrollTo 和scrollBy y轴方向都是正数向上移动 复数向下移动，和屏幕坐标系正好相反，所以需要用起始点去减终点，这样才能适配
                mYDown = y;
                recycleYMove(realDelta);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                int currentIndex = (getScrollY() + mHeight / 2)/ mHeight;
                int dy = currentIndex * mHeight - getScrollY();
                mScroller.startScroll(0, getScrollY(), 0, dy);
                invalidate();
                break;

        }

        return super.onTouchEvent(event);
    }

    private void recycleYMove(float realDelta) {
        int yDelta = (int)realDelta % mHeight;
        scrollBy(0, (int)yDelta);
        if (getScrollY() < 5) {
            LogUtil.i("22222222");
            addPre();
            scrollBy(0, mHeight);
        } else if( getScrollY() > mHeight *(getChildCount()- 1) - 5) {
            LogUtil.i("3333333333");
            addNext();
            scrollBy(0, -mHeight);
        }

//        realDelta = realDelta % mHeight;
//        //delta = (int) (delta / resistance);
//        if (Math.abs(realDelta) > mHeight / 4) {
//            return;
//        }
//        scrollBy(0, (int)realDelta);
//        if (getScrollY() < 5) {
//            addPre();
//            scrollBy(0, mHeight);
//        } else if (getScrollY() > (getChildCount() - 1) * mHeight - 5) {
//            addNext();
//            scrollBy(0, -mHeight);
//        }
    }

//    private void addPre() {
//        int childCount = getChildCount();
//        View lastChild = getChildAt(childCount - 1);
//        removeViewAt(childCount - 1 );
//        addView(lastChild, 0);
//        mCurrentItem = ((mCurrentItem - 1) + getChildCount()) % getChildCount();
//
//    }
//
//    private void addNext() {
//        int childCount = getChildCount();
//        View lastChild = getChildAt(0);
//        removeViewAt(0 );
//        addView(lastChild, childCount - 1);
//    }

    /**
     * 把第一个item移动到最后一个item位置
     */
    private void addNext() {
        //mCurScreen = (mCurScreen + 1) % getChildCount();
        int childCount = getChildCount();
        LogUtil.i(childCount);
        View view = getChildAt(0);
        removeViewAt(0);

        addView(view, childCount - 1);
//        if (iStereoListener != null) {
//            iStereoListener.toNext(mCurScreen);
//        }
    }

    /**
     * 把最后一个item移动到第一个item位置
     */
    private void addPre() {
        //mCurScreen = ((mCurScreen - 1) + getChildCount()) % getChildCount();
        int childCount = getChildCount();
        LogUtil.i(childCount);
        View view = getChildAt(childCount - 1);
        removeViewAt(childCount - 1);
        LogUtil.i(getChildCount());
        addView(view, 0);
        LogUtil.i(getChildCount());
//        if (iStereoListener != null) {
//            iStereoListener.toPre(mCurScreen);
//        }
    }

    @Override
    public void computeScroll() {
        if (!mScroller.isFinished()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
