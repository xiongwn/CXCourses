package com.example.zhoubiao.cxcourses;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import com.example.zhoubiao.cxcourses.adapter.ContentListAdapter;
import com.example.zhoubiao.cxcourses.constant.Constant;
import com.example.zhoubiao.cxcourses.model.CommonCourse;
import com.example.zhoubiao.cxcourses.util.JsonParser;

public class CourseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course);
		ActionBar actionBar = getActionBar();
//		actionBar.setDisplayHomeAsUpEnabled(true);

		Intent intent=getIntent();

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, PlaceholderFragment.newInstance(intent.getIntExtra(Constant.LIST_TYPE, Constant.TYPE_COURSE), intent.getIntExtra(Constant.POSITION_NUMBER, 0), true)).commit();
		}
//		actionBar.setTitle(intent.getStringExtra(Constant.ACTION_BAR_TITLE));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.course, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch(id)
		{
		case R.id.action_settings:
			return true;
		case android.R.id.home:
			this.finish();
			break;
		}
		/*if (id == R.id.action_settings) {
			return true;
		}*/
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		private ContentListAdapter adapter;
		private List<CommonCourse> lessons=new LinkedList<CommonCourse>();
		private JsonParser jsonParser=new JsonParser();
		 private Handler refreshHandler=new Handler()
	        {
	        	public void handleMessage(Message msg)
	        	{
	        		switch(msg.what)
	        		{
	        		case Constant.LIST_REFRESH:
	        			lessons=(List<CommonCourse>) msg.obj;
	        			adapter.setList(lessons);
	        			adapter.notifyDataSetChanged();
	        			break;
	        		}
	        	}
	        };
	        String profix;

		public static PlaceholderFragment newInstance(int type,int sectionNumber,boolean isSearchable) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(Constant.POSITION_NUMBER, sectionNumber);
            args.putInt(Constant.LIST_TYPE, type);
            args.putBoolean(Constant.IS_SEARCHABLE, isSearchable);
            fragment.setArguments(args);
            return fragment;
        }

		public static PlaceholderFragment newInstance(int type,int [] cases) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(Constant.LIST_TYPE, type);
            args.putIntArray(Constant.CASES_ARRAY, cases);
            fragment.setArguments(args);
            return fragment;
        }

		public PlaceholderFragment() {
		}
		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			
//			Constant constant=new Constant();
			Thread refreshListThread=new Thread()
        	{
        		@Override
        		public void run() {
        			// TODO Auto-generated method stub
        			Message msg=refreshHandler.obtainMessage(Constant.LIST_REFRESH);
        			switch(getArguments().getInt(Constant.LIST_TYPE))
        			{
        			case Constant.TYPE_COURSE:
        				adapter.setProfix("单元");
        				msg.obj=jsonParser.getCreativeCoursefromJson(getJsonFromAssets("Course"+getArguments().getInt(Constant.POSITION_NUMBER)+Constant.JSON_FILE_SUFFIX));
        				break;
        			case Constant.TYPE_TOOLS:
        				adapter.setProfix("");
        				msg.obj=jsonParser.getCreativeCoursefromJson(getJsonFromAssets("case"+getArguments().getInt(Constant.POSITION_NUMBER)+Constant.JSON_FILE_SUFFIX));
//        				Log.v("JASON", (String) msg.obj);
        				break;
        			}
        			
        			msg.sendToTarget();
        			super.run();
        		}
        	};
        	refreshListThread.start();
			adapter=new ContentListAdapter(lessons, getActivity());
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_course,
					container, false);
			ListView lessonList=(ListView) rootView.findViewById(R.id.course_content_list);
			lessonList.setAdapter(adapter);
			lessonList.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> listView, View selectedView,
						int selectedPosition, long id) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(getActivity(), ContentViewerActivity.class);
					intent.putExtra("select", selectedPosition);
					intent.putExtra("position", getArguments().getInt(Constant.POSITION_NUMBER));
					intent.putExtra("lessonName", lessons.get(selectedPosition).getCourseName());
					startActivity(intent);
				}
			});
			return rootView;
		}
		  public void onActivityCreated (Bundle savedInstanceState) {
	            super.onActivityCreated(savedInstanceState);
	            // Indicate that this fragment would like to influence the set of actions in the action bar.
	            setHasOptionsMenu(true);
	        }
	        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	            // If the drawer is open, show the global app actions in the action bar. See also
	            // showGlobalContextActionBar, which controls the top-left area of the action bar.
	        	if(getArguments().getBoolean(Constant.IS_SEARCHABLE))
	        	{
	                inflater.inflate(R.menu.search, menu);
	                SearchView searchView=(SearchView) menu.findItem(R.id.action_bar_searching).getActionView();
	                searchView.setOnQueryTextListener(new OnQueryTextListener() {
						
						@Override
						public boolean onQueryTextSubmit(String query) {
							// TODO Auto-generated method stub
							return false;
						}
						
						@Override
						public boolean onQueryTextChange(String newText) {
//
							// TODO Auto-generated method stub
							LinkedList<CommonCourse> searchResult=new LinkedList<CommonCourse>();
							adapter.setList(searchResult);
							adapter.notifyDataSetChanged();
							for(CommonCourse course:lessons)
							{
								if(course.getCourseName().contains(newText)||course.getCourseIntroduction().contains(newText))
									searchResult.add(course);
								adapter.notifyDataSetChanged();
								
							}
							return false;
						}
					});
	                searchView.setOnCloseListener(new OnCloseListener() {
						
						@Override
						public boolean onClose() {
							// TODO Auto-generated method stub
							adapter.setList(lessons);
							adapter.notifyDataSetChanged();
							return false;
						}
					});
	        	}
	            super.onCreateOptionsMenu(menu, inflater);
	        }
		 public String getJsonFromAssets(String fileName)
	        {
	        	
	        	StringBuffer sb = new StringBuffer();
	    		String line = null;
	    		BufferedReader buffer = null;       	
	        	try {
					InputStream fileStream=getActivity().getResources().getAssets().open(fileName);
					buffer=new BufferedReader(new InputStreamReader(fileStream));
					while ((line = buffer.readLine()) != null) {
						sb.append(line);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	finally {
	    			try {
	    				buffer.close();
	    			} catch (Exception e) {
	    				e.printStackTrace();
	    			}
	    		}
	        	return sb.toString();
	        }
	}

}
