package com.csii.androidviewtest.OpenGLES;

/**
 * Created by zqhead on 2018/8/17.
 */

public class Triangle {

    private final String vertexShaderCode =
            "attribute vc4 vPosition;"+
                    "void main(){"+
                    "  gl_Position = vPosition;"+
                    "}";

    public Triangle() {
        super();
    }

    public void draw(){

    }
}



