package com.example.zhoubiao.cxcourses.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


import com.example.zhoubiao.cxcourses.CourseActivity;
import com.example.zhoubiao.cxcourses.Main;
import com.example.zhoubiao.cxcourses.R;
import com.example.zhoubiao.cxcourses.adapter.ContentListAdapter;
import com.example.zhoubiao.cxcourses.constant.Constant;
import com.example.zhoubiao.cxcourses.model.CommonCourse;
import com.example.zhoubiao.cxcourses.util.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by BUPTIET on 2016/9/5.
 */
public class Ss extends Fragment {
    private ListView listView;
    ContentListAdapter adapter;
    List<CommonCourse> course=new LinkedList<CommonCourse>();
    JsonParser jsonParser=new JsonParser();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.material,container,false);
        course=jsonParser.getCreativeCoursefromJson(getJsonFromAssets(Constant.COURSE_JSON_NAME+Constant.JSON_FILE_SUFFIX));
        adapter=new ContentListAdapter(course,getActivity());
        listView=(ListView)view.findViewById(R.id.ss);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> listView, View selectedView,
                                    int selectedPosition, long id) {
                Intent intent;
                intent=new Intent(getActivity(), CourseActivity.class);
                intent.putExtra(Constant.POSITION_NUMBER, selectedPosition);
                intent.putExtra(Constant.LIST_TYPE, Constant.TYPE_COURSE);
                intent.putExtra(Constant.ACTION_BAR_TITLE, course.get(selectedPosition).getCourseName());
                startActivity(intent);
            }
        });
        return view;
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
