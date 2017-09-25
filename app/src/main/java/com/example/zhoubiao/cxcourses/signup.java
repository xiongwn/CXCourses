package com.example.zhoubiao.cxcourses;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by BUPTIET on 2016/10/19.
 */
public class signup extends Activity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        webView=new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        //webView.loadUrl("http://182.92.242.146/users/sign_up");
        webView.loadUrl("http://47.93.87.98/fx/index.html");
        setContentView(webView);
    }
}
