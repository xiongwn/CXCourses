package com.example.zhoubiao.cxcourses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhoubiao.cxcourses.constant.Constant;

import org.apache.http.util.ByteArrayBuffer;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends Activity {
	Button buttonLogin;
	TextView sign_up;
	private SharedPreferences share;
	EditText userID;
	EditText password;
	EditText ph;
	EditText e_mail;
	LinearLayout phone;
	LinearLayout email;
private CheckBox checkBox;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		buttonLogin=(Button)findViewById(R.id.login);
		share=getSharedPreferences(Constant.LOGIN_USER_INFO_FILE, Context.MODE_PRIVATE);
		userID=(EditText) findViewById(R.id.editText1);
		password=(EditText) findViewById(R.id.editText2);
		sign_up=(TextView)findViewById(R.id.sign_up);

//		checkBox=(CheckBox)findViewById(R.id.cb);
//		checkBox.setChecked(share.getBoolean("ischeched",false));
//		if(checkBox.isChecked())
//		{
//			userID.setText(share.getString("username",""));
//			password.setText(share.getString("password",""));
//		}
//
//		checkBox.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Editor editor=share.edit();
//
//				editor.putBoolean("ischecked",checkBox.isChecked());
//				editor.commit();
//			}
//		});
		sign_up.setText(Html.fromHtml("<a href=\"http://182.92.242.146/users/sign_up\">进入注册页面</a>"));
		sign_up.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(LoginActivity.this,signup.class);
				startActivity(intent);

			}
		});
		buttonLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Editor edit=share.edit();
				edit.putString(Constant.SP_USER_ID, userID.getText().toString());
				edit.putBoolean(Constant.SP_IS_LOGIN, true);

				setResult(Constant.RESULT_CODE_LOGIN);
				final  String  username=userID.getText().toString().trim();
				final String ps=password.getText().toString().trim();
				edit.putString("username",username);
				edit.putString("password",ps);
				edit.commit();
				new Thread()
				{
					public void run()
					{
						try
						{
							URL url=new URL("http://182.92.242.146/api/v1/courses?info="+username+"&password="+ps);
							HttpURLConnection con=(HttpURLConnection)url.openConnection();

							con.setRequestMethod("GET");

							con.setConnectTimeout(5000);
							//con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.22 Safari/537.36 SE 2.X MetaSr 1.0");
							int code=con.getResponseCode();

							if(code==200)
							{

						InputStream is=con.getInputStream();

						ByteArrayOutputStream bao=new ByteArrayOutputStream();
						byte[] buffer=new byte[1024];
						int len=0;
						while((len=is.read(buffer))!=-1)
						{
							bao.write(buffer,0,len);
						}
						is.close();
						bao.close();
						byte[] result=bao.toByteArray();
						final String temp=new String(result);
						Log.v("----------",temp);
								if(temp.equals("success"))
								{

									Intent intent=new Intent();
									intent.setClass(getApplicationContext(), MainActivity.class);
									//Bundle bundle=new Bundle();
									//bundle.putString(username,"username");
									//intent.putExtra("bd",bundle);
									intent.putExtra("username",username);
									startActivity(intent);
								}
								else
								{

									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											Log.v("========",temp);
											Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_LONG).show();
										}
									});
								}
							}
							else
							{
								Log.v("=========",Integer.toString(con.getResponseCode()));
							}
						}
						catch (Exception e)
						{}
					}
				}.start();



				//Intent intent=new Intent();
				//intent.setClass(getApplicationContext(),MainActivity.class);
				//startActivity(intent);

			}
		});
		
	/*	buttonLogout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});*/
	}

}
