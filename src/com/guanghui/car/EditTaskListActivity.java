package com.guanghui.car;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * 编辑任务信息列表
 * @author zhangyun
 *
 */
public class EditTaskListActivity extends Activity{
	
	private Context context = this;
	private Button edit_task_list_btn_back;
	private ListView edit_task_list_view;
	private List<String> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.edit_task_list_activity);
		
		new BuildTab(context,1) ;		

		//String rid = this.getIntent().getExtras().getString("taskid");
		edit_task_list_view = (ListView)this.findViewById(R.id.edit_task_list_view);
		edit_task_list_btn_back = (Button)this.findViewById(R.id.edit_task_list_btn_back);
		edit_task_list_btn_back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		data = new ArrayList<String>();
		data.add("任务信息");
		data.add("手续登记信息");
		data.add("车型信息");
		data.add("车况信息");
		data.add("车价评估信息");
		edit_task_list_view.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,data));
		
		
		edit_task_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent();
				switch(arg2)
				{
					case 0:						
						intent.setClass(context, BuildTaskActivity.class);				
						
						break;
					case 1:
						break;
					case 2:
						break;
					case 3:
						break;
					case 4:
						break;
				}
				startActivity(intent);
			}
		});
		
		
	}
	
}
