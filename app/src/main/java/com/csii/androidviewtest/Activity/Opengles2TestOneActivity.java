package com.csii.androidviewtest.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.AndroidCharacter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.csii.androidviewtest.OpenGLES.TestGLSurfaceView;
import com.csii.androidviewtest.R;
import com.csii.androidviewtest.Util.DimensionUtil;

public class Opengles2TestOneActivity extends AppCompatActivity implements View.OnClickListener{

    private TestGLSurfaceView glSurfaceView;
    private Button button;
    private ViewGroup viewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opengles2_test_one);

        View decorView  = this.getWindow().getDecorView();
        FrameLayout contentView = (FrameLayout) decorView.findViewById(android.R.id.content);
        viewGroup = (ViewGroup) contentView.getChildAt(0);

        glSurfaceView = new TestGLSurfaceView(this);
        button = (Button) findViewById(R.id.openGlview);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.openGlview:
                if(glSurfaceView.getParent() == null){
                    FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DimensionUtil.dip2px(getWindow().getContext(),500.0f));
                    lp.gravity = Gravity.BOTTOM;
                    viewGroup.addView(glSurfaceView, lp);
                } else {
                    viewGroup.removeView(glSurfaceView);
                }
                break;
        }
    }
}
