package com.example.zhoubiao.cxcourses;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

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

public class MainSlectionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_slection);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment())
					.commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_slection, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		 private List<CommonCourse> courses=new LinkedList<CommonCourse>();
//       private Constant constant=new Constant();
       private ContentListAdapter adapter;
       private JsonParser jsonParser=new JsonParser();
       private ListView list;

		public PlaceholderFragment() {
		}
		 public void onCreate(Bundle savedInstanceState) {
	        	// TODO Auto-generated method stub
	        	super.onCreate(savedInstanceState);       	
//	        	courses=constant.getCreativeCourse();
	        	courses=jsonParser.getCreativeCoursefromJson(getJsonFromAssets("mainSelectionJSON.txt"));
	        	adapter=new ContentListAdapter(courses, getActivity());
	        }

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_selection, container, false);
			list=(ListView) rootView.findViewById(R.id.main_selection_list);
			list.setAdapter(adapter);
			list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> listView, View selectedItemView, int position, long id) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(getActivity(), Main.class);
						intent.putExtra(Constant.MAIN_SELECTION_SELECTED_POSITION, position);
						startActivity(intent);
					getActivity().finish();
				}
			});
			return rootView;
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
