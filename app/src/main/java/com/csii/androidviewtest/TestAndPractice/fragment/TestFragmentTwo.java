package com.csii.androidviewtest.TestAndPractice.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csii.androidviewtest.R;

/**
 * Created by zqhead on 2018/10/9.
 */

public class TestFragmentTwo extends Fragment {
    public TestFragmentTwo() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left_test_two, container, false);
        return view;
    }
}
