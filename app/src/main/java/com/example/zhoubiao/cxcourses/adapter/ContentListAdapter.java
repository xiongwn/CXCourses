package com.example.zhoubiao.cxcourses.adapter;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import com.example.zhoubiao.cxcourses.R;
import com.example.zhoubiao.cxcourses.model.CommonCourse;

public class ContentListAdapter extends BaseAdapter{
	private List<CommonCourse> courses;
	private Activity activityContext;
	private String item_profix;
	public ContentListAdapter(List<CommonCourse> courses,Activity activityContext)
	{
		this.activityContext=activityContext;
		this.courses=courses;
	}
	public void setProfix(String profix)
	{
		item_profix=profix;
	}
	public void setList(List<CommonCourse> courses)
	{
		this.courses=courses;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return courses.size();
	}

	@Override
	public CommonCourse getItem(int position) {
		// TODO Auto-generated method stub
		return courses.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ListViewHolder holder;
		if(convertView==null)
		{
			convertView=activityContext.getLayoutInflater().inflate(R.layout.content_list_item, null);
			holder=new ListViewHolder(convertView);
			convertView.setTag(holder);
		}
		else
		{
			holder=(ListViewHolder) convertView.getTag();
		}
		if(item_profix!=null)
		holder.getCourseNameView().setText(item_profix+(position+1)+"："+getItem(position).getCourseName());
		else
			holder.getCourseNameView().setText(getItem(position).getCourseName());
		holder.getCourseIntroductionView().setText(getItem(position).getCourseIntroduction());
		if(getItem(position).getCourseIcon()!=null)
		{
			holder.getCourseIconView().setVisibility(View.VISIBLE);
			try {
				holder.getCourseIconView().setImageBitmap(BitmapFactory.decodeStream(activityContext.getResources().getAssets().open(getItem(position).getCourseIcon())));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			holder.getCourseIconView().setVisibility(View.GONE);
		}
		return convertView;
	}

}

class ListViewHolder
{
	TextView courseName;
	TextView courseIntroduction;
	ImageView courseIcon;
	public ListViewHolder(View convertView)
	{
		courseName=(TextView) convertView.findViewById(R.id.course_name);
		courseIntroduction=(TextView) convertView.findViewById(R.id.course_introduction);
		courseIcon=(ImageView) convertView.findViewById(R.id.course_icon);
	}
	public TextView getCourseNameView()
	{
		return courseName;
	}
	public TextView getCourseIntroductionView()
	{
		return courseIntroduction;
	}
	public ImageView getCourseIconView()
	{
		return courseIcon;
	}
}
