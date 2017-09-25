package com.example.zhoubiao.cxcourses.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.example.zhoubiao.cxcourses.R;
import com.example.zhoubiao.cxcourses.constant.Constant;

public class NavigationDrawerAdapter extends BaseAdapter{
	private List<String> naviString;
	private Activity activityContext;
	private SharedPreferences share;
	public NavigationDrawerAdapter(List<String> naviString,Activity activityContext)
	{
		this.naviString=naviString;
		this.activityContext=activityContext;
		share=activityContext.getSharedPreferences(Constant.LOGIN_USER_INFO_FILE, Context.MODE_PRIVATE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return naviString.size()+1;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
	/*	if(convertView==null)
		{
			convertView=activityContext.getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);
		}
		TextView text=(TextView) convertView.findViewById(android.R.id.text1);
		if(position==0)
		{
			text.setText("");
		}
		else
		{
			text.setText(naviString.get(position-1));
		}
		return convertView;*/
		if(position==0)
		{
			convertView=activityContext.getLayoutInflater().inflate(R.layout.navigation_user_info, null);
			if(share.getBoolean(Constant.SP_IS_LOGIN, false))
			{
				ImageView avatar=(ImageView) convertView.findViewById(R.id.user_avatar);
				avatar.setImageResource(R.drawable.big_default_avatar);
				TextView userName=(TextView) convertView.findViewById(R.id.user_name);
				userName.setText(share.getString(Constant.SP_USER_NAME, "蔺方舟"));
				TextView userEmail=(TextView) convertView.findViewById(R.id.user_email_address);
				userEmail.setText(share.getString(Constant.SP_USER_ID, "lfz6356@sina.com"));
			}
			else
			{
				TextView userName=(TextView) convertView.findViewById(R.id.user_name);
				userName.setText("尚未登录");
				TextView userEmail=(TextView) convertView.findViewById(R.id.user_email_address);
				userEmail.setText("请点击登陆");
			}
			
		}
		else
		{
			convertView=activityContext.getLayoutInflater().inflate(R.layout.navigation_list_item, null);
			TextView text=(TextView) convertView.findViewById(R.id.navigation_name);
			text.setText(naviString.get(position-1));
			ImageView icon=(ImageView) convertView.findViewById(R.id.icon);
			switch(position-1)
			{
			case 0:
				icon.setImageResource(R.drawable.course_icon);
				break;
			case 1:
				icon.setImageResource(R.drawable.case_icon);
				break;
			case 2:
				icon.setImageResource(R.drawable.tools_icon);
				break;
			}
		}
		return convertView;
	}

}
