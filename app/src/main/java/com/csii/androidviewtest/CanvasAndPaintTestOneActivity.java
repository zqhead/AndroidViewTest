package com.csii.androidviewtest;

import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.csii.androidviewtest.CustomView.CellEditText;
import com.csii.androidviewtest.CustomView.LoopView;
import com.csii.androidviewtest.CustomView.LoopView.LoopData;
import com.csii.androidviewtest.TestAndPractice.TestCanvasAndPaint;

import java.util.ArrayList;
import java.util.List;

public class CanvasAndPaintTestOneActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = "CanvasAndPaintTestOneAc";
    public TestCanvasAndPaint test1;
    private CellEditText cell;
    private Button mAddBtn;
    private Button mDelBtn;

    private LoopView mLoopView;
    private List<LoopData> list = new ArrayList<>();

    private String[] mNameList = {"zq1", "zq2","zq3","zq4","zq5","zq6","zq7","zq8"};
    private int[] mDataList = {200, 300, 500, 100, 350, 600, 200, 50};

    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.i(TAG, "run:1 ");
            super.handleMessage(msg);
            if(msg.what == 2){
                cell.setTextLength(msg.arg1);
            } else if (msg.what == 1){
                cell.setTextLength(cell.getTextLength() - 1);
            }
        }
    };
    public Handler mHandler1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_and_paint_test_one);
        test1 = (TestCanvasAndPaint) findViewById(R.id.test_1);

        cell = (CellEditText) findViewById(R.id.Edt_cell);
        cell.setMaxLength(7);

        mAddBtn = (Button) findViewById(R.id.btn_add);
        mAddBtn.setOnClickListener(this);
        mDelBtn = (Button) findViewById(R.id.btn_delete);
        mDelBtn.setOnClickListener(this);

        mLoopView = (LoopView) findViewById(R.id.view_loop);
        for (int i = 0; i < 8; i++) {
            /**
             * 内部类的实例化 类.内部类 变量名 = 类.new 内部类();
             * */
            LoopView.LoopData data = mLoopView.new LoopData(mNameList[i], mDataList[i]);
            list.add(data);
        }
        mLoopView.initLoopData(list);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();//子线程使用handler，需要先用looper.prepare()来为子线程实例化一个looper对象
                        mHandler1 = new Handler(){
                            @Override
                            public void handleMessage(Message msg) {
                                super.handleMessage(msg);
                                Log.i(TAG, "run:2 ");
                                    if (msg.what == 0){
                                    Message msg1 = new Message();
                                    msg1.what = 1;
                                    msg1.arg1 = -1;
                                    mhandler.sendMessage(msg1);
                                }
                            }
                        };
                        Log.i(TAG, "run:3 ");
                        for(int i = 1; i <= 7; i++) {
                            Message msg = new Message();
                            msg.arg1 = i;
                            msg.what = 2;
                            mhandler.sendMessage(msg);
                            try{
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                            }
                        }
                        //loop里面是一个死循环，所以子线程中的操作需要放在它前面执行
                        Looper.loop();

                    }
                }).start();
                break;
            case R.id.btn_delete:
                mhandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, "run:4 ");
                        Message msg2 = new Message();
                        msg2.what = 0;
                        mHandler1.sendMessage(msg2);
                        //cell.setTextLength(cell.getTextLength() - 1);
                    }
                });
                //cell.setTextLength(cell.getTextLength() - 1);
                break;
        }
    }


    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.i(TAG, "onConfigurationChanged: ");
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, "onSaveInstanceState: ");
        outState.putInt("textLength", cell.getTextLength());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i(TAG, "onRestoreInstanceState: ");
        cell.setTextLength(savedInstanceState.getInt("textLength"));
        super.onRestoreInstanceState(savedInstanceState);
    }
}
