package com.csii.androidviewtest.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.hardware.fingerprint.FingerprintManager;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.csii.androidviewtest.R;
import com.csii.androidviewtest.Util.ToastUtil;

public class FingerPrintTestActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "FingerPrintTestActivity";
    FingerprintManagerCompat fingerprintManager;
    AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_print_test);

        fingerprintManager = getFingerprintManager(this);
        Button button = (Button) findViewById(R.id.btn_start_fingerprint);
        button.setOnClickListener(this);

    }

    private FingerprintManagerCompat getFingerprintManager(Context context){
        FingerprintManagerCompat fingerprintManagerCompat = null;
        try {
            fingerprintManagerCompat = FingerprintManagerCompat.from(this);
        }catch (Error error){
            Log.e(TAG, "getFingerprintManager: " + error);
        }

        return fingerprintManagerCompat;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_fingerprint:
                if (fingerprintManager == null || !fingerprintManager.isHardwareDetected()) {
                    ToastUtil.showInfo(this, "本设备不支持指纹验证");
                    return;
                }
                if (fingerprintManager.hasEnrolledFingerprints()) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("验证指纹");
                    //    设置Content来显示一个信息
                    builder.setMessage("请点击Home键验证已有指纹");
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FingerPrintTestActivity.this.dialog.dismiss();
                        }
                    });

                    dialog = builder.show();

                    fingerprintManager.authenticate(null, 0, null, new fingerCallBack(), null);
                } else {
                    ToastUtil.showInfo(this, "没有指纹");
                }

                break;
            default:
                break;
        }
    }

    private class fingerCallBack extends FingerprintManagerCompat.AuthenticationCallback{
        private static final String TAG = "fingerCallBack";

        @Override
        public void onAuthenticationError(int errMsgId, CharSequence errString) {
            ToastUtil.showInfo(FingerPrintTestActivity.this, errString.toString());
            Log.d(TAG, "onAuthenticationError: " + errString);
            //dialog.dismiss();
            //super.onAuthenticationError(errMsgId, errString);
        }

        @Override
        public void onAuthenticationFailed() {
            ToastUtil.showInfo(FingerPrintTestActivity.this, "验证失败");
            Log.d(TAG, "onAuthenticationFailed: " + "验证失败");
            //dialog.dismiss();
            //super.onAuthenticationFailed();
        }

        @Override
        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
            ToastUtil.showInfo(FingerPrintTestActivity.this, helpString.toString());
            Log.d(TAG, "onAuthenticationHelp: " +  helpString);
            //dialog.dismiss();
            //super.onAuthenticationHelp(helpMsgId, helpString);
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
            ToastUtil.showInfo(FingerPrintTestActivity.this, "验证成功");
            Log.d(TAG, "onAuthenticationSucceeded:  " +  "验证成功");
            dialog.dismiss();
            //super.onAuthenticationSucceeded(result);
        }
    }
}
