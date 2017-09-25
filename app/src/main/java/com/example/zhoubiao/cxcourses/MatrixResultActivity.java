package com.example.zhoubiao.cxcourses;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.zhoubiao.cxcourses.util.ArrayChange;

public class MatrixResultActivity extends Activity {
	ListView matrixList;
	
	int matrixX,matrixY;
	String[] listData;
	String[] changedArray;
	
	ArrayChange ac=new ArrayChange();
	
	 String[][] data={{"35,28,31,08,02,03,10","03,19,35,40,01,26,02","17,15,08,35,34,28,29,30,40","15,17,28,12,35,29,30","28,17,29,35,01,31,04","17,28,01,29,35,15,31,04",
    	 "28,29,07,40,35,31,02","40,35,02,04,07","03,35,14,17,04,07","31,28,26,07,02,03,05,40","02,05,07,04,34,10","10,05,34,16,02","15,02,25,19,38,18","10,30,35,28,08,37,18","35,10,19,03,34,31,12"
    	 ,"35,15,01,28,07,14,34,39","19,30,36,31,12,18,14,25,01","10,40,30,36,37,31,04,03,12","28,31,40,35,10,30,18,14,04","35,01,30,39,29,21,07,19,12","02,40,06,31,36,29,04,38,32","01,35,32,38,13,19,23"
    	 ,"01,02,28,03,35,25","05,35,31,24,03,04,07,40","10,19,28,20,35,01,02,03,16","19,06,03,10,02,13,34,12","10,24,11,35,25","35,02,13,03,05,39,14","02,30,39,25,10,21,31,35,27","02,30,39,25,10,21,31,35,27"
    	 ,"02,35,10,39,03,31,22,24,27","29,28,15,02,12,05,07,34","10,15,13,05,24,25,26","02,15,03,11,25,23,35,24,01","03,17,01,35,27,14,11,04,31","02,28,17,27,03,30,33,11,14","02,05,06,23,24,28","26,10,17,13,35,02,05,28",
    	 "03,01,31,14,40,30","05,24,27,21,02,25,18,31,35","01,05,36,25,16,14,27,24,03","28,05,26,35,25,01,18,24,,03","28,05,26,35,25,01,18,24,03","35,24,10,28,12,01,37,03,16","26,35,40,30,36,02,10,25,28","10,12,02,19,15,05",
    	 "26,28,32,05,20,36,32,37","28,26,35,10,02,37"}};
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_matrix_result);
	
	ActionBar actionBar = getActionBar();
	actionBar.setDisplayHomeAsUpEnabled(true);
	
	matrixList=(ListView)findViewById(R.id.matrixList);
	Intent intent=getIntent();
	matrixX=intent.getIntExtra("matrixX", 0);
	matrixY=intent.getIntExtra("matrixY", 0);
	
	listData=data[matrixX][matrixY].split(",");
	
	changedArray=ac.changeArray(listData);
	
	ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, changedArray);
	matrixList.setAdapter(adapter);
	
	matrixList.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Intent nextIntent=new Intent(MatrixResultActivity.this,ContentViewerActivity.class);
//			Intent intent=new Intent(getActivity(), ContentViewerActivity.class);
			
			switch(arg2){
			case 0:
				nextIntent.putExtra("lessonName","变害为利原理");
				break;
			case 1:
				nextIntent.putExtra("lessonName","抽取原理");
				break;
			case 2:
				nextIntent.putExtra("lessonName","等势原理");
				break;
			default:
				nextIntent.putExtra("lessonName","动态特性原理");
				break;	
				
			}
			
			startActivity(nextIntent);
		}
	});
    }
@Override
public boolean onOptionsItemSelected(MenuItem item) {
	// TODO Auto-generated method stub
	switch (item.getItemId()) {
	case android.R.id.home:
		this.finish();
	default:
		return super.onOptionsItemSelected(item);
	}
	 
}
}
