package com.csii.androidviewtest.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.csii.androidviewtest.BaseActivity;
import com.csii.androidviewtest.R;
import com.csii.androidviewtest.TestAndPractice.TouchEvent.TestViewTouchEventOne;
import com.csii.androidviewtest.Util.LogUtil;

public class TouchEventTestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event_test);

        TestViewTouchEventOne view = (TestViewTouchEventOne) findViewById(R.id.touch1);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtil.i("TouchEventTestActivity","setOnTouchListener");
                return false;
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.i("TouchEventTestActivity","setOnClickListener");
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.i("\n\n");
        return super.dispatchTouchEvent(ev);
    }
}
