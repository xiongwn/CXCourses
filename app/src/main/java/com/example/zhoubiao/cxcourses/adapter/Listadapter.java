package com.example.zhoubiao.cxcourses.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhoubiao.cxcourses.R;
import com.example.zhoubiao.cxcourses.model.Comment;

import org.json.JSONObject;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by BUPTIET on 2016/11/9.
 */
public class Listadapter extends RecyclerView.Adapter<Listadapter.MyViewHolder>{
    private List<JSONObject> list;
    private Context context;
 public   Listadapter(Context context,List<JSONObject> list)
    {
        this.list=list;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder=new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.mylist,null));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        try {
            holder.user.setText(list.get(position).getString("userinfo")+"：");
            holder.comment.setText(list.get(position).getString("words"));
        }
        catch (Exception e)
        {
            System.out.print(e);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView user;
        private TextView comment;
public MyViewHolder(View view)
{
    super(view);
    user=(TextView)view.findViewById(R.id.user);
    comment=(TextView)view.findViewById(R.id.comment);
}
    }
}
