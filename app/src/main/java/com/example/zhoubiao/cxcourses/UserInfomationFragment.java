package com.example.zhoubiao.cxcourses;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhoubiao.cxcourses.constant.Constant;

public class UserInfomationFragment extends Fragment{
	private static final String ARG_SECTION_NUMBER = "section_number";
	  public static Fragment newInstance(int sectionNumber) {
		  UserInfomationFragment fragment = new UserInfomationFragment();
          Bundle args = new Bundle();
          args.putInt(ARG_SECTION_NUMBER, sectionNumber);
          fragment.setArguments(args);
          return fragment;
      }
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		((Main) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
		super.onAttach(activity);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
	}

	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView=inflater.inflate(R.layout.user_infomation, container, false);
		ImageView userAvatar=(ImageView) rootView.findViewById(R.id.user_avatar);
		userAvatar.setImageResource(R.drawable.big_default_avatar);
		TextView userName=(TextView) rootView.findViewById(R.id.user_name);
		userName.setText("Verso");

		return rootView;
	}
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.user_info, menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);
		switch(item.getItemId())
        {
        case R.id.logout:
        	SharedPreferences share=getActivity().getSharedPreferences(Constant.LOGIN_USER_INFO_FILE, Context.MODE_PRIVATE);
        	Editor edit=share.edit();
        	edit.clear();
        	edit.commit();

        	((Main)getActivity()).onNavigationDrawerItemSelected(1);
        	((NavigationDrawerFragment)getFragmentManager().findFragmentById(R.id.navigation_drawer)).refreshDrawer();
 
        	break;
        }
		return true;
	}
	

}
