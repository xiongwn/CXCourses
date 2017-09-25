package com.example.zhoubiao.cxcourses.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import com.example.zhoubiao.cxcourses.constant.Constant;
import com.example.zhoubiao.cxcourses.model.CommonCourse;

public class JsonParser {
	public List<CommonCourse> getCreativeCoursefromJson(String jsonString)
	{
		LinkedList<CommonCourse> creativeCourses=new LinkedList<CommonCourse>();
		try {
			JSONArray array=new JSONArray(jsonString);
			for(int i=0;i<array.length();i++)
			{
				JSONObject courseObject=array.getJSONObject(i);
				CommonCourse course=new CommonCourse();
				course.setCourseName(courseObject.getString(Constant.JSON_COURSE_NAME));
				course.setCourseIntroduction(courseObject.getString(Constant.JSON_COURSE_INTRODUCTION));
				try
				{
					course.setCourseIcon(courseObject.getString(Constant.JSON_COURSE_ICON));
				}
				catch(JSONException e)
				{
					course.setCourseIcon(null);
				}
				
				creativeCourses.add(course);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return creativeCourses;
		
	}

}
