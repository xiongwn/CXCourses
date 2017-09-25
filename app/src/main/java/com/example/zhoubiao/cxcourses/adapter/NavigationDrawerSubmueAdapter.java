package com.example.zhoubiao.cxcourses.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import com.example.zhoubiao.cxcourses.R;

public class NavigationDrawerSubmueAdapter extends BaseAdapter {
	class Holder
	{
		TextView text;
		public Holder(View convertView)
		{
			text=(TextView) convertView.findViewById(R.id.submenu_text);
		}
		public TextView getText()
		{
			return text;
		}
	}
	private List<String> menuList;
	private Activity activity;
	public NavigationDrawerSubmueAdapter(List<String> menuList,Activity activity)
	{
		this.menuList=menuList;
		this.activity=activity;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return menuList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return menuList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder holder;
		if(convertView==null)
		{
			convertView=activity.getLayoutInflater().inflate(R.layout.navigation_submenu_list_item, null);
			holder=new Holder(convertView);
			convertView.setTag(holder);
		}
		else
		{
			holder=(Holder) convertView.getTag();
		}
		holder.getText().setText(menuList.get(position));
		return convertView;
	}
	

}
