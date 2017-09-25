package com.example.zhoubiao.cxcourses.course_detail_activity;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhoubiao.cxcourses.MainActivity;
import com.example.zhoubiao.cxcourses.R;
import com.example.zhoubiao.cxcourses.cn.sharesdk.demo.src.cn.sharesdk.onekeyshare.OnekeyShare;
import com.example.zhoubiao.cxcourses.database_util.LocalCourseDAo;
import com.example.zhoubiao.cxcourses.dataobject.Course;
import com.example.zhoubiao.cxcourses.dataobject.LocalCourse;
import com.example.zhoubiao.cxcourses.download.DownLoadService;
import com.example.zhoubiao.cxcourses.fragment.CourseIntroFragment;
import com.example.zhoubiao.cxcourses.fragment.CourseRecommendFragment;
import com.example.zhoubiao.cxcourses.fragment.TeacherIntrroFragment;
import com.example.zhoubiao.cxcourses.utils.NetUtil;
import com.example.zhoubiao.cxcourses.utils.StreamTools;
import com.example.zhoubiao.cxcourses.videoplayer.VideoPlayActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.github.ksoichiro.android.observablescrollview.CacheFragmentStatePagerAdapter;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.sharesdk.framework.ShareSDK;

public class LessonDetailActivity extends AppCompatActivity{

	private String courseName;
	private float rating;
	private TextView lessonName;
	private TextView teacherName;
	private ViewPager viewPager;
	private SimpleDraweeView lessionImage;
	private ImageView shareImage;
	private ImageView downloadImage;
	private ImageView play;
    private RelativeLayout courseTitle;
	public static TextView tvArr[] = new TextView[3];
	public static  RatingBar ratingBar;
	private int selectedColor = R.color.forestgreen;
	private int unselectorColor = R.color.lawngreen;
	private int pre = 0;
    private boolean isDownload;
	private Course course;
	private Bundle bundle;
	public static Course course2;
	public static String  username;
    public static SharedPreferences share;
	public static JSONArray jsonArray;

	private LocalCourse localCourse;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		isDownload = false;
		setContentView(R.layout.lesson_detail);
		initData();
		initView();
		downloadImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!isDownload) {
					if (localCourse != null) {
						Toast.makeText(LessonDetailActivity.this, "亲，该视频已下载", Toast.LENGTH_LONG).show();
					} else {
						if (NetUtil.isNetworkConnected(LessonDetailActivity.this)) {
							if (NetUtil.isWifi(LessonDetailActivity.this)) {
								Toast.makeText(LessonDetailActivity.this, "视频正在下载，可以下拉屏幕查看进度条，下载完成后将在本地显示", Toast.LENGTH_LONG).show();
								Intent intent = new Intent(LessonDetailActivity.this, DownLoadService.class);
								intent.putExtra("url", course.videoUrl);
								intent.putExtra("courseName", course.name);
								startService(intent);
								isDownload = true;
							} else {
								AlertDialog.Builder builder = new AlertDialog.Builder(LessonDetailActivity.this);
								builder.setTitle("提示");
								builder.setMessage("没有连接到wifi，很耗流量，不建议现在下载，土豪请忽略。。");
								builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										dialog.dismiss();
									}
								});
								builder.setNegativeButton("继续", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										Intent intent = new Intent(LessonDetailActivity.this, DownLoadService.class);
										intent.putExtra("url", course.videoUrl);
										intent.putExtra("courseName", course.name);
										startService(intent);
										dialog.dismiss();
									}
								});
								builder.show();
							}
						} else {
							Toast.makeText(LessonDetailActivity.this, "网络不可用！", Toast.LENGTH_LONG).show();
						}
					}
				}
			}
		});
		shareImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
				intent.putExtra(Intent.EXTRA_TEXT, course.videoUrl);
				startActivity(Intent.createChooser(intent, "分享"));
			}
		});
	}
	 public  void initData(){
      course = (Course)getIntent().getSerializableExtra("course");
		  course2=course;
		 LocalCourseDAo localCourseDAo = new LocalCourseDAo(this);
		 localCourse = localCourseDAo.find(course.name);
		 bundle=getIntent().getExtras();
		  username=bundle.getString("username");
		 Log.v("----------------", username);
		 new Thread(new Runnable() {
			 @Override
			 public void run() {
				 try {
					 HttpClient httpClient = new DefaultHttpClient();
					 HttpGet httpGet = new HttpGet("http://182.92.242.146/api/v1/reviews/show?coursename=" + course.name);
					 HttpResponse httpResponse = httpClient.execute(httpGet);
					 if(httpResponse.getStatusLine().getStatusCode()==200)
					 {
						 InputStream inputStream=httpResponse.getEntity().getContent();
						 String str=StreamTools.readInputStream(inputStream);
						 jsonArray=new JSONArray(str);

						 Log.v("8888888888888",str);
					 }
				 }
				 catch (Exception e)
				 {

				 }
			 }
		 }).start();

	 }

	public void initView(){
		ratingBar=(RatingBar)findViewById(R.id.ratingbar);
		shareImage = (ImageView)findViewById(R.id.img_share);
		downloadImage = (ImageView)findViewById(R.id.img_download);
		lessionImage = (SimpleDraweeView)findViewById(R.id.lesson_image);
		lessionImage.setImageURI(Uri.parse(course.imageUrl));
		lessonName = (TextView)findViewById(R.id.lesson_name);
		lessonName.setText("课程：" + course.name);


		courseTitle = (RelativeLayout)findViewById(R.id.relativeLayout_title);
		play=(ImageView)findViewById(R.id.lesson_play);
		shareImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ShareSDK.initSDK(LessonDetailActivity.this);
				OnekeyShare oks = new OnekeyShare();

				//关闭sso授权
				oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
				//oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
				// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
				oks.setTitle(course.name);
				// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
				oks.setTitleUrl("http://sharesdk.cn");
				// text是分享文本，所有平台都需要这个字段
				oks.setText("我是分享文本");
				// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
				//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
				// url仅在微信（包括好友和朋友圈）中使用
				oks.setUrl("http://sharesdk.cn");
				// comment是我对这条分享的评论，仅在人人网和QQ空间使用
				oks.setComment("我是测试评论文本");
				// site是分享此内容的网站名称，仅在QQ空间使用
				oks.setSite(getString(R.string.app_name));
				// siteUrl是分享此内容的网站地址，仅在QQ空间使用
				oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
				oks.show(LessonDetailActivity.this);
			}
		});
		share=getSharedPreferences(course.name+"rating",MODE_PRIVATE);


        Log.v("rating",Float.toString(share.getFloat(course.name+"rating", 4)));
		ratingBar.setRating(share.getFloat(course.name+"rating",4));
		play.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

                new Thread() {
                    public void run() {
                        try {

                            Date date = new Date();
                            HttpClient httpClient = new DefaultHttpClient();
                           // HttpGet httpGet = new HttpGet("182.92.242.146/api/v1/records?userinfo=" + username + "coursename=" + course.name + "trace=" + date.getYear() + "-" + date.getMonth() + "-" + date.getDay() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
                            HttpGet httpGet = new HttpGet("http://182.92.242.146/api/v1/records?userinfo=" + username + "&coursename=" + course.name +"&trace="+date.getYear() + "-" + date.getMonth() + "-" + date.getDay()+"-"+date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() );

                          //  Log.v("============", "182.92.242.146/api/v1/records?userinfo=" + username + "coursename=" + course.name + "trace=" + date.getYear() + "-" + date.getMonth() + "-" + date.getDay() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
                            HttpResponse httpResponse = httpClient.execute(httpGet);
                            System.out.println(httpResponse.getStatusLine().getStatusCode()+"*****************************************");
                            //  Toast.makeText(getActivity(), "ss", Toast.LENGTH_LONG).show();

                            if (httpResponse.getStatusLine().getStatusCode() == 200) {
//获取返回结果
                                // button.setText("提交成功");


                                //String result = EntityUtils.toString(httpResponse.getEntity());
                                InputStream inputStream = httpResponse.getEntity().getContent();
                                String result = StreamTools.readInputStream(inputStream);

                             //   Log.v("----------------", "ss");

                                } else {
                             //       Log.v("----------------", "nn");
                                }
                            }
                        catch(Exception e)
                        {

                        }
                    }
                }.start();
				if (NetUtil.isNetworkConnected(LessonDetailActivity.this)||localCourse !=null) {
					if(NetUtil.isWifi(LessonDetailActivity.this)||localCourse !=null) {
						Intent intent = new Intent(LessonDetailActivity.this, VideoPlayActivity.class);
						intent.putExtra("courseName", course.name);

						if (localCourse != null) {
							intent.putExtra("localPath", localCourse.getLocalPath());
						} else {
							intent.putExtra("lessonWebsite", course.videoUrl);
						}
						startActivity(intent);
					}else {
						AlertDialog.Builder builder = new AlertDialog.Builder(LessonDetailActivity.this);
						builder.setTitle("提示");
						builder.setMessage("没有连接到wifi，很耗流量，不建议现在观看，土豪请忽略。。");
						builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
							}
						});
						builder.setNegativeButton("继续", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								Intent intent = new Intent(LessonDetailActivity.this, DownLoadService.class);
								intent.putExtra("url", course.videoUrl);
								intent.putExtra("courseName", course.name);
								startService(intent);
								dialog.dismiss();
							}
						});
						builder.show();
					}
				}else {
					Toast.makeText(LessonDetailActivity.this,"网络无连接！",Toast.LENGTH_SHORT).show();
					Log.v("nnnnnnnnnnnnnnnn","没联网");
				}
			}
		});

		tvArr[0] = (TextView)findViewById(R.id.course_intro);
		tvArr[1] = (TextView)findViewById(R.id.teacher_intro);
		tvArr[2] = (TextView)findViewById(R.id.course_recommend);
		viewPager = (ViewPager)findViewById(R.id.course_detail_viewpager);
		viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
		viewPager.setOffscreenPageLimit(3);
		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {

				switch (position)
				{
					case 0:tvArr[0].setBackground(getResources().getDrawable(R.drawable.fenleianniu2));
						tvArr[1].setBackground(getResources().getDrawable(R.drawable.fenleianniu));
						tvArr[2].setBackground(getResources().getDrawable(R.drawable.fenleianniu));
						break;
					case 1:tvArr[0].setBackground(getResources().getDrawable(R.drawable.fenleianniu));
						tvArr[1].setBackground(getResources().getDrawable(R.drawable.fenleianniu2));
						tvArr[2].setBackground(getResources().getDrawable(R.drawable.fenleianniu));
						break;
					case 2:tvArr[0].setBackground(getResources().getDrawable(R.drawable.fenleianniu));
						tvArr[1].setBackground(getResources().getDrawable(R.drawable.fenleianniu));
						tvArr[2].setBackground(getResources().getDrawable(R.drawable.fenleianniu2));
						break;
					default:break;
				}
				//setTitleColor(position);
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

		tvArr[0].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tvArr[0].setBackground(getResources().getDrawable(R.drawable.fenleianniu2));
				tvArr[1].setBackground(getResources().getDrawable(R.drawable.fenleianniu));
				tvArr[2].setBackground(getResources().getDrawable(R.drawable.fenleianniu));
				viewPager.setCurrentItem(0);
			}
		});
		tvArr[1].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tvArr[0].setBackground(getResources().getDrawable(R.drawable.fenleianniu));
				tvArr[1].setBackground(getResources().getDrawable(R.drawable.fenleianniu2));
				tvArr[2].setBackground(getResources().getDrawable(R.drawable.fenleianniu));
				viewPager.setCurrentItem(1);
			}
		});
		tvArr[2].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tvArr[0].setBackground(getResources().getDrawable(R.drawable.fenleianniu));
				tvArr[1].setBackground(getResources().getDrawable(R.drawable.fenleianniu));
				tvArr[2].setBackground(getResources().getDrawable(R.drawable.fenleianniu2));
			//	Intent intent=new Intent(LessonDetailActivity.this,CourseRecommendFragment.class);
			//	startActivity(intent);
				viewPager.setCurrentItem(2);
			}
		});

	}

	public void setTitleColor(int index){
//      for(int i = 0; i< 3; i++){
//          if(i == index ){
//              tvArr[i].setBackgroundResource(selectedColor);
//          }else {
//              tvArr[i].setBackgroundResource(unselectorColor);
//          }
//      }
		if(pre != index) {
			tvArr[pre].setBackgroundResource(unselectorColor);
			tvArr[index].setBackgroundResource(selectedColor);
			pre = index;
		}
	}
	static class MyViewPagerAdapter extends CacheFragmentStatePagerAdapter{

		public MyViewPagerAdapter(FragmentManager fm) {
			super(fm);
		}


		@Override
		protected  Fragment createItem(int i) {
			Fragment fragment = null;
			switch (i){
				case 0:

               fragment = CourseIntroFragment.newInstance(course2.intro);

					break;
				case 1:
			  fragment = TeacherIntrroFragment.newInstance(course2.teacher_intro);
					break;
	 		    case 2:
			  fragment = new CourseRecommendFragment();
					break;
			}
			return fragment;
		}

		@Override
		public int getCount() {
			return 3;
		}
	}
}
