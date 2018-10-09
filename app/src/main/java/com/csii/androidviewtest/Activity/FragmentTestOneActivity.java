package com.csii.androidviewtest.Activity;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.csii.androidviewtest.R;
import com.csii.androidviewtest.TestAndPractice.fragment.TestFragmentOne;
import com.csii.androidviewtest.TestAndPractice.fragment.TestFragmentThree;

public class FragmentTestOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test_one);
        Button button = (Button)findViewById(R.id.button8);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new TestFragmentThree());
            }
        });
        replaceFragment(new TestFragmentOne());

    }

    private void replaceFragment(Fragment fragment){
        //获取fragmentManager实例
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 开启fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //添加或替换fragment
        transaction.replace(R.id.frameLayout1, fragment);
        //提交
        transaction.commit();

    }


}

