package com.example.zhoubiao.cxcourses.dataobject;

import java.io.Serializable;

/**
 * Created by zb-1 on 2015/9/20.
 */
public class Course implements Serializable{
    public String name;
    public String imageUrl;
    public boolean isRecommend;
    public String videoUrl;
    public String intro;
    public String teacher;
    public String teacher_intro;
    public  Course(String name,String imageUrl,boolean isRecommend,String videoUrl,String intro,String teacher,String teacher_intro){
        this.name = name;
        this.imageUrl = imageUrl;
        this.isRecommend = isRecommend;
        this.videoUrl = videoUrl;
        this.intro=intro;
        this.teacher=teacher;
        this.teacher_intro=teacher_intro;

    }
}

