package com.example.zhoubiao.cxcourses.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhoubiao.cxcourses.MainActivity;
import com.example.zhoubiao.cxcourses.R;
import com.example.zhoubiao.cxcourses.adapter.Listadapter;
import com.example.zhoubiao.cxcourses.course_detail_activity.LessonDetailActivity;
import com.example.zhoubiao.cxcourses.model.Comment;
import com.example.zhoubiao.cxcourses.utils.StreamTools;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

/**
 * Created by zb-1 on 2015/10/6.
 */
public class CourseRecommendFragment extends Fragment {
    private RecyclerView recyclerView1;

    private Button button;
    private EditText editText;
    private RatingBar ratingBar1;
    private RatingBar ratingBar2;
    private RatingBar ratingBar3;
    private RatingBar ratingBar4;
private TextView ping;
    private String name;
    private List<JSONObject> list;

    private String ss;
    private int count;
    private JSONArray jsonArray;
    private JSONObject jsonObject;
    private RecyclerView recyclerView;
    private Listadapter listadapter;
    private Handler handler=new Handler(){
        @Override
    public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what)
            {
                case 1:
                    if(list.size()>0) {
                        listadapter = new Listadapter(getActivity(), list);
                        recyclerView1.setAdapter(listadapter);
                        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
                    }
                    else
                    {
                        Log.v("999999999999","err");
                    }
                    break;
            }
        }
    };

    private String url="http://182.92.242.146/api/v1/reviews?";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_recommend_layout, container, false);
list=new ArrayList<JSONObject>();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);


                    count = LessonDetailActivity.jsonArray.length();
                    jsonArray = LessonDetailActivity.jsonArray;

                    for (int i = 0; i < count; i++) {
                        try {
                            jsonObject = jsonArray.getJSONObject(i);
                             Log.v("9999999999", jsonObject.toString());
                            list.add(jsonObject);
                        } catch (Exception E) {
                            System.out.print(E);
                        }
                        if (i == count - 1) {
                            Message message = handler.obtainMessage();
                            message.what = 1;
                            handler.sendMessage(message);
                        }

                    }
                    }
                    catch (Exception e)
                    {}
            }
        }).start();



//        none.setText(LessonDetailActivity.wordss);
        button = (Button) view.findViewById(R.id.update);
        name= LessonDetailActivity.course2.name;
        editText = (EditText) view.findViewById(R.id.editText);
        ratingBar1 = (RatingBar) view.findViewById(R.id.ratingbar1);
        ratingBar2 = (RatingBar) view.findViewById(R.id.ratingbar2);
        ratingBar3 = (RatingBar) view.findViewById(R.id.ratingbar3);
        ratingBar4 = (RatingBar) view.findViewById(R.id.ratingbar4);
        ping=(TextView)view.findViewById(R.id.ping);
        ping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.requestFocus();
            }
        });


        SharedPreferences share = getActivity().getSharedPreferences("save", Context.MODE_PRIVATE);
        ratingBar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {



                SharedPreferences share = getActivity().getSharedPreferences("save", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = share.edit();
                editor.putFloat(name+"rating1", rating);
                editor.commit();
            }
        });
        ratingBar2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {


                SharedPreferences share = getActivity().getSharedPreferences("save", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = share.edit();
                editor.putFloat(name+"rating2", rating);
                editor.commit();
            }
        });
        ratingBar3.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {


                SharedPreferences share = getActivity().getSharedPreferences("save", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = share.edit();
                editor.putFloat(name+"rating3", rating);
                editor.commit();
            }
        });
        ratingBar4.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {


                SharedPreferences share = getActivity().getSharedPreferences("save", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = share.edit();
                editor.putFloat(name+"rating4", rating);
                editor.commit();
            }
        });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences share = getActivity().getSharedPreferences("save", Context.MODE_PRIVATE);
                    final String string = editText.getText().toString();

                    SharedPreferences.Editor editor = share.edit();
                    editor.putString(name+"course", string);
                    editor.commit();
                    editText.setText("");
                  //  button.setText("保存");
new Thread(new Runnable() {
    @Override
    public void run() {
                    try {
                        String str=string;
if(string.equals(""))
{

        str="default";

}
Log.v("++++++++++++++++++",Boolean.toString(string.equals("")));
                        HttpClient httpClient = new DefaultHttpClient();
                        HttpGet httpGet=new HttpGet(url+"userinfo="+LessonDetailActivity.username+"&coursename="+name+"&one="+ratingBar1.getRating()+"&two="+ratingBar2.getRating()+"&three="+ratingBar3.getRating()+"&four="+ratingBar4.getRating()+"&words="+str);
                        Log.v("============",url+"userinfo="+LessonDetailActivity.username+"&coursename="+name+"&one="+ratingBar1.getRating()+"&two="+ratingBar2.getRating()+"&three="+ratingBar3.getRating()+"&four="+ratingBar4.getRating()+"&words="+str);
                        HttpResponse httpResponse = httpClient.execute(httpGet);

                        if (httpResponse.getStatusLine().getStatusCode() == 200) {
//获取返回结果
                           // button.setText("提交成功");


                            //String result = EntityUtils.toString(httpResponse.getEntity());
                            InputStream inputStream=httpResponse.getEntity().getContent();
                         String result=   StreamTools.readInputStream(inputStream);


                             ss=new String(result);
                            if(ss.equals(null)!=true)
                            {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run()
                                    {
                                        Toast.makeText(getContext(),"提交成功",Toast.LENGTH_LONG).show();
                                        LessonDetailActivity.ratingBar.setRating(Float.parseFloat(ss));
                                    }
                                });
                            }
                            Log.v("_______________", ss);
                            SharedPreferences.Editor editor1=LessonDetailActivity.share.edit();
                            editor1.putFloat(name + "rating",Float.parseFloat(ss) );
                            editor1.commit();
                         //   Toast.makeText(getActivity(), httpResponse.toString(), Toast.LENGTH_LONG).show();
                        } else
                        {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run()
                                {
                                    Toast.makeText(getActivity(), "sorry", Toast.LENGTH_LONG).show();
                                }
                            });

                        }
                    } catch (Exception e) {
                        e.printStackTrace();

                    }

    }
}).start();

                }
                                              /*    Message message = handler.obtainMessage();
                                                  message.what = 1;
                                                  handler.sendMessage(message);
                                                  */
            });


            editText.setVisibility(View.VISIBLE);
           // button.setText("保存");
         /*   button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences share = getActivity().getSharedPreferences("save", Context.MODE_PRIVATE);
                    String string = editText.getText().toString();
                    none.setText(string);
                    SharedPreferences.Editor editor = share.edit();
                    editor.putString("course", string);
                    editor.commit();
                    editText.setVisibility(View.INVISIBLE);
                }


            });
            */

        ratingBar1.setRating(share.getFloat(name+"rating1", 4));
        ratingBar2.setRating(share.getFloat(name+"rating2", 4));
        ratingBar3.setRating(share.getFloat(name+"rating3", 4));
        ratingBar4.setRating(share.getFloat(name+"rating4", 4));
        recyclerView1=(RecyclerView)view.findViewById(R.id.recommend_list);
//        none.setText(share.getString(name+"course", ""));
//        String string1=none.getText().toString();
        return view;
    }
}
