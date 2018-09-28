package com.csii.androidviewtest.OpenGLES;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Created by zqhead on 2018/8/16.
 */

public class TestGLSurfaceView extends GLSurfaceView {

    private myRenderer renderer;

    public TestGLSurfaceView(Context context) {
        this(context, null);
    }

    public TestGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        renderer = new myRenderer();
        this.setRenderer(renderer);
    }
}
