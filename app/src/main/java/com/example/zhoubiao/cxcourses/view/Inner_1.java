package com.example.zhoubiao.cxcourses.view;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.example.zhoubiao.cxcourses.R;
import com.example.zhoubiao.cxcourses.fragment.AllCourseFragment;
import com.example.zhoubiao.cxcourses.fragment.Allcoursefragment_1;
import com.example.zhoubiao.cxcourses.fragment.RecommendFragment;
/**
 * Created by BUPTIET on 2016/7/7.
 */
public class Inner_1 extends FragmentActivity{
    public static FragmentManager childFm;
    private ViewPager viewPager;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inner);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        childFm=getSupportFragmentManager();
        MyAdapter myAdapter=new MyAdapter(childFm);
        viewPager.setAdapter(myAdapter);
    }
    public static class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public int getCount() {
            return 1;
        }
        //得到每个item
        @Override
        public Fragment getItem(int position) {
            Fragment fragment1=new Allcoursefragment_1();
            return fragment1;
        }
        // 初始化每个页卡选项
        @Override
        public Object instantiateItem(ViewGroup arg0, int arg1) {
            // TODO Auto-generated method stub
            return super.instantiateItem(arg0, arg1);
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            System.out.println( "position Destory" + position);
            super.destroyItem(container, position, object);
        }
    }
}
