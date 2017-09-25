package com.example.zhoubiao.cxcourses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by BUPTIET on 2016/11/16.
 */
public class Person extends Activity {
    private Button button;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person);
        button=(Button)findViewById(R.id.logout);
        textView=(TextView)findViewById(R.id.personal_name);
        textView.setText(MainActivity.username);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Person.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
