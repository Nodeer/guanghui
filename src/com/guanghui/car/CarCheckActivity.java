package com.guanghui.car;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.guanghui.car.communications.MineAlert;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;
import com.guanghui.car.config.AppPreference;
import com.guanghui.car.db.ChekuangDengjiDB;
import com.guanghui.car.jingmicheck.JingMiCarCheckActivity;
import com.guanghui.car.ui.DetailADialog;
import com.guanghui.car.ui.DetailBDialog;
import com.guanghui.car.ui.DetailCDialog;

public class CarCheckActivity extends Activity {

	// add by 杜超
	private RadioButton outStar5;// 外观评级
	private RadioButton outStar4;
	private RadioButton outStar3;
	private RadioButton outStar2;
	private RadioButton outStar1;
	private RadioButton inStar5;// 内饰评级
	private RadioButton inStar4;
	private RadioButton inStar3;
	private RadioButton inStar2;
	private RadioButton inStar1;

	// 事故评级
	private RadioButton accident_a;
	private RadioButton accident_b;
	private RadioButton accident_c;

	private Button accident_a_detail;
	private Button accident_b_detail;
	private Button accident_c_detail;
	private Button saveButton;
	private Button commitButton;
	// 特殊车

	private RadioButton special_1;
	private RadioButton special_2;
	private RadioButton special_3;
	private RadioButton special_4;
	private RadioButton special_5;

	private RadioGroup special_group1;
	private RadioGroup special_group2;
	private RadioGroup special_group3;
	private RadioGroup special_group4;
	// 综合评级
	private RadioButton zonghe_t;
	private RadioButton zonghe_b;
	private RadioButton zonghe_r;
	private RadioButton zonghe_m;
	private RadioButton zonghe_i;
	private RadioButton zonghe_o;
	private RadioButton zonghe_e;

	private int AppearanceScore= 0;
	private int InteriorScore = 0;
	private int AccidentLlevel = 0;
	private int SpecialCar = 0;
	private int ComprehensiveScore = 0;
	private Boolean changeGroup = false;

	
	private EditText car_check_chekuangmiaoshu;
	private EditText car_check_chekuangmiaoshu2;
	private EditText car_check_chekuangmiaoshu3;
	private EditText car_check_chekuangmiaoshu4;
	
	private TextView chexiao_btn_1;
	private TextView chexiao_btn_2;
	private Context context = this;

	private int IsGHAuth;
	private MyHandler myhandler;
	private SoapMgr mgr;
	private SoapObject backSoapObject;
	
	private boolean edit;
	
	
	private String HistoryResourceID;
	
	private String miaoshu1;
	private String miaoshu2;
	private String miaoshu3;
	private String miaoshu4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.car_check);
		new BuildTab(context,1) ;
		
		HistoryResourceID = new AppPreference(context).getHistoryResourceID();
		
		edit = this.getIntent().getBooleanExtra("edit",	false);
		
		
		IsGHAuth = this.getIntent().getIntExtra("IsGHAuth", 1);
		
		// 空间初始化
		car_check_chekuangmiaoshu = (EditText)this.findViewById(R.id.car_check_chekuangmiaoshu);
		car_check_chekuangmiaoshu2 = (EditText)this.findViewById(R.id.car_check_chekuangmiaoshu2);
		car_check_chekuangmiaoshu3 = (EditText)this.findViewById(R.id.car_check_chekuangmiaoshu3);
		car_check_chekuangmiaoshu4 = (EditText)this.findViewById(R.id.car_check_chekuangmiaoshu4);
		
		
		chexiao_btn_1 = (TextView) this.findViewById(R.id.chexiao_btn_1);
		chexiao_btn_2 = (TextView) this.findViewById(R.id.chexiao_btn_2);
		
		outStar5 = (RadioButton) this.findViewById(R.id.out_star5);
		outStar4 = (RadioButton) this.findViewById(R.id.out_star4);
		outStar3 = (RadioButton) this.findViewById(R.id.out_star3);
		outStar2 = (RadioButton) this.findViewById(R.id.out_star2);
		outStar1 = (RadioButton) this.findViewById(R.id.out_star1);
		inStar5 = (RadioButton) findViewById(R.id.in_star5);
		inStar4 = (RadioButton) findViewById(R.id.in_star4);
		inStar3 = (RadioButton) findViewById(R.id.in_star3);
		inStar2 = (RadioButton) findViewById(R.id.in_star2);
		inStar1 = (RadioButton) findViewById(R.id.in_star1);
		accident_a = (RadioButton) findViewById(R.id.accident_a);
		accident_b = (RadioButton) findViewById(R.id.accident_b);
		accident_c = (RadioButton) findViewById(R.id.accident_c);
		accident_a_detail = (Button) findViewById(R.id.accident_a_detail);
		accident_b_detail = (Button) findViewById(R.id.accident_b_detail);
		accident_c_detail = (Button) findViewById(R.id.accident_c_detail);
		special_1 = (RadioButton) findViewById(R.id.special_one);
		special_2 = (RadioButton) findViewById(R.id.special_two);
		special_3 = (RadioButton) findViewById(R.id.special_three);
		special_4 = (RadioButton) findViewById(R.id.special_four);
		special_5 = (RadioButton) findViewById(R.id.special_five);
		zonghe_t = (RadioButton) findViewById(R.id.zonghe_t);
		zonghe_b = (RadioButton) findViewById(R.id.zonghe_b);
		zonghe_r = (RadioButton) findViewById(R.id.zonghe_r);
		zonghe_m = (RadioButton) findViewById(R.id.zonghe_m);
		zonghe_i = (RadioButton) findViewById(R.id.zonghe_i);
		zonghe_o = (RadioButton) findViewById(R.id.zonghe_o);
		zonghe_e = (RadioButton) findViewById(R.id.zonghe_e);
		special_group1 = (RadioGroup) findViewById(R.id.zonghe_group1);
		special_group2 = (RadioGroup) findViewById(R.id.zonghe_group2);
		special_group3 = (RadioGroup) findViewById(R.id.zonghe_group3);
		special_group4 = (RadioGroup) findViewById(R.id.zonghe_group4);
		special_group1.setOnCheckedChangeListener(mCheckedChangeListener);
		special_group2.setOnCheckedChangeListener(mCheckedChangeListener);
		special_group3.setOnCheckedChangeListener(mCheckedChangeListener);
		special_group4.setOnCheckedChangeListener(mCheckedChangeListener);
		outStar5.setOnClickListener(mListener);
		outStar4.setOnClickListener(mListener);
		outStar3.setOnClickListener(mListener);
		outStar2.setOnClickListener(mListener);
		outStar1.setOnClickListener(mListener);
		inStar5.setOnClickListener(mListener);
		inStar4.setOnClickListener(mListener);
		inStar3.setOnClickListener(mListener);
		inStar2.setOnClickListener(mListener);
		inStar1.setOnClickListener(mListener);
		accident_a.setOnClickListener(mListener);
		accident_a_detail.setOnClickListener(mListener);
		accident_b.setOnClickListener(mListener);
		accident_b_detail.setOnClickListener(mListener);
		accident_c.setOnClickListener(mListener);
		accident_c_detail.setOnClickListener(mListener);
		special_1.setOnClickListener(mListener);
		special_2.setOnClickListener(mListener);
		special_3.setOnClickListener(mListener);
		special_4.setOnClickListener(mListener);
		special_5.setOnClickListener(mListener);
		zonghe_t.setOnClickListener(mListener);
		zonghe_b.setOnClickListener(mListener);
		zonghe_r.setOnClickListener(mListener);
		zonghe_m.setOnClickListener(mListener);
		zonghe_i.setOnClickListener(mListener);
		zonghe_o.setOnClickListener(mListener);
		zonghe_e.setOnClickListener(mListener);
		
		
		chexiao_btn_1.setText(Html.fromHtml("<u>点击撤销</u>"));
		chexiao_btn_2.setText(Html.fromHtml("<u>点击撤销</u>"));
		chexiao_btn_1.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				accident_a.setChecked(false);
				accident_b.setChecked(false);
				accident_c.setChecked(false);
				AccidentLlevel = 0 ;
			}
		});
		chexiao_btn_2.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				special_1.setChecked(false);
				special_2.setChecked(false);
				special_3.setChecked(false);
				special_4.setChecked(false);
				special_5.setChecked(false);
				SpecialCar = 0;
			}
		});
		
		
		initTitle();
		initBottom();

		
		
		
		
		if(!HistoryResourceID.equals("0"))
		{			
			ChekuangDengjiDB db = new ChekuangDengjiDB(context);
			Cursor cursor = db.selectById(new AppPreference(context).getTaskId());		
			if(cursor.moveToFirst() == false)
			{
				initUIDataFromDB(HistoryResourceID);
			}		
			else
			{
				initUIDataFromDB(new AppPreference(context).getTaskId());
			}
			cursor.close();
			db.close();
		}
		else
		{			
			initUIDataFromDB(new AppPreference(context).getTaskId());
		}
		
		
		
		
	}

	private OnClickListener mListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.out_star5:
				if (outStar5.isChecked()) {
					Log.d("###", "五颗星");
					AppearanceScore= 1;
				}
				break;
			case R.id.out_star4:
				if (outStar4.isChecked()) {
					Log.d("###", "4颗星");
					AppearanceScore= 2;
				}
				break;
			case R.id.out_star3:
				if (outStar3.isChecked()) {
					Log.d("###", "3颗星");
					AppearanceScore= 3;
				}
				break;
			case R.id.out_star2:
				if (outStar2.isChecked()) {
					Log.d("###", "2颗星");
					AppearanceScore= 4;
				}
				break;
			case R.id.out_star1:
				if (outStar1.isChecked()) {
					Log.d("###", "1颗星");
					AppearanceScore= 5;
				}
				break;
			case R.id.in_star5:
				if (inStar5.isChecked()) {
					InteriorScore = 1;
				}
				break;
			case R.id.in_star4:
				if (inStar4.isChecked()) {
					InteriorScore = 2;
				}
				break;
			case R.id.in_star3:
				if (inStar3.isChecked()) {
					InteriorScore = 3;
				}
				break;
			case R.id.in_star2:
				if (inStar2.isChecked()) {
					InteriorScore = 4;
				}
				break;
			case R.id.in_star1:
				if (inStar1.isChecked()) {
					InteriorScore = 5;
				}
				break;
			case R.id.accident_a:
				if (accident_a.isChecked()) {
					AccidentLlevel = 1 ;
				}
				break;
			case R.id.accident_a_detail:
				new DetailADialog(context);
				break;
			case R.id.accident_b:
				if (accident_b.isChecked()) {
					AccidentLlevel = 2;
				}
				break;
			case R.id.accident_b_detail:
				new DetailBDialog(context);
				break;
			case R.id.accident_c:
				if (accident_c.isChecked()) {
					AccidentLlevel = 3;
				}
				break;
			case R.id.accident_c_detail:
				new DetailCDialog(context);
				break;
			case R.id.special_one:
				if (special_1.isChecked()) {
					SpecialCar = 1;
				}
				break;
			case R.id.special_two:
				if (special_2.isChecked()) {
					SpecialCar = 2;
				}
				break;
			case R.id.special_three:
				if (special_3.isChecked()) {
					SpecialCar = 3;
				}
				break;
			case R.id.special_four:
				if (special_4.isChecked()) {
					SpecialCar = 4;
				}
				break;
			case R.id.special_five:
				if (special_5.isChecked()) {
					SpecialCar = 5;
				}
				break;
			case R.id.zonghe_t:
				if (zonghe_t.isChecked()) {
					Log.d("###", "综合T");
					ComprehensiveScore = 1;
				}
				break;
			case R.id.zonghe_b:
				if (zonghe_b.isChecked()) {
					Log.d("###", "综合B");
					ComprehensiveScore = 2;
				}
				break;
			case R.id.zonghe_r:
				if (zonghe_r.isChecked()) {
					Log.d("###", "综合R");
					ComprehensiveScore = 3;
				}
				break;
			case R.id.zonghe_m:
				if (zonghe_m.isChecked()) {
					Log.d("###", "综合M");
					ComprehensiveScore = 4;
				}
				break;
			case R.id.zonghe_i:
				if (zonghe_i.isChecked()) {
					Log.d("###", "综合I");
					ComprehensiveScore = 5;
				}
				break;
			case R.id.zonghe_o:
				if (zonghe_o.isChecked()) {
					Log.d("###", "综合O");
					ComprehensiveScore = 6;
				}
				break;
			case R.id.zonghe_e:
				if (zonghe_e.isChecked()) {
					Log.d("###", "综合E");
					ComprehensiveScore = 7;
				}
				break;
			default:
				break;
			}
		}
	};

	private android.widget.RadioGroup.OnCheckedChangeListener mCheckedChangeListener = new android.widget.RadioGroup.OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			if (group != null && checkedId > -1 && changeGroup == false) {
				if (group.getId() == R.id.zonghe_group1) {
					changeGroup = true;
					special_group2.clearCheck();
					special_group3.clearCheck();
					special_group4.clearCheck();
					changeGroup = false;
				} else if (group.getId() == R.id.zonghe_group2) {
					changeGroup = true;
					special_group1.clearCheck();
					special_group3.clearCheck();
					special_group4.clearCheck();
					changeGroup = false;
				} else if (group.getId() == R.id.zonghe_group3) {
					changeGroup = true;
					special_group1.clearCheck();
					special_group2.clearCheck();
					special_group4.clearCheck();
					changeGroup = false;
				} else if (group.getId() == R.id.zonghe_group4) {
					changeGroup = true;
					special_group1.clearCheck();
					special_group2.clearCheck();
					special_group3.clearCheck();
					changeGroup = false;
				}
			}
		}
	};

	private void initTitle() {
		((TextView) findViewById(R.id.title_renwu_btn1))
				.setBackgroundDrawable(context.getResources().getDrawable(
						R.drawable.title_bg));
		((TextView) findViewById(R.id.title_renwu_btn2))
				.setBackgroundDrawable(context.getResources().getDrawable(
						R.drawable.title_bg));
		((TextView) findViewById(R.id.title_renwu_btn3))
				.setBackgroundDrawable(context.getResources().getDrawable(
						R.drawable.bg));

	}

	private void initBottom() {
		saveButton = (Button) findViewById(R.id.stop_save);
		commitButton = (Button) findViewById(R.id.comfirm_communit);
		saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(AppearanceScore != 0)
				{
					if(InteriorScore != 0){
						if(ComprehensiveScore != 0)
						{
							if(saveData())
							{
								final MineAlert alert = new MineAlert(context);
								alert.createAlertOneButton(false,"已成功保存", 
										new View.OnClickListener() {								
											@Override
											public void onClick(View v) {
												alert.dimiss();
											}
										});
							}
							else
							{
								final MineAlert alert = new MineAlert(context);
								alert.createAlertOneButton(false,"保存失败", 
										new View.OnClickListener() {								
											@Override
											public void onClick(View v) {
												alert.dimiss();
											}
										});
							}
						}
						else
						{
							Toast.makeText(context, "请选择综合评级", Toast.LENGTH_SHORT).show();
						}
					
					}
					else
					{
						Toast.makeText(context, "请选择内饰评星", Toast.LENGTH_SHORT).show();
					}
				}
				else
				{
					Toast.makeText(context, "请选择外观评星", Toast.LENGTH_SHORT).show();
				}
//				
			}
		});
		commitButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(AppearanceScore != 0)
				{
					if(InteriorScore != 0){
						if(ComprehensiveScore != 0)
						{
							miaoshu1 = car_check_chekuangmiaoshu.getText().toString().trim();
							miaoshu2 = car_check_chekuangmiaoshu2.getText().toString().trim();
							miaoshu3 = car_check_chekuangmiaoshu3.getText().toString().trim();
							miaoshu4 = car_check_chekuangmiaoshu4.getText().toString().trim();
							if(miaoshu1.equals(""))
							{
								miaoshu1 = "无";
							}
							if(miaoshu2.equals(""))
							{
								miaoshu2 = "无";
							}
							if(miaoshu3.equals(""))
							{
								miaoshu3 = "无";
							}
							if(miaoshu4.equals(""))
							{
								miaoshu4 = "无";
							}							
							saveCarCheckInfo(String.valueOf(AppearanceScore), String.valueOf(InteriorScore), String.valueOf(AccidentLlevel), String.valueOf(SpecialCar),String.valueOf( ComprehensiveScore),
									"外观描述:"+ miaoshu1,
									",内饰描述:"+ miaoshu2,
									",骨架描述:"+ miaoshu3,
									",装置描述:"+ miaoshu4
									);
						}
						else
						{
							Toast.makeText(context, "请选择综合评级", Toast.LENGTH_SHORT).show();
						}
					
					}
					else
					{
						Toast.makeText(context, "请选择内饰评星", Toast.LENGTH_SHORT).show();
					}
				}
				else
				{
					Toast.makeText(context, "请选择外观评星", Toast.LENGTH_SHORT).show();
				}
//				
				
			}
		});
	}
	
	/**
     * 获取用户的基本信息
     */
    private void saveCarCheckInfo(String AppearanceScore,String InteriorScore,String AccidentLlevel,String SpecialCar,String ComprehensiveScore,String Comments,String Comments2,String Comments3,String Comments4)
    {		
    	saveData();
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "SaveCarState");
		
		soapParameter.addProperty("ResourceID", new AppPreference(context).getTaskId());
		soapParameter.addProperty("AppearanceScore", AppearanceScore);
		soapParameter.addProperty("InteriorScore", InteriorScore);	
		soapParameter.addProperty("AccidentLlevel", AccidentLlevel);
		soapParameter.addProperty("SpecialCar", SpecialCar);
		soapParameter.addProperty("ComprehensiveScore", ComprehensiveScore);
		soapParameter.addProperty("Comments", Comments+Comments2+Comments3+Comments4);
		
		
		myhandler =  new MyHandler()
		{
			@Override
			public void success(Message msg) {
				backSoapObject = mgr.getServiceBackSoapObject();						
				if(backSoapObject!=null)
				{
					String Retcode = ((SoapObject)backSoapObject.getProperty(0)).getProperty("Retcode").toString();
					//0成功，1失败
					if(Retcode.equals("0"))
					{
						Log.d("###", "发送成功");
						
						
						if(edit)
						{							
							Intent intent = new Intent();
							intent.setClass(context, JingMiCarCheckActivity.class);
							setResult(3, intent);							
							finish();
						}
						else
						{
							Intent intent = new Intent();
							intent.setClass(context, CheckTaskPreviewActivity.class);							
							intent.putExtra("IsGHAuth", IsGHAuth);							
							startActivity(intent);			
						}	
					}
				}
			}
			@Override
			public void failed(Message msg) {				
				
			}
		};
				mgr = new SoapMgr(context,
						null, 
						null, 
						SoapAction.SaveCarState , 
						"SaveCarState",
						soapParameter,
						myhandler,
						true,
						false
				);
    }
    
    
    /**
	 * 保存数据到本地数据库
	 * @return  是否成功
	 */
	public boolean saveData()
	{
		//获得数据		
		
		
		miaoshu1 = car_check_chekuangmiaoshu.getText().toString().trim();
		miaoshu2 = car_check_chekuangmiaoshu2.getText().toString().trim();
		miaoshu3 = car_check_chekuangmiaoshu3.getText().toString().trim();
		miaoshu4 = car_check_chekuangmiaoshu4.getText().toString().trim();
		if(miaoshu1.equals(""))
		{
			miaoshu1 = "无";
		}
		if(miaoshu2.equals(""))
		{
			miaoshu2 = "无";
		}
		if(miaoshu3.equals(""))
		{
			miaoshu3 = "无";
		}
		if(miaoshu4.equals(""))
		{
			miaoshu4 = "无";
		}							
		
		ChekuangDengjiDB db = new ChekuangDengjiDB(context);
		long rowID = db.insert(new AppPreference(context).getTaskId(), 
				AppearanceScore+"", 
				InteriorScore+"", 
				AccidentLlevel+"", 
				SpecialCar+"", 
				ComprehensiveScore+"", 
				"外观描述:"+ miaoshu1 +
				",内饰描述:"+ miaoshu2 +
				",骨架描述:"+ miaoshu3 +
				",装置描述:"+ miaoshu4);
		db.close();
		if(rowID!=-1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {		
		 if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0 &&event.getAction() == KeyEvent.ACTION_DOWN) {	        	
			 	saveData();
	        }
		 return super.dispatchKeyEvent(event); 
	}
	
	/**
	 * 从本地数据库读取数据对UI进行初始化
	 * 任务ID
	 */
	private void initUIDataFromDB(String taskID)
	{		
		ChekuangDengjiDB db = new ChekuangDengjiDB(context);
		Cursor cursor = db.selectById(taskID);		
		if(cursor.moveToFirst() == false)
		{
			return;
		}
		String ResourceID = cursor.getString(0);
		String AppearanceScore = cursor.getString(1);
		String InteriorScore = cursor.getString(2);
		String AccidentLlevel = cursor.getString(3);
		String SpecialCar = cursor.getString(4);
		String ComprehensiveScore = cursor.getString(5);
		String Comments = cursor.getString(6);
		
		this.AppearanceScore = Integer.parseInt(AppearanceScore);
		this.InteriorScore = Integer.parseInt(InteriorScore);
		this.AccidentLlevel = Integer.parseInt(AccidentLlevel);
		this.SpecialCar = Integer.parseInt(SpecialCar);
		this.ComprehensiveScore = Integer.parseInt(ComprehensiveScore);
		
		switch(Integer.parseInt(AppearanceScore))
		{
			case 1:
				outStar5.setChecked(true);
				break;
			case 2:
				outStar4.setChecked(true);
				break;
			case 3:
				outStar3.setChecked(true);
				break;
			case 4:
				outStar2.setChecked(true);
				break;
			case 5:
				outStar1.setChecked(true);
				break;		
		}
		switch(Integer.parseInt(InteriorScore))
		{
			case 1:
				inStar5.setChecked(true);
				break;
			case 2:
				inStar4.setChecked(true);
				break;
			case 3:
				inStar3.setChecked(true);
				break;
			case 4:
				inStar2.setChecked(true);
				break;
			case 5:
				inStar1.setChecked(true);
				break;		
		}
		switch(Integer.parseInt(AccidentLlevel))
		{
			case 1:
				accident_a.setChecked(true);
				break;
			case 2:
				accident_b.setChecked(true);
				break;
			case 3:
				accident_c.setChecked(true);
				break;
		}
		switch(Integer.parseInt(SpecialCar))
		{
			case 1:
				special_1.setChecked(true);
				break;
			case 2:
				special_2.setChecked(true);
				break;
			case 3:
				special_3.setChecked(true);
				break;
			case 4:
				special_4.setChecked(true);
				break;
			case 5:
				special_5.setChecked(true);
				break;		
		}
		switch(Integer.parseInt(ComprehensiveScore))
		{
			case 1:
				zonghe_t.setChecked(true);
				break;
			case 2:
				zonghe_b.setChecked(true);
				break;
			case 3:
				zonghe_r.setChecked(true);
				break;
			case 4:
				zonghe_m.setChecked(true);
				break;
			case 5:
				zonghe_i.setChecked(true);
				break;	
			case 6:
				zonghe_o.setChecked(true);
				break;	
			case 7:
				zonghe_e.setChecked(true);
				break;	
		}		
		
		
		String str1 = Comments.substring(Comments.indexOf("外观描述:")+5, Comments.indexOf(",内饰描述:"));
		String str2 = Comments.substring(Comments.indexOf("内饰描述:")+5, Comments.indexOf(",骨架描述:"));
		String str3 = Comments.substring(Comments.indexOf("骨架描述:")+5, Comments.indexOf(",装置描述:"));
		String str4 = Comments.substring(Comments.indexOf("装置描述:")+5, Comments.length());
		
		
		if(!str1.equals("无"))
		{
			car_check_chekuangmiaoshu.setText(str1);		
		}
		if(!str2.equals("无"))
		{
			car_check_chekuangmiaoshu2.setText(str2);
		}
		if(!str3.equals("无"))
		{
			car_check_chekuangmiaoshu3.setText(str3);
		}
		if(!str4.equals("无"))
		{
			car_check_chekuangmiaoshu4.setText(str4);
		}
	}
    
    
}
