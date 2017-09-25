package com.example.zhoubiao.cxcourses;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MatrixActivity extends Activity {
	static int matrixX;
	static int matrixY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_matrix);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment())
					.commit();
		}
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.matrix, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
		case R.id.action_settings:
			break;
		}
		return super.onOptionsItemSelected(item);

	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		private int[] itemArrayResourse = { R.array.category0, R.array.category1,
				R.array.category2, R.array.category3, R.array.category4 ,R.array.category5,R.array.category6,R.array.category7};
		private ArrayAdapter<CharSequence> i1spinnerAdapter;
		private ArrayAdapter<CharSequence> i2spinnerAdapter;
		Spinner c1spinner;
		Spinner i1spinner;
		Spinner c2spinner;
		Spinner i2spinner;
//		TextView resultText;
//		ListView resultList;
		Button resultButton;
		int c1, i1, c2, i2;
//		private ContentListAdapter adapter;
/*		private List<CommonCourse> cases = new LinkedList<CommonCourse>();
		private JsonParser jsonParser = new JsonParser();*/
	/*	private Handler refreshHandler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				switch (msg.what) {
				case Constant.LIST_REFRESH:
					cases = (List<CommonCourse>) msg.obj;
					adapter.setList(cases);
					adapter.notifyDataSetChanged();
					break;
				}
			};
		};
		private Thread refreshListThread;*/

		/*public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			adapter = new ContentListAdapter(cases, getActivity());
			refreshListThread = new Thread() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					super.run();
					String casesString = Constant.matrix[0][0];
					String singleCaseArray[] = casesString.split(",");
					Message msg = refreshHandler.obtainMessage(Constant.LIST_REFRESH);
					msg.obj = jsonParser.getCreativeCoursefromJson(getJsonFromAssets("Course" + 0
							+ Constant.JSON_FILE_SUFFIX));
					msg.sendToTarget();
				}
			};
		};*/

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_matrix, container, false);
			c1spinner = (Spinner) rootView.findViewById(R.id.spinner_category_1);
			i1spinner = (Spinner) rootView.findViewById(R.id.spinner_item_1);
			c2spinner = (Spinner) rootView.findViewById(R.id.spinner_category_2);
			i2spinner = (Spinner) rootView.findViewById(R.id.spinner_item_2);
//			resultText = (TextView) rootView.findViewById(R.id.result_text);
//			resultList = (ListView) rootView.findViewById(R.id.list_maxtix_result);
			mSpinnerOnItemSelectedListener listener = new mSpinnerOnItemSelectedListener();
			i1spinner.setOnItemSelectedListener(listener);
			c1spinner.setOnItemSelectedListener(listener);
			i2spinner.setOnItemSelectedListener(listener);
			c2spinner.setOnItemSelectedListener(listener);
//			resultList.setAdapter(adapter);
			resultButton=(Button) rootView.findViewById(R.id.confirm_button);
			resultButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(getActivity(),MatrixResultActivity.class);
					intent.putExtra("matrixX", matrixX);
					intent.putExtra("matrixY", matrixY);
					startActivity(intent);
					
				}
			});
			return rootView;
		}

		class mSpinnerOnItemSelectedListener implements OnItemSelectedListener {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				switch (arg0.getId()) {

				case R.id.spinner_category_1:
					i1spinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
							 R.array.optimizCategory0, android.R.layout.simple_list_item_1);
					i1spinner.setAdapter(i1spinnerAdapter);
					c1 = arg2;
					break;
				case R.id.spinner_category_2:
					i2spinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
							itemArrayResourse[arg2], android.R.layout.simple_list_item_1);
					i2spinner.setAdapter(i2spinnerAdapter);
					c2 = arg2;
					break;
				case R.id.spinner_item_1:
					i1 = arg2;

					if(c1==c2&&i1==i2)
					{
						resultButton.setText("请选择不同的矛盾个体");
						resultButton.setClickable(false);
					}
					else
					{

						matrixX=c1*6+i1;
						matrixY=c2*6+i2;
						resultButton.setText("查看处理方法");
						resultButton.setClickable(true);
					}
					break;
				case R.id.spinner_item_2:
					i2 = arg2;

					if(c1==c2&&i1==i2)
					{
						resultButton.setText("请选择不同的矛盾个体");
						resultButton.setClickable(false);
					}
					else
					{

						matrixX=c1*6+i1;
						matrixY=c2*6+i2;
						resultButton.setText("查看处理方法");
						resultButton.setClickable(true);
					}
					break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		}

		/*public String getJsonFromAssets(String fileName) {

			StringBuffer sb = new StringBuffer();
			String line = null;
			BufferedReader buffer = null;
			try {
				InputStream fileStream = getActivity().getResources().getAssets().open(fileName);
				buffer = new BufferedReader(new InputStreamReader(fileStream));
				while ((line = buffer.readLine()) != null) {
					sb.append(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					buffer.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return sb.toString();
		}*/
	}

}
