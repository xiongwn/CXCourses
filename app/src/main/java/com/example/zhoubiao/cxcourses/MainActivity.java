package com.example.zhoubiao.cxcourses;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhoubiao.cxcourses.adapter.ContentListAdapter;
import com.example.zhoubiao.cxcourses.constant.Constant;
import com.example.zhoubiao.cxcourses.course_search.SearchActivity;
import com.example.zhoubiao.cxcourses.fragment.AllCourseFragment;

import com.example.zhoubiao.cxcourses.fragment.MyFragment;
import com.example.zhoubiao.cxcourses.fragment.RecommendFragment;
import com.example.zhoubiao.cxcourses.fragment.Ss;
import com.example.zhoubiao.cxcourses.net.RequestManager;
import com.example.zhoubiao.cxcourses.util.JsonParser;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.github.ksoichiro.android.observablescrollview.CacheFragmentStatePagerAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    public static ExecutorService pool = Executors.newCachedThreadPool();
    private boolean mHashToastShow = false;
    private ViewPager viewPager;
    Button button;
//    private TextView recommendTv;
//    private TextView allTv;
//    private TextView myTv;
    private TextView tvArr[] = new TextView[4];
    private int selectedColor = R.color.forestgreen;
    private int unselectorColor = R.color.lawngreen;
    private int pre = 0;

    public static String username;
private ImageView img;

    private ImageView search;
    private CharSequence mTitle;
    private ImageView person;
    public void onSectionAttached(int number) {
        Log.v("改变title", "进入");
        switch (number) {
            case 1:
                mTitle = "创新课程";
                break;
            case 2:
                mTitle = "案例";
                break;
            case 3:
                mTitle ="创新工具";
                break;
            case 0:
                mTitle="用户信息";
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
/*
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
*/
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
       RequestManager.init(this);
        initData();
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public  void initData(){

         username= getIntent().getStringExtra("username");
Log.v("===============", username);
    }
    public void initView(){
        /*
        good=(Drawable)getResources().getDrawable(R.drawable.good);
        all=(Drawable)getResources().getDrawable(R.drawable.all);
        my=(Drawable)getResources().getDrawable(R.drawable.my);
        good.setBounds(0,0,40,40);
        all.setBounds(0,0,40,40);
        my.setBounds(0,0,40,40);
        */


        search = (ImageView)findViewById(R.id.search);
        img=(ImageView)findViewById(R.id.icon_imv);
        person=(ImageView)findViewById(R.id.person);
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Person.class);
                startActivity(intent);
            }
        });
img.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getApplicationContext(),About.class);
        startActivity(intent);
    }
});
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });
      /*  button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Category.class);
                startActivity(intent);
            }
        });
        */
        tvArr[0] = (TextView)findViewById(R.id.recommend);
        tvArr[1] =(TextView)findViewById(R.id.all);
        tvArr[2] = (TextView)findViewById(R.id.my);
        tvArr[3]=(TextView)findViewById(R.id.mate);
Drawable drawable1=getResources().getDrawable(R.drawable.tuijian2);
        drawable1.setBounds(0,0,60,60);
        tvArr[0].setCompoundDrawables(null,drawable1,null,null);
        Drawable drawable2=getResources().getDrawable(R.drawable.fenlei2);
        drawable2.setBounds(0,0,60,60);
        tvArr[1].setCompoundDrawables(null,drawable2,null,null);
        Drawable drawable3=getResources().getDrawable(R.drawable.bendi);
        drawable3.setBounds(0,0,60,60);
        tvArr[2].setCompoundDrawables(null,drawable3,null,null);
        Drawable drawable4=getResources().getDrawable(R.drawable.jiaocai2);
        drawable4.setBounds(0,0,60,60);
        tvArr[3].setCompoundDrawables(null,drawable4,null,null);
        tvArr[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });
        tvArr[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });
        tvArr[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });
        tvArr[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(3);
            }
        });
        viewPager = (ViewPager)findViewById(R.id.hot_course);
        NavigationAdapter navigationAdapter = new NavigationAdapter(getSupportFragmentManager());
        viewPager.setAdapter(navigationAdapter);
        viewPager.setOffscreenPageLimit(4);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                //setTitleColor(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

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

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {


            if (!mHashToastShow) {
                mHashToastShow = true;
                Toast.makeText(this, "再按一次返回登录界面", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHashToastShow = false;
                    }
                }, 5000);

                return true;
            }
            finish();

            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    class NavigationAdapter extends CacheFragmentStatePagerAdapter{

    public NavigationAdapter(FragmentManager fm){
        super(fm);

     }
     @Override
     protected Fragment createItem(int i) {
         Fragment fragment = null;

         switch (i){
             case 0:
                 fragment = new RecommendFragment();
                 break;
             case 1:
                 fragment = new Category();
                 break;
             case 2:
                 fragment = new MyFragment();
                 break;
             case 3:
                 fragment=new Ss();
                 break;
         }
         return fragment;
     }

     @Override
     public int getCount() {
         return 4;
     }
 }

}
