package com.csii.androidviewtest.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.csii.androidviewtest.BaseActivity;
import com.csii.androidviewtest.R;
import com.csii.androidviewtest.TestAndPractice.TestDrawableOne;
import com.csii.androidviewtest.Util.LogUtil;

public class DrawableTestOneActivity extends BaseActivity {

    private TestDrawableOne testDrawableOne;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_test_one);
        testDrawableOne = (TestDrawableOne) findViewById(R.id.drawable_test_one);

        //测试补间动画使用
        animation = AnimationUtils.loadAnimation(DrawableTestOneActivity.this, R.anim.anim_drawable);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                LogUtil.i("setAnimationListener - onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                LogUtil.i("setAnimationListener - onAnimationEnd");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                LogUtil.i("setAnimationListener - onAnimationRepeat");
            }
        });

        Button button = (Button) findViewById(R.id.start_xml_animation);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testDrawableOne.startAnimation(animation);
            }
        });
    }
}
