package com.example.zhoubiao.cxcourses;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.zhoubiao.cxcourses.constant.Constant;

import java.io.File;

public class ContentViewerActivity extends Activity {

	/*
	 * @Override protected void onCreate(Bundle savedInstanceState) {
	 * super.onCreate(savedInstanceState);
	 * setContentView(R.layout.activity_content_viewer);
	 * 
	 * if (savedInstanceState == null) { getFragmentManager().beginTransaction()
	 * .add(R.id.container, new PlaceholderFragment()).commit(); } }
	 * 
	 * @Override public boolean onCreateOptionsMenu(Menu menu) {
	 * 
	 * // Inflate the menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.content_viewer, menu); return true; }
	 * 
	 * @Override public boolean onOptionsItemSelected(MenuItem item) { // Handle
	 * action bar item clicks here. The action bar will // automatically handle
	 * clicks on the Home/Up button, so long // as you specify a parent activity
	 * in AndroidManifest.xml. int id = item.getItemId(); if (id ==
	 * R.id.action_settings) { return true; } return
	 * super.onOptionsItemSelected(item); }
	 *//**
	 * A placeholder fragment containing a simple view.
	 */
	/*
	 * public static class PlaceholderFragment extends Fragment {
	 * 
	 * public PlaceholderFragment() { }
	 * 
	 * @Override public View onCreateView(LayoutInflater inflater, ViewGroup
	 * container, Bundle savedInstanceState) { View rootView =
	 * inflater.inflate(R.layout.fragment_content_viewer, container, false);
	 * return rootView; } }
	 */
	private WebView webView;
	String SDPATH = Environment.getExternalStorageDirectory() + File.separator+"科学创新课程文档"+File.separator;

	private String fileName;
	private int position;
	private int select;

	// private Intent intent;
	/**
	 * 现在视频播放的问题是，横竖屏切换的时候会重新加载页面而不是保持在播放页面
	 */

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_PROGRESS);
		// intent=getIntent();
		// intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Intent intent=getIntent();
		fileName=intent.getExtras().getString("lessonName");
		position=intent.getExtras().getInt("position");
		select=intent.getExtras().getInt("select");
		ActionBar actionBar = getActionBar();
//		actionBar.setDisplayHomeAsUpEnabled(true);
		webView = new WebView(this);
		WebSettings webSetting = webView.getSettings();
		webSetting.setJavaScriptEnabled(true);
		webSetting.setBuiltInZoomControls(true);
		webSetting.setDisplayZoomControls(false);
		webSetting.setLoadWithOverviewMode(true);

		webSetting.setUseWideViewPort(true);
		webView.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				setTitle("载入中...");
				setProgress(progress * 100);
				if (progress == 100)
					setTitle("内容详情");
			}

		});
		webView.setWebViewClient(new WebViewClient() {

			public void onReceivedError(WebView view, int errorCode,
										String description, String failingUrl) { // Handle the error

			}

			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});

		File file=new File("file:///android_asset/"+Integer.toString(position)+Integer.toString(select)+".htm");
		if(file.exists())
		{
		webView.loadUrl("file:///android_asset/"+Integer.toString(position)+Integer.toString(select)+".htm");
			//Log.v("....................", fileName);
			Log.v("....................",Integer.toString(position + select));
			Log.v("------------------",Integer.toString(select));
		}else{
			webView.loadUrl("file:///android_asset/"+Integer.toString(position)+Integer.toString(select)+".htm");
			Log.v("....................", fileName);
			Log.v("....................",Integer.toString(position));
			Log.v("------------------",Integer.toString(select));
		}

		setContentView(webView);
		// startActivity(intent);
	}

	@Override
	public void onPause() {
		super.onPause();
		webView.onPause();
	}

	public void onResume() {
		super.onResume();
		webView.onResume();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
