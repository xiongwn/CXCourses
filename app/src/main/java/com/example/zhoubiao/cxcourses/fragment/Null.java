package com.example.zhoubiao.cxcourses.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhoubiao.cxcourses.R;

/**
 * Created by BUPTIET on 2016/5/23.
 */
public class Null extends Fragment {
    public Null(){}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.null_,container,false);
        return view;
    }
}
