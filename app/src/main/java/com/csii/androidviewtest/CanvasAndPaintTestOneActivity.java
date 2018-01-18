package com.csii.androidviewtest;

import android.content.res.Configuration;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ExpandedMenuView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.csii.androidviewtest.CustomView.CellEditText;
import com.csii.androidviewtest.TestAndPractice.TestCanvasAndPaint;

public class CanvasAndPaintTestOneActivity extends BasicActivity implements View.OnClickListener{
    private static final String TAG = "CanvasAndPaintTestOneAc";
    public TestCanvasAndPaint test1;
    private CellEditText cell;
    private Button mAddBtn;
    private Button mDelBtn;

    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            cell.setTextLength(msg.arg1);
        }
    };

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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 1; i <= 7; i++) {
                            Message msg = new Message();
                            msg.arg1 = i;
                            mhandler.sendMessage(msg);
                            try{
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                            }
                        }
                    }
                }).start();
                break;
            case R.id.btn_delete:
                mhandler.post(new Runnable() {
                    @Override
                    public void run() {
                        cell.setTextLength(cell.getTextLength() - 1);
                    }
                });
                cell.setTextLength(cell.getTextLength() - 1);
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
