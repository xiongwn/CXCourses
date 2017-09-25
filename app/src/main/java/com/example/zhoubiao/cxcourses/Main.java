package com.example.zhoubiao.cxcourses;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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

public class Main extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
	private int lastPosition=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        /*if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, PlaceholderFragment.newInstance(1)).commit();}*/

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
    	FragmentManager fragmentManager = getFragmentManager();
        // update the main content by replacing fragments
    	if(lastPosition==position)
    		return;
    	if(position==0)
    	{
    		fragmentManager.beginTransaction().replace(R.id.container, UserInfomationFragment.newInstance(0)).commit();
    	}
    	else
    	{
        
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position))
                .commit();
    	}
    	lastPosition=position;
    }

    public void onSectionAttached(int number) {

        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 0:
            	mTitle=getString(R.string.user_info);
            	break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
//            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	AlertDialog.Builder builder=new Builder(this);
        	builder.setTitle("").setMessage("").setPositiveButton("", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});
        	builder.create().show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        public List<CommonCourse> courses=new LinkedList<CommonCourse>();
//        private Constant constant=new Constant();
        private ContentListAdapter adapter;
        private JsonParser jsonParser=new JsonParser();
        private Handler refreshHandler=new Handler()
        {
        	public void handleMessage(Message msg)
        	{
        		switch(msg.what)
        		{
        		case Constant.LIST_REFRESH:
        			courses=(List<CommonCourse>) msg.obj;
        			adapter.setList(courses);        			
        			adapter.notifyDataSetChanged();
        			break;
        		}
        	};
        };

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }
        @Override
        public void onCreate(Bundle savedInstanceState) {
        	// TODO Auto-generated method stub
        	super.onCreate(savedInstanceState);       	
//        	courses=constant.getCreativeCourse();
        	Thread refreshListThread=new Thread()
        	{
        		@Override
        		public void run() {
        			// TODO Auto-generated method stub
        			Message msg=refreshHandler.obtainMessage(Constant.LIST_REFRESH);
        			switch(getArguments().getInt(ARG_SECTION_NUMBER))
        			{
        			case 1:
        				msg.obj=jsonParser.getCreativeCoursefromJson(getJsonFromAssets(Constant.COURSE_JSON_NAME+Constant.JSON_FILE_SUFFIX));
        				adapter.setProfix("课程");
        				break;
        			case 2:
        				msg.obj=jsonParser.getCreativeCoursefromJson(getJsonFromAssets(Constant.COURSE_JSON_NAME+Constant.JSON_FILE_SUFFIX));
        				adapter.setProfix("案例");
        				break;
        			case 3:
        				msg.obj=jsonParser.getCreativeCoursefromJson(getJsonFromAssets(Constant.TOOLS_JSON_NAME+Constant.JSON_FILE_SUFFIX));
        				adapter.setProfix("");
        				break;
        			}
        			
        			msg.sendToTarget();
        			super.run();
        		}
        	};
        	refreshListThread.start();
        	adapter=new ContentListAdapter(courses, getActivity());
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            /*TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));*/
            ListView listview=(ListView)rootView.findViewById(R.id.content_list);
            listview.setAdapter(adapter);
            listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> listView, View selectedView,
						int selectedPosition, long id) {
					Intent intent;
					// TODO Auto-generated method stub
					switch(getArguments().getInt(ARG_SECTION_NUMBER))
					{
					case 1:
						intent=new Intent(getActivity(), CourseActivity.class);
					intent.putExtra(Constant.POSITION_NUMBER, selectedPosition);
					intent.putExtra(Constant.LIST_TYPE, Constant.TYPE_COURSE);
					intent.putExtra(Constant.ACTION_BAR_TITLE, courses.get(selectedPosition).getCourseName());
						break;
					case 2:
						intent=new Intent(getActivity(), ContentViewerActivity.class);
						intent.putExtra("lessonName", courses.get(selectedPosition).getCourseName());
						intent.putExtra(Constant.LESSON_NUMBER, selectedPosition);
						intent.putExtra(Constant.POSITION_NUMBER, getArguments().getInt(Constant.POSITION_NUMBER));
						intent.putExtra(Constant.ACTION_BAR_TITLE, courses.get(selectedPosition).getCourseName());
						break;
					case 3:
						if(courses.get(selectedPosition).getCourseName().equals(Constant.Contradiction_Matrix))
						{
							intent=new Intent(getActivity(), MatrixActivity.class);
							
							
						}
						else
						{
						intent=new Intent(getActivity(), CourseActivity.class);
						intent.putExtra(Constant.POSITION_NUMBER, selectedPosition);
						intent.putExtra(Constant.LIST_TYPE, Constant.TYPE_TOOLS);
						intent.putExtra(Constant.ACTION_BAR_TITLE, courses.get(selectedPosition).getCourseName());
						}
						break;
						default:
							intent=null;
						break;
					}
					startActivity(intent);
					
				}
			});
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
        	super.onAttach(activity);
            ((Main) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
            
        }
        public void onActivityCreated (Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            // Indicate that this fragment would like to influence the set of actions in the action bar.
            setHasOptionsMenu(true);
        }
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            // If the drawer is open, show the global app actions in the action bar. See also
            // showGlobalContextActionBar, which controls the top-left area of the action bar.
        	switch(getArguments().getInt(ARG_SECTION_NUMBER))
        	{
        	/*case 0:
        		inflater.inflate(R.menu.user_info, menu);
        		break;*/
        	case 2:
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
 						Log.v("进入搜索", "当搜索文字变化");
 						// TODO Auto-generated method stub
 						LinkedList<CommonCourse> searchResult=new LinkedList<CommonCourse>();
 						adapter.setList(searchResult);
 						adapter.notifyDataSetChanged();
 						for(CommonCourse course:courses)
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
 						Log.v("进入搜索", "关闭搜索");
 						// TODO Auto-generated method stub
 						adapter.setList(courses);
 						adapter.notifyDataSetChanged();
 						return false;
 					}
 				});
        		break;
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
