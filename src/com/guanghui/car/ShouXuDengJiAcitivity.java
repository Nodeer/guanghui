package com.guanghui.car;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.guanghui.car.adapter.CarLeiXingAdapter;
import com.guanghui.car.common.PictureUtil;
import com.guanghui.car.common.ToastUtilMgr;
import com.guanghui.car.communications.MineAlert;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;
import com.guanghui.car.config.AppConfig;
import com.guanghui.car.config.AppPreference;
import com.guanghui.car.db.ShouxuDengjiDB;
import com.guanghui.car.ui.AllCapTransformationMethod;
import com.guanghui.car.ui.DateTimePickDialogUtil;

/**
 * 手续登记主界面
 * 
 * @author zhangyun
 * 
 */
public class ShouXuDengJiAcitivity extends Activity {

	private Context context = this;

	private Button paizhao;
	
	private String HistoryResourceID ;
	

	private List<HashMap> listmap;
	
	private int RESULT_CAPTURE_IMAGE = 1;

	private Button car_shouxu_dengji_btn_back;

	private ImageView paizhao_image;
	
	//获得车牌归属地的按钮
	private Button shouxu_dengji_btn_getguishudi;

	// 车牌号
	private EditText sx_edit_car_number;

	// 归属地
	private EditText sx_edit_car_guishudi;

	// 车牌识别代号
	private EditText sx_edit_car_shibie_numer;

	// 车牌识别代号
//	private RadioButton sx_xingzhi_radion1;
//
//	private RadioButton sx_xingzhi_radion2;
//
//	private RadioButton sx_xingzhi_radion3;
	
	private Spinner sx_xingzhi_spineer;
	

	// 车辆类型
	private RadioButton sx_edit_car_type1;
	private RadioButton sx_edit_car_type2;
	// 车辆品牌型号
	private EditText sx_edit_car_pinpai_xinghao;

	// 发动机号码
	private EditText sx_edit_car_fadongji_number;

	// 出厂日期
	private EditText sx_edit_car_chuchang_date;

	// 首次注册日期
	private EditText sx_edit_car_zhuce_date;

	// 强制报废日期
	private EditText sx_edit_car_baofei_date;
	
	private Button sx_btn_qiangzhibaofei;

	// 所有人
	private RadioButton sx_suoyouren_radion1;

	private RadioButton sx_suoyouren_radion2;

	private RadioButton sx_suoyouren_radion3;

	// 排放标准
	private RadioButton sx_paifang_radion1;

	private RadioButton sx_paifang_radion2;

	private RadioButton sx_paifang_radion3;

	private RadioButton sx_paifang_radion4;

	private RadioButton sx_paifang_radion5;

	// 排放标准验证码
	private Button sx_paifang_button_huoqu;

	// 办证状态
	private RadioButton sx_banzheng_radion1;

	private RadioButton sx_banzheng_radion2;

	private RadioButton sx_banzheng_radion3;

	private RadioButton sx_banzheng_radion4;

	// 变更记录
	private RadioButton sx_biangeng_radion1;

	private RadioButton sx_biangeng_radion2;

	private EditText sx_biangeng_cishu;

	private CheckBox sx_biangeng_checkbox1;

	private CheckBox sx_biangeng_checkbox2;

	private CheckBox sx_biangeng_checkbox3;

	// 购置税
	private RadioButton sx_gouzhi_radion1;

	private RadioButton sx_gouzhi_radion2;

	private RadioButton sx_gouzhi_radion3;

	// 交强险有效期
	private EditText sx_edit_jiaoqiangxian_date;

	// 年检日期
	private EditText sx_edit_nianjian_date;

	// 通行费有效期
	private EditText sx_edit_tongxingfei_date;

	// 违章处理
	private EditText sx_edit_fen;

	private EditText sx_edit_yuan;

	// 牌照额度
	private RadioButton sx_paizhao_edu_radion1;

	private RadioButton sx_paizhao_edu_radion2;

	// 原厂钥匙
	private RadioButton sx_yaoshi_radion1;

	private EditText sx_yaoshi_ba;

	private RadioButton sx_yaoshi_radion2;

	// 随车工具
	private RadioButton sx_gongju_radion1;

	private RadioButton sx_gongju_radion2;

	private EditText sx_gongju_lost;

	// 车辆使用说明书
	private RadioButton sx_shoumingshu_radion1;

	private RadioButton sx_shoumingshu_radion2;

	// 车辆保养手册
	private RadioButton sx_baoyang_radion1;

	private RadioButton sx_baoyang_radion2;

	private Button wukexuanxiang;

	private String singleCameraImgPath1;
	
	
	private Button shouxu_dengji1_btn_shibie;
	
	private Bitmap bm;
	
	
	
	private Button buqueding13;
	private Button buqueding14;
	private Button buqueding15;
	private Button buqueding16;
	private Button buqueding17;
	private Button buqueding18;
	private Button buqueding19;
	private Button buqueding20;
	
	
	
	
	private boolean edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_shouxu_dengji);
		
		//初始化HistoryResourceID
		new AppPreference(context).setHistoryResourceID("0");
		
		edit = this.getIntent().getBooleanExtra("edit", false);
		
		
		new BuildTab(context,1) ;
		initTitle();
		initBottom();
		wukexuanxiang = (Button) this
				.findViewById(R.id.shouxu_dengji_btn_wukexuanxiang);

		paizhao = (Button) this.findViewById(R.id.shouxu_dengji1_btn_paizhao);
		shouxu_dengji1_btn_shibie = (Button) this.findViewById(R.id.shouxu_dengji1_btn_shibie);
		// 返回按钮
		car_shouxu_dengji_btn_back = (Button) this
				.findViewById(R.id.car_shouxu_dengji_btn_back);
		// 车牌号
		sx_edit_car_number = (EditText) findViewById(R.id.sx_edit_car_number);
		sx_edit_car_number
				.setTransformationMethod(new AllCapTransformationMethod());
		// 归属地
		sx_edit_car_guishudi = (EditText) findViewById(R.id.sx_edit_car_guishudi);
		shouxu_dengji_btn_getguishudi = (Button) findViewById(R.id.shouxu_dengji_btn_getguishudi);
		// 车牌识别代号
		sx_edit_car_shibie_numer = (EditText) findViewById(R.id.sx_edit_car_shibie_numer);

		// 车辆类型
		sx_edit_car_type1 = (RadioButton) findViewById(R.id.sx_edit_car_type1);
		sx_edit_car_type2 = (RadioButton) findViewById(R.id.sx_edit_car_type2);
		// 车辆品牌型号
		sx_edit_car_pinpai_xinghao = (EditText) findViewById(R.id.sx_edit_car_pinpai_xinghao);
		// 发动机号码
		sx_edit_car_fadongji_number = (EditText) findViewById(R.id.sx_edit_car_fadongji_number);
		// 出厂日期
		sx_edit_car_chuchang_date = (EditText) findViewById(R.id.sx_edit_car_chuchang_date);
		sx_edit_car_chuchang_date.setOnClickListener(timeEditClick);
		// 首次注册日期
		sx_edit_car_zhuce_date = (EditText) findViewById(R.id.sx_edit_car_zhuce_date);
		sx_edit_car_zhuce_date.setOnClickListener(timeEditClick);
		// 强制报废日期
		sx_edit_car_baofei_date = (EditText) findViewById(R.id.sx_edit_car_baofei_date);
		sx_edit_car_baofei_date.setOnClickListener(timeEditClick);
		sx_biangeng_cishu = (EditText) findViewById(R.id.sx_biangeng_cishu);
		sx_btn_qiangzhibaofei = (Button) findViewById(R.id.sx_btn_qiangzhibaofei);
		
		// 交强险有效期
		sx_edit_jiaoqiangxian_date = (EditText) findViewById(R.id.sx_edit_jiaoqiangxian_date);
		sx_edit_jiaoqiangxian_date.setOnClickListener(timeEditClick);
		// 年检日期
		sx_edit_nianjian_date = (EditText) findViewById(R.id.sx_edit_nianjian_date);
		sx_edit_nianjian_date.setOnClickListener(timeEditClick);
		// 通行费有效期
		sx_edit_tongxingfei_date = (EditText) findViewById(R.id.sx_edit_tongxingfei_date);
		sx_edit_tongxingfei_date.setOnClickListener(timeEditClick);
		// 违章处理
		sx_edit_fen = (EditText) findViewById(R.id.sx_edit_fen);
		sx_edit_yuan = (EditText) findViewById(R.id.sx_edit_yuan);
		sx_yaoshi_ba = (EditText) findViewById(R.id.sx_yaoshi_ba);

		sx_edit_fen.setNextFocusDownId(R.id.sx_edit_yuan);

		// 车牌识别代号
//		sx_xingzhi_radion1 = (RadioButton) findViewById(R.id.sx_xingzhi_radion1);
//		sx_xingzhi_radion2 = (RadioButton) findViewById(R.id.sx_xingzhi_radion2);
//		sx_xingzhi_radion3 = (RadioButton) findViewById(R.id.sx_xingzhi_radion3);
		sx_xingzhi_spineer = (Spinner) findViewById(R.id.sx_xingzhi_spineer);
		
		listmap = new ArrayList<HashMap>();		
		HashMap map = new HashMap();
		map.put("key", "非营运");
		map.put("value", "非营运");
		listmap.add(map);
		
		
		HashMap map22 = new HashMap();
		map22.put("key", "营转非");
		map22.put("value", "营转非");
		listmap.add(map22);
		
		
		HashMap map1 = new HashMap();
		map1.put("key", "出租营转非");
		map1.put("value", "出租营转非");
		listmap.add(map1);
		HashMap map2 = new HashMap();
		map2.put("key", "租赁营转非");
		map2.put("value", "租赁营转非");
		listmap.add(map2);
		HashMap map3 = new HashMap();
		map3.put("key", "教练营转非");
		map3.put("value", "教练营转非");
		listmap.add(map3);
		HashMap map4 = new HashMap();
		map4.put("key", "公交营转非");
		map4.put("value", "公交营转非");
		listmap.add(map4);
		HashMap map5 = new HashMap();
		map5.put("key", "其他营转非");
		map5.put("value", "其他营转非");
		listmap.add(map5);
		HashMap map51 = new HashMap();
		map51.put("key", "营转非不确定");
		map51.put("value", "营转非不确定");
		listmap.add(map51);
		
		HashMap map33 = new HashMap();
		map33.put("key", "营运");
		map33.put("value", "营运");
		listmap.add(map33);
		
		
		
		HashMap map6 = new HashMap();
		map6.put("key", "出租营运");
		map6.put("value", "出租营运");
		listmap.add(map6);
		HashMap map7 = new HashMap();
		map7.put("key", "租赁营运");
		map7.put("value", "租赁营运");
		listmap.add(map7);
		HashMap map8 = new HashMap();
		map8.put("key", "教练营运");
		map8.put("value", "教练营运");
		listmap.add(map8);
		HashMap map9 = new HashMap();
		map9.put("key", "公交营运");
		map9.put("value", "公交营运");
		listmap.add(map9);
		HashMap map10 = new HashMap();
		map10.put("key", "其他营运");
		map10.put("value", "其他营运");
		listmap.add(map10);
		HashMap map101 = new HashMap();
		map101.put("key", "营运不确定");
		map101.put("value", "营运不确定");
		listmap.add(map101);
		CarLeiXingAdapter adapter = new CarLeiXingAdapter(context,listmap);
		sx_xingzhi_spineer.setAdapter(adapter);
						
				

		// 所有人
		sx_suoyouren_radion1 = (RadioButton) findViewById(R.id.sx_suoyouren_radion1);
		sx_suoyouren_radion2 = (RadioButton) findViewById(R.id.sx_suoyouren_radion2);
		sx_suoyouren_radion3 = (RadioButton) findViewById(R.id.sx_suoyouren_radion3);
		// 排放标准
		sx_paifang_radion1 = (RadioButton) findViewById(R.id.sx_paifang_radion1);
		sx_paifang_radion2 = (RadioButton) findViewById(R.id.sx_paifang_radion2);
		sx_paifang_radion3 = (RadioButton) findViewById(R.id.sx_paifang_radion3);
		sx_paifang_radion4 = (RadioButton) findViewById(R.id.sx_paifang_radion4);
		sx_paifang_radion5 = (RadioButton) findViewById(R.id.sx_paifang_radion5);
		sx_paifang_button_huoqu = (Button) findViewById(R.id.sx_paifang_button_huoqu);
		// 办证状态
		sx_banzheng_radion1 = (RadioButton) findViewById(R.id.sx_banzheng_radion1);
		sx_banzheng_radion2 = (RadioButton) findViewById(R.id.sx_banzheng_radion2);
		sx_banzheng_radion3 = (RadioButton) findViewById(R.id.sx_banzheng_radion3);
		sx_banzheng_radion4 = (RadioButton) findViewById(R.id.sx_banzheng_radion4);
		// 变更记录
		sx_biangeng_radion1 = (RadioButton) findViewById(R.id.sx_biangeng_radion1);
		sx_biangeng_radion2 = (RadioButton) findViewById(R.id.sx_biangeng_radion2);
		sx_biangeng_checkbox1 = (CheckBox) findViewById(R.id.sx_biangeng_checkbox1);
		sx_biangeng_checkbox2 = (CheckBox) findViewById(R.id.sx_biangeng_checkbox2);
		sx_biangeng_checkbox3 = (CheckBox) findViewById(R.id.sx_biangeng_checkbox3);

		// 购置税
		sx_gouzhi_radion1 = (RadioButton) findViewById(R.id.sx_gouzhi_radion1);
		sx_gouzhi_radion2 = (RadioButton) findViewById(R.id.sx_gouzhi_radion2);
		sx_gouzhi_radion3 = (RadioButton) findViewById(R.id.sx_gouzhi_radion3);

		// 牌照额度
		sx_paizhao_edu_radion1 = (RadioButton) findViewById(R.id.sx_paizhao_edu_radion1);
		sx_paizhao_edu_radion2 = (RadioButton) findViewById(R.id.sx_paizhao_edu_radion2);
		// 原厂钥匙
		sx_yaoshi_radion1 = (RadioButton) findViewById(R.id.sx_yaoshi_radion1);
		sx_yaoshi_radion2 = (RadioButton) findViewById(R.id.sx_yaoshi_radion2);
		// 随车工具
		sx_gongju_radion1 = (RadioButton) findViewById(R.id.sx_gongju_radion1);
		sx_gongju_radion2 = (RadioButton) findViewById(R.id.sx_gongju_radion2);
		sx_gongju_lost = (EditText) findViewById(R.id.sx_gongju_lost);
		// 车辆使用说明书
		sx_shoumingshu_radion1 = (RadioButton) findViewById(R.id.sx_shoumingshu_radion1);
		sx_shoumingshu_radion2 = (RadioButton) findViewById(R.id.sx_shoumingshu_radion2);
		// 车辆保养手册
		sx_baoyang_radion1 = (RadioButton) findViewById(R.id.sx_baoyang_radion1);
		sx_baoyang_radion2 = (RadioButton) findViewById(R.id.sx_baoyang_radion2);
		
		
		buqueding13 = (Button) findViewById(R.id.buqueding13);
		buqueding14 = (Button) findViewById(R.id.buqueding14);
		buqueding15 = (Button) findViewById(R.id.buqueding15);
		buqueding16 = (Button) findViewById(R.id.buqueding16);
		buqueding17 = (Button) findViewById(R.id.buqueding17);
		buqueding18 = (Button) findViewById(R.id.buqueding18);
		buqueding19 = (Button) findViewById(R.id.buqueding19);
		buqueding20 = (Button) findViewById(R.id.buqueding20);
		
		
		
		
		if(sx_xingzhi_spineer.getSelectedItem().toString().equals("非营运") && sx_edit_car_type1.isChecked())
		{
			sx_btn_qiangzhibaofei.setEnabled(false);
			sx_edit_car_baofei_date.setEnabled(false);
		}
		else
		{
			sx_btn_qiangzhibaofei.setEnabled(true);
			sx_edit_car_baofei_date.setEnabled(true);
		}
		
		sx_xingzhi_spineer.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if(sx_xingzhi_spineer.getSelectedItem().toString().equals("非营运") && sx_edit_car_type1.isChecked())
				{
					sx_btn_qiangzhibaofei.setEnabled(false);
					sx_edit_car_baofei_date.setEnabled(false);
				}
				else
				{
					sx_btn_qiangzhibaofei.setEnabled(true);
					sx_edit_car_baofei_date.setEnabled(true);
				}			
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {				
			}
			
	    });
		
		
		//小型车监听
		sx_edit_car_type1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if(isChecked && sx_xingzhi_spineer.getSelectedItem().toString().equals("非营运"))
				{
					sx_btn_qiangzhibaofei.setEnabled(false);
					sx_edit_car_baofei_date.setEnabled(false);
				}
				else
				{
					sx_btn_qiangzhibaofei.setEnabled(true);
					sx_edit_car_baofei_date.setEnabled(true);
				}
			}			
		});
		sx_edit_car_type2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {	
				if(isChecked)		
				{
					sx_btn_qiangzhibaofei.setEnabled(true);
					sx_edit_car_baofei_date.setEnabled(true);
				}					
			}			
		});		
		
		
		
		sx_paifang_button_huoqu.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				String xinghao = sx_edit_car_pinpai_xinghao.getText().toString();
				if(!xinghao.trim().trim().equals(""))
				{
					GetEmission(xinghao);
				}
				else
				{
					final MineAlert alert = new MineAlert(context);
					alert.createAlertOneButton(false,"请先输入品牌型号信息", 
							new View.OnClickListener() {								
								@Override
								public void onClick(View v) {
									alert.dimiss();
								}
							});
				}				
			}
		});
		
		

		car_shouxu_dengji_btn_back
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						finish();
					}
				});

		// 显示拍照的图片
		paizhao_image = (ImageView) this
				.findViewById(R.id.shouxu_dengji_paizhao_image);
		wukexuanxiang.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(context, CarReplyActivity.class);
				startActivity(intent);

			}
		});
		
		
		shouxu_dengji1_btn_shibie.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(LicensePhoto.trim().equals(""))
				{
					final MineAlert alert = new MineAlert(context);
					alert.createAlertOneButton(false,"请先进行拍照后操作", 
							new View.OnClickListener() {								
								@Override
								public void onClick(View v) {
									alert.dimiss();
								}
							});
				}
				else
				{
					getLicenseRecognition(LicensePhoto);
				}
				
			}
		});
		

		shouxu_dengji_btn_getguishudi.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				String guishudi = sx_edit_car_number.getText().toString().trim();
				if(guishudi.equals(""))
				{
					final MineAlert alert = new MineAlert(context);
					alert.createAlertOneButton(false,"请输入车牌号码", 
							new View.OnClickListener() {								
								@Override
								public void onClick(View v) {
									alert.dimiss();
								}
							});
					return;
				}
				if(!isChepai())
				{
					final MineAlert alert = new MineAlert(context);
					alert.createAlertOneButton(false,"请输入正确的车牌号码", 
							new View.OnClickListener() {								
								@Override
								public void onClick(View v) {
									alert.dimiss();
								}
							});
					return;
				}
				GetLicenseAttribution(guishudi);
			}
		});
		
		
		sx_btn_qiangzhibaofei.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				if(!isInputChuChangRiQi())
				{
					final MineAlert alert = new MineAlert(context);
					alert.createAlertOneButton(false,"请输入出厂日期", 
							new View.OnClickListener() {								
								@Override
								public void onClick(View v) {
									alert.dimiss();
								}
							});
					return;
				}
				isCarleixing();
				isInputShiYongXingZhi();
				
				GetDiscardedLimit(sx_edit_car_chuchang_date.getText().toString(), sx_edit_car_zhuce_date.getText().toString(), carleixing, Nature);
				
				
			}
		});
		
		
		
		paizhao.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//LicensePhoto = "";
				String fileName = new SimpleDateFormat("yyyyMMddHHmmss")
						.format(new Date()) + ".jpg";// 照片按时间格式yyyyMMddHHmmss命名
				// 该照片的绝对路径
				singleCameraImgPath1 = AppConfig.CacheDir + "/" + fileName;
				
				File vFile = new File(singleCameraImgPath1);
				if (!vFile.exists()) {
					File vDirPath = vFile.getParentFile();
					vDirPath.mkdirs();
				}
//				Uri uri = Uri.fromFile(vFile);
//				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//				intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//				((Activity) context).startActivityForResult(intent,
//						RESULT_CAPTURE_IMAGE);
				
				Intent intent = new Intent();
				intent.setClass(context , TakePhotoActivity.class);		
				intent.putExtra("title", "行驶证");
				intent.putExtra("fileDir", AppConfig.CacheDir);
				intent.putExtra("fileName", fileName);
				ShouXuDengJiAcitivity.this.startActivityForResult(intent, 0);
				
				
				
			}
		});
		
		
		
		buqueding13.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				sx_banzheng_radion1.setChecked(false);
				sx_banzheng_radion2.setChecked(false);
				sx_banzheng_radion3.setChecked(false);
				sx_banzheng_radion4.setChecked(false);
			}
		});
		buqueding14.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				sx_biangeng_radion1.setChecked(false);
				sx_biangeng_radion2.setChecked(false);
				sx_biangeng_checkbox1.setChecked(false);
				sx_biangeng_checkbox2.setChecked(false);
				sx_biangeng_checkbox3.setChecked(false);
				sx_biangeng_cishu.setText("");
			}
		});
		buqueding15.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				sx_gouzhi_radion1.setChecked(false);
				sx_gouzhi_radion2.setChecked(false);
				sx_gouzhi_radion3.setChecked(false);
			}
		});
		buqueding16.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				sx_edit_jiaoqiangxian_date.setText("");
			}
		});
		buqueding17.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				sx_edit_nianjian_date.setText("");
			}
		});
		buqueding18.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				sx_edit_tongxingfei_date.setText("");
			}
		});
		buqueding19.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				sx_edit_fen.setText("");
				sx_edit_yuan.setText("");
			}
		});
		buqueding20.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				sx_paizhao_edu_radion1.setChecked(false);
				sx_paizhao_edu_radion2.setChecked(false);
			}
		});
		
		
		initUIDataFromDB();
	}

	OnClickListener timeEditClick = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.sx_edit_car_chuchang_date:
				DateTimePickDialogUtil dateTimePicKDialog1 = new DateTimePickDialogUtil(
						ShouXuDengJiAcitivity.this, sx_edit_car_chuchang_date,2);
				break;
			case R.id.sx_edit_car_zhuce_date:
				DateTimePickDialogUtil dateTimePicKDialog2 = new DateTimePickDialogUtil(
						ShouXuDengJiAcitivity.this, sx_edit_car_zhuce_date,2);
				break;
			case R.id.sx_edit_car_baofei_date:
				DateTimePickDialogUtil dateTimePicKDialog3 = new DateTimePickDialogUtil(
						ShouXuDengJiAcitivity.this, sx_edit_car_baofei_date,2);
				break;
			case R.id.sx_edit_jiaoqiangxian_date:
				DateTimePickDialogUtil dateTimePicKDialog4 = new DateTimePickDialogUtil(
						ShouXuDengJiAcitivity.this, sx_edit_jiaoqiangxian_date,1);
				break;
			case R.id.sx_edit_nianjian_date:
				DateTimePickDialogUtil dateTimePicKDialog5 = new DateTimePickDialogUtil(
						ShouXuDengJiAcitivity.this, sx_edit_nianjian_date,1);
				break;
			case R.id.sx_edit_tongxingfei_date:
				DateTimePickDialogUtil dateTimePicKDialog6 = new DateTimePickDialogUtil(
						ShouXuDengJiAcitivity.this, sx_edit_tongxingfei_date,1);
				break;
			}
		}
	};

	private void initTitle() {
		((TextView) findViewById(R.id.title_renwu_btn1))
				.setBackgroundDrawable(context.getResources().getDrawable(
						R.drawable.bg));
		((TextView) findViewById(R.id.title_renwu_btn2))
				.setBackgroundDrawable(context.getResources().getDrawable(
						R.drawable.title_bg));
		((TextView) findViewById(R.id.title_renwu_btn3))
				.setBackgroundDrawable(context.getResources().getDrawable(
						R.drawable.title_bg));
	}

	private void initBottom() {
		((Button) findViewById(R.id.stop_save))
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						boolean b = new CheckInput()
								.inputModel(CheckInput.DoSaveModel);
						if (b) {
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
					}
				});
		((Button) findViewById(R.id.comfirm_communit))
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						boolean b = new CheckInput()
								.inputModel(CheckInput.DoSubmitModel);
						if (b) {
							sendEnrolment();
						}
					}
				});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			if (resultCode == Activity.RESULT_OK) {				
				String path = data.getStringExtra("path");
				if(bm!=null)
				{
					if(!bm.isRecycled())
					{
						bm.recycle();
						bm = null;
					}
				}
				bm = PictureUtil.getSmallBitmap(path,1);
				paizhao_image.setImageBitmap(bm);
				LicensePhoto = PictureUtil.bitmapToString(path,false);
			}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private String License = "";

	private String BelongingTo = "";

	private String IdentificationCode = "";

	private String Nature = "非运营";

	private String carleixing = "小型车";

	private String BrandModel = "";

	private String EngineSerialNum = "";

	private String ManufactureDate = "";

	private String FirstEnrolmentDate = "";

	private String ScrapDate = "";

	private String OwnerType = "0";

	private String EmissionStandards = "0";

	private String RegistrationStatus = "-1";
	
	private String ChangedNum = "-1";

	private String ChangeType = "";

	private String PurchaseTax = "-1";

	private String InsuranceValidity = "";

	private String AnnualInspectionValidity = "";

	private String TollsValidity = "";

	private String IllegalScore = "-1";
	
	private String IllegalMoney = "-1";
	
	private String LicenceLines = "-1";
	

	private String OriginalKeys = "0";

	private String IsMissingTool = "0";

	private String MissingTools = "";

	private String HasInstruction = "0";

	private String HasMaintenanceManua = "0";

	private String LicensePhoto = "";

	/**
	 * 验证用户输入信息
	 * */
	class CheckInput {

		// 保存模式，根据操作模式来更改判断方式，保存模式只需要输入至少一条数据便可；
		public static final int DoSaveModel = 0;

		// 提交模式，根据操作模式来更改判断方式，提交模式需要将每一项都输入方可提交；
		public static final int DoSubmitModel = 1;

		public CheckInput() {

		}

		public boolean inputModel(int model) {

			if (model == DoSaveModel) {
				return checkSaveInput();
			} else if (model == DoSubmitModel) {
				return checkSubmitInput();
			}
			return false;
		}

		/**
		 * 检查保存模式下的输入
		 * */
		private boolean checkSaveInput() {
			if (isInputChepai() || isInputChepaiGuiShuDi()
					|| isInputShiBieDaiHao() || isInputPinPaiXingHao()
					|| isInputFaDongJiHao() || isInputChuChangRiQi()
					|| isInputShouCiDengJiShiJian()
					|| isInputQiangZhiBaoFeiNianXian()
					|| isInputJiaoQiangXianYouXiaoQi()
					|| isInputNianJianYouXiaoQi() || isInputWeiZhang()
					|| isPaiZhao()) {
				return true;
			} else {
				ToastUtilMgr.TextToast(context, getString(R.string.error_save),
						Toast.LENGTH_SHORT);
				return false;
			}
		}

		/**
		 * 检测提交模式下的输入
		 * */
		private boolean checkSubmitInput() {

			/**
			 * 检查车牌号码是否输入 *
			 */
			if (isInputChepai()) {
				/**
				 * 检验车牌是否输入正确
				 * */
				if (isChepai()) {
					/**
					 * 检查车牌归属地是否输入
					 * */
					if (isInputChepaiGuiShuDi()) {
						/**
						 * 检查识别代号是发输入
						 * */
						if (isInputShiBieDaiHao()) {
							/**
							 * 检查使用性质是否输入
							 * */
							if (isInputShiYongXingZhi()) {
								/**
								 * 检查车型是否输入
								 * */
								if (isCarleixing()) {
									/**
									 * 检查品牌型号是否输入
									 * */
									if (isInputPinPaiXingHao()) {
										/**
										 * 检查发动机号码是否输入
										 * */
										if (isInputFaDongJiHao()) {
											/**
											 * 检查出厂日期是否输入
											 * */
											if (isInputChuChangRiQi()) {
												/**
												 * 检查首次注册登记时间是否输入
												 * */
												if (isInputShouCiDengJiShiJian()) {
													/**
													 * 检查强制报废年限是否输入
													 * */
													if (isInputQiangZhiBaoFeiNianXian()) {
														/**
														 * 检查所有人是否输入
														 * */
														if (isInputSuoYouRen()) {
															/**
															 * 检查排放标准是否输入
															 * */
															if (isInputPaiFangBiaoZhun()) {
																/**
																 * 检查办证状态是否输入
																 * */
																if (isInputBanZhengZhuangTai()) {
																	/**
																	 * 检查变更记录是否输入
																	 * */
																	if (isInputBianGengJiLu()) {
																		/**
																		 * 检查购置税是否输入
																		 * */
																		if (isInputGouZhiShui()) {
																			/**
																			 * 检查交强险有效期是否输入
																			 * */
																			if (isInputJiaoQiangXianYouXiaoQi()) {
																				/**
																				 * 检查年检有效期是否输入
																				 * */
																				if (isInputNianJianYouXiaoQi()) {
																					/**
																					 * 检查通行费有效期是否输入
																					 * */
																					if (isInputTongXingFeiYouXiaoQi()) {
																						/**
																						 * 检查违章未处理记录是否输入
																						 * */
																						if (isInputWeiZhang()) {
																							/**
																							 * 检查牌照额度是否输入
																							 * */
																							if (isInputPaiZhaoEDU()) {
																								/**
																								 * 检查原厂钥匙是否输入
																								 * */
																								if (isInputYuanChangYaoShi()) {
																									/**
																									 * 检查随车工具是否输入
																									 * */
																									if (isInputSuiCheGongJu()) {
																										/**
																										 * 检查车辆使用说明书是否输入
																										 * */
																										if (isInputShuoMingShu()) {
																											/**
																											 * 检查车辆保养手册是否输入
																											 * */
																											if (isInputBaoYangShouCe()) {
																												// 验证是否拍照
																												if (isPaiZhao()) {
																													return true;
																												} else {
																													ToastUtilMgr
																															.TextToast(
																																	context,
																																	getString(R.string.without_txt25),
																																	Toast.LENGTH_SHORT);
																												}
																											} else {
																												ToastUtilMgr
																														.TextToast(
																																context,
																																getString(R.string.without_txt24),
																																Toast.LENGTH_SHORT);
																											}
																										} else {
																											ToastUtilMgr
																													.TextToast(
																															context,
																															getString(R.string.without_txt23),
																															Toast.LENGTH_SHORT);
																										}
																									} else {
																										ToastUtilMgr
																												.TextToast(
																														context,
																														getString(R.string.without_txt22),
																														Toast.LENGTH_SHORT);
																									}

																								} else {
																									ToastUtilMgr
																											.TextToast(
																													context,
																													getString(R.string.without_txt21),
																													Toast.LENGTH_SHORT);
																								}
																							} else {
																								ToastUtilMgr
																										.TextToast(
																												context,
																												getString(R.string.without_txt20),
																												Toast.LENGTH_SHORT);
																							}
																						} else {
																							ToastUtilMgr
																									.TextToast(
																											context,
																											getString(R.string.without_txt19),
																											Toast.LENGTH_SHORT);
																						}
																					} else {
																						ToastUtilMgr
																								.TextToast(
																										context,
																										getString(R.string.without_txt18),
																										Toast.LENGTH_SHORT);
																					}
																				} else {
																					ToastUtilMgr
																							.TextToast(
																									context,
																									getString(R.string.without_txt17),
																									Toast.LENGTH_SHORT);
																				}
																			} else {
																				ToastUtilMgr
																						.TextToast(
																								context,
																								getString(R.string.without_txt16),
																								Toast.LENGTH_SHORT);
																			}
																		} else {
																			ToastUtilMgr
																					.TextToast(
																							context,
																							getString(R.string.without_txt15),
																							Toast.LENGTH_SHORT);
																		}
																	} else {
																		ToastUtilMgr
																				.TextToast(
																						context,
																						getString(R.string.without_txt14),
																						Toast.LENGTH_SHORT);
																	}
																} else {
																	ToastUtilMgr
																			.TextToast(
																					context,
																					getString(R.string.without_txt13),
																					Toast.LENGTH_SHORT);
																}
															} else {
																ToastUtilMgr
																		.TextToast(
																				context,
																				getString(R.string.without_txt12),
																				Toast.LENGTH_SHORT);
															}
														} else {
															ToastUtilMgr
																	.TextToast(
																			context,
																			getString(R.string.without_txt11),
																			Toast.LENGTH_SHORT);
														}
													} else {
														ToastUtilMgr
																.TextToast(
																		context,
																		getString(R.string.without_txt10),
																		Toast.LENGTH_SHORT);
													}
												} else {
													ToastUtilMgr
															.TextToast(
																	context,
																	getString(R.string.without_txt9),
																	Toast.LENGTH_SHORT);
												}

											} else {
												ToastUtilMgr
														.TextToast(
																context,
																getString(R.string.without_txt8),
																Toast.LENGTH_SHORT);
											}
										} else {
											ToastUtilMgr
													.TextToast(
															context,
															getString(R.string.without_txt7),
															Toast.LENGTH_SHORT);
										}
									} else {
										ToastUtilMgr
												.TextToast(
														context,
														getString(R.string.without_txt6),
														Toast.LENGTH_SHORT);
									}
								} else {
									ToastUtilMgr.TextToast(context,
											getString(R.string.without_txt5),
											Toast.LENGTH_SHORT);
								}
							} else {
								ToastUtilMgr.TextToast(context,
										getString(R.string.without_txt4),
										Toast.LENGTH_SHORT);
							}
						} else {
							ToastUtilMgr.TextToast(context,
									getString(R.string.without_txt3),
									Toast.LENGTH_SHORT);
						}
					} else {
						ToastUtilMgr.TextToast(context,
								getString(R.string.without_txt2),
								Toast.LENGTH_SHORT);
					}
				} else {
					ToastUtilMgr.TextToast(context,
							getString(R.string.errorChepai),
							ToastUtilMgr.LENGTH_LONG);
				}
			} else {
				ToastUtilMgr.TextToast(context,
						getString(R.string.without_txt1), Toast.LENGTH_SHORT);
			}

			return false;
		}
		
		
	
	}

	/**
	 * 检查车牌号码是否输入
	 */
	private boolean isInputChepai() {
		String license = sx_edit_car_number.getText().toString().trim();
		if (!license.equals("")) {
			License = license;
			return true;
		}
		return false;
	}

	/**
	 * 车牌格式是否正确
	 * 
	 * @return
	 */
	private boolean isChepai() {
		String chepai = sx_edit_car_number.getText().toString().trim();
		Log.e("车牌", chepai.toUpperCase());
		// 车牌号格式验证
		String vehicleNoStyle = "^[\u4e00-\u9fa5]{1}[A-Z0-9]{6}$";
		Pattern pattern = Pattern.compile(vehicleNoStyle);
		Matcher matcher = pattern.matcher(chepai.toUpperCase());
		if (matcher.matches()) {
			return true;
		}
		else
		{
			return false;
		}
		
	}

	/**
	 * 检查车牌归属地是否输入
	 * */
	private boolean isInputChepaiGuiShuDi() {

		String belongingTo = sx_edit_car_guishudi.getText().toString().trim();
		if (!belongingTo.equals("")) {
			BelongingTo = belongingTo;
			return true;
		}
		return false;
	}

	/**
	 * 检查识别代号是发输入
	 * */
	private boolean isInputShiBieDaiHao() {

		String shibie = sx_edit_car_shibie_numer.getText().toString().trim();
		if (!shibie.equals("")) {
			IdentificationCode = shibie;
			return true;
		}
		return false;
	}

	/**
	 * 检查使用性质是否输入
	 * */
	private boolean isInputShiYongXingZhi() {

//		if (sx_xingzhi_radion1.isChecked() || sx_xingzhi_radion2.isChecked()
//				|| sx_xingzhi_radion3.isChecked()) {
//			if (sx_xingzhi_radion1.isChecked()) {
//				Nature = "非营运";
//			} else if (sx_xingzhi_radion2.isChecked()) {
//				Nature = "营转非";
//			} else if (sx_xingzhi_radion3.isChecked()) {
//				Nature = "营运";
//			}
//			return true;
//		}
//		return false;
		
		
		Nature = this.sx_xingzhi_spineer.getSelectedView().getTag().toString();
		
		
		return true;
	}

	private boolean isCarleixing() {
			if (sx_edit_car_type1.isChecked()) {
				carleixing = "小型车";
			} 
			if (sx_edit_car_type2.isChecked()) {
				carleixing = "大型车";
			} 
			return true;
	}

	/**
	 * 检查品牌型号是否输入
	 * */
	private boolean isInputPinPaiXingHao() {

		String pinpai = sx_edit_car_pinpai_xinghao.getText().toString().trim();
		if (!pinpai.equals("")) {
			BrandModel = pinpai;
			return true;
		}
		return false;
	}

	/**
	 * 检查发动机号码是否输入
	 * */
	private boolean isInputFaDongJiHao() {

		String faDongJiHao = sx_edit_car_fadongji_number.getText().toString()
				.trim();
		if (!faDongJiHao.equals("")) {
			EngineSerialNum = faDongJiHao;
			return true;
		}
		return false;
	}

	/**
	 * 检查出厂日期是否输入
	 * */
	private boolean isInputChuChangRiQi() {

		String chuchang_date = sx_edit_car_chuchang_date.getText().toString()
				.trim();
		if (!chuchang_date.equals("")) {
			ManufactureDate = chuchang_date;
			return true;
		}
		return false;
	}

	/**
	 * 检查首次注册登记时间是否输入
	 * */
	private boolean isInputShouCiDengJiShiJian() {

		String shouCiDengJi = sx_edit_car_zhuce_date.getText().toString()
				.trim();
		if (!shouCiDengJi.equals("")) {
			FirstEnrolmentDate = shouCiDengJi;
			return true;
		}
		return false;
	}

	/**
	 * 检查强制报废年限是否输入
	 * */
	private boolean isInputQiangZhiBaoFeiNianXian() {		
		
		String qiangZhiBaoFeiNianXian = sx_edit_car_baofei_date.getText().toString().trim();
		
		if(sx_xingzhi_spineer.getSelectedItem().toString().equals("非营运") && sx_edit_car_type1.isChecked())
		{
			
//			if (qiangZhiBaoFeiNianXian.equals("")) {
//				ScrapDate = qiangZhiBaoFeiNianXian;
//				return true;
//			}
//			return false;
			return true;
		}
		else
		{
			if (qiangZhiBaoFeiNianXian.equals("")) {
				ScrapDate = qiangZhiBaoFeiNianXian;
				return false;
			}
			else
			{
				ScrapDate = qiangZhiBaoFeiNianXian;
				return true;
			}			
		}
		
		
	}

	/**
	 * 检查所有人是否输入
	 * */
	private boolean isInputSuoYouRen() {

		if (sx_suoyouren_radion1.isChecked()
				|| sx_suoyouren_radion2.isChecked()
				|| sx_suoyouren_radion3.isChecked()) {
			if (sx_suoyouren_radion1.isChecked()) {
				OwnerType = "0";
			} else if (sx_suoyouren_radion2.isChecked()) {
				OwnerType = "1";
			} else if (sx_suoyouren_radion3.isChecked()) {
				OwnerType = "2";
			}
			return true;
		}
		return false;
	}

	/**
	 * 检查排放标准是否输入
	 * */
	private boolean isInputPaiFangBiaoZhun() {

		if (sx_paifang_radion1.isChecked() || sx_paifang_radion2.isChecked()
				|| sx_paifang_radion3.isChecked()
				|| sx_paifang_radion4.isChecked()
				|| sx_paifang_radion5.isChecked()) {
			if (sx_paifang_radion1.isChecked()) {
				EmissionStandards = "0";
			} else if (sx_paifang_radion2.isChecked()) {
				EmissionStandards = "1";
			} else if (sx_paifang_radion3.isChecked()) {
				EmissionStandards = "2";
			} else if (sx_paifang_radion4.isChecked()) {
				EmissionStandards = "3";
			} else if (sx_paifang_radion5.isChecked()) {
				EmissionStandards = "4";
			}
			return true;
		}
		return false;
	}

	/**
	 * 检查办证状态是否输入
	 * */
	private boolean isInputBanZhengZhuangTai() {

		if (sx_banzheng_radion1.isChecked() || sx_banzheng_radion2.isChecked()
				|| sx_banzheng_radion3.isChecked()
				|| sx_banzheng_radion4.isChecked()) {
			if (sx_banzheng_radion1.isChecked()) {
				RegistrationStatus = "0";
			} else if (sx_banzheng_radion2.isChecked()) {
				RegistrationStatus = "1";
			} else if (sx_banzheng_radion3.isChecked()) {
				RegistrationStatus = "2";
			} else if (sx_banzheng_radion4.isChecked()) {
				RegistrationStatus = "3";
			}
		}
		return true;
	}

	/**
	 * 检查变更记录是否输入
	 * */
	private boolean isInputBianGengJiLu() {
//		if (!sx_biangeng_radion1.isChecked()
//				&& !sx_biangeng_radion2.isChecked()) {
//			return false;
//		}
		if (sx_biangeng_radion1.isChecked()) {
			ChangedNum = "0";
			ChangeType = "";
			// 变更类型
			if (sx_biangeng_checkbox1.isChecked()) {
				ChangeType = ChangeType + "," + "0";
			}
			if (sx_biangeng_checkbox2.isChecked()) {
				ChangeType = ChangeType + "," + "1";
			}
			if (sx_biangeng_checkbox3.isChecked()) {
				ChangeType = ChangeType + "," + "2";
			}
			if (ChangeType.indexOf(",") != -1) {
				ChangeType = ChangeType.substring(
						ChangeType.indexOf(",") + 1, ChangeType.length());
			}
		}
		if (sx_biangeng_radion2.isChecked()) {
			String cishu = sx_biangeng_cishu.getText().toString().trim();
			if (!cishu.equals("")) {
				ChangedNum = cishu;
				ChangeType = "";
				// 变更类型
				if (sx_biangeng_checkbox1.isChecked()) {
					ChangeType = ChangeType + "," + "0";
				}
				if (sx_biangeng_checkbox2.isChecked()) {
					ChangeType = ChangeType + "," + "1";
				}
				if (sx_biangeng_checkbox3.isChecked()) {
					ChangeType = ChangeType + "," + "2";
				}
				if (ChangeType.indexOf(",") != -1) {
					ChangeType = ChangeType.substring(
							ChangeType.indexOf(",") + 1, ChangeType.length());
				}
			}
		}
		return true;
	}

	/**
	 * 检查购置税是否输入
	 * */
	private boolean isInputGouZhiShui() {
		if (sx_gouzhi_radion1.isChecked() || sx_gouzhi_radion2.isChecked()
				|| sx_gouzhi_radion3.isChecked()) {
			if (sx_gouzhi_radion1.isChecked()) {
				PurchaseTax = "0";
			} else if (sx_gouzhi_radion2.isChecked()) {
				PurchaseTax = "1";
			} else if (sx_gouzhi_radion3.isChecked()) {
				PurchaseTax = "2";
			}
		}
		return true;
	}

	/**
	 * 检查交强险有效期是否输入
	 * */
	private boolean isInputJiaoQiangXianYouXiaoQi() {
		String jiaoQiangXian = sx_edit_jiaoqiangxian_date.getText().toString()
				.trim();
		if (!jiaoQiangXian.equals("")) {
			InsuranceValidity = jiaoQiangXian;
		}
		return true;
	}

	/**
	 * 检查年检有效期是否输入
	 * */
	private boolean isInputNianJianYouXiaoQi() {
		String youXiaoQi = sx_edit_nianjian_date.getText().toString().trim();
		if (!youXiaoQi.equals("")) {
			AnnualInspectionValidity = youXiaoQi;
		}
		return true;
	}

	/**
	 * 检查通行费有效期是否输入
	 * */
	private boolean isInputTongXingFeiYouXiaoQi() {
		String youXiaoQi = sx_edit_tongxingfei_date.getText().toString().trim();
		if (!youXiaoQi.equals("")) {
			TollsValidity = youXiaoQi;
		}
		return true;
	}

	/**
	 * 检查违章未处理记录是否输入
	 * */
	private boolean isInputWeiZhang() {
		String koufen = sx_edit_fen.getText().toString().trim();
		String faKuan = sx_edit_yuan.getText().toString().trim();
		if (!koufen.equals("") && !faKuan.equals("")) {
			IllegalScore = koufen;
			IllegalMoney = faKuan;
		}
		return true;
	}

	/**
	 * 检查牌照额度是否输入
	 * */
	private boolean isInputPaiZhaoEDU() {

		if (sx_paizhao_edu_radion1.isChecked()
				|| sx_paizhao_edu_radion2.isChecked()) {
			if (sx_paizhao_edu_radion1.isChecked()) {
				LicenceLines = "0";
			} else if (sx_paizhao_edu_radion2.isChecked()) {
				LicenceLines = "1";
			}
		}
		return true;
	}

	/**
	 * 检查原厂钥匙是否输入
	 * */
	private boolean isInputYuanChangYaoShi() {
		if (sx_yaoshi_radion1.isChecked()) {
			String yaoshi_ba = sx_yaoshi_ba.getText().toString().trim();
			if (!yaoshi_ba.equals("")) {
				OriginalKeys = yaoshi_ba;
				return true;
			} else {
				return false;
				// ToastUtilMgr.TextToast(context,
				// getString(R.string.without_yaoshishuliang),
				// Toast.LENGTH_SHORT) ;
			}
		}
		if (sx_yaoshi_radion2.isChecked()) {
			OriginalKeys = "0";
			return true;
		}
		return false;
	}

	/**
	 * 检查随车工具是否输入
	 * */
	private boolean isInputSuiCheGongJu() {
		if (sx_gongju_radion1.isChecked()) {
			IsMissingTool = "0";
			MissingTools = sx_gongju_lost.getText().toString().trim();
			return true;
		} else if (sx_gongju_radion2.isChecked()) {
			IsMissingTool = "1";
			String lost = sx_gongju_lost.getText().toString().trim();
			if (!lost.equals("")) {
				MissingTools = lost;
				return true;
			} else {
				return false;
				// ToastUtilMgr.TextToast(context,
				// getString(R.string.without_suichegongju), Toast.LENGTH_SHORT)
				// ;
			}
		}
		return false;
	}

	/**
	 * 检查车辆使用说明书是否输入
	 * */
	private boolean isInputShuoMingShu() {

		if (sx_shoumingshu_radion1.isChecked()
				|| sx_shoumingshu_radion2.isChecked()) {
			if (sx_shoumingshu_radion1.isChecked()) {
				HasInstruction = "1";
			} else if (sx_shoumingshu_radion2.isChecked()) {
				HasInstruction = "0";
			}
			return true;
		}
		return false;
	}

	/**
	 * 检查车辆保养手册是否输入
	 * */
	private boolean isInputBaoYangShouCe() {

		if (sx_baoyang_radion1.isChecked() || sx_baoyang_radion2.isChecked()) {
			if (sx_baoyang_radion1.isChecked()) {
				HasMaintenanceManua = "1";
			} else if (sx_baoyang_radion2.isChecked()) {
				HasMaintenanceManua = "0";
			}
			return true;
		}
		return false;
	}

	/**
	 * 检查是否拍摄行驶证
	 * */
	private boolean isPaiZhao() {
		if (!LicensePhoto.trim().equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 发送手续登记信息
	 * */
	private SoapMgr mgr;
	private SoapObject backSoapObject;

	private void sendEnrolment() {

		saveData();
		
		boolean b= checkDate();
		if(!b)
		{
			return;	
		}
		MyHandler myhandler = new MyHandler() {

			@Override
			public void success(Message msg) {

				backSoapObject = mgr.getServiceBackSoapObject();
				if (backSoapObject != null) {
					String Retcode = ((SoapObject) backSoapObject
							.getProperty(0)).getProperty("Retcode").toString();
					
					//是否存在历史ID
					HistoryResourceID = ((SoapObject) backSoapObject
							.getProperty(0)).getProperty("HistoryResourceID").toString();
					// 0成功，1失败
					if (Retcode.equals("0")) {
						ToastUtilMgr.TextToast(context, "数据提交成功",
								ToastUtilMgr.LENGTH_SHORT);
						
						if(HistoryResourceID.equals("anyType{}"))
						{
							HistoryResourceID = "";
						}
						if(HistoryResourceID.trim().equals(""))
						{
							HistoryResourceID = "0";
						}
						
						
						if(edit)
						{
							finish();
						}
						else
						{
							
							Handler handler = new Handler()
							{

								@Override
								public void handleMessage(Message msg) {
									super.handleMessage(msg);
									
									Intent intent = new Intent();
									intent.setClass(context, CarDengji.class);
									intent.putExtra("VIN", sx_edit_car_shibie_numer.getText().toString().trim());									
									new AppPreference(context).setHistoryResourceID(HistoryResourceID);
									startActivity(intent);
								}
								
							};
							
							
							
							
//							if(!HistoryResourceID.equals("0"))
//							{
//								//获取数据
//								new ShouXuDengJiHistory(context,HistoryResourceID,handler);
//							}
//							else
//							{
								Intent intent = new Intent();
								intent.setClass(context, CarDengji.class);
								intent.putExtra("VIN", sx_edit_car_shibie_numer.getText().toString().trim());								
								new AppPreference(context).setHistoryResourceID(HistoryResourceID);
								startActivity(intent);
//							}
							
							
							
							
						}
						
					} else {
						ToastUtilMgr.TextToast(context,
								getString(R.string.server_error1),
								ToastUtilMgr.LENGTH_LONG);
					}
				}
			}

			@Override
			public void failed(Message msg) {
				
			}
		};

		// 再重新初始化一次各个参数
		License = sx_edit_car_number.getText().toString().trim();
		isInputChepaiGuiShuDi();
		isInputShiBieDaiHao();
		isCarleixing();
		isInputShiYongXingZhi();
		isInputPinPaiXingHao();
		isInputFaDongJiHao();
		isInputChuChangRiQi();
		isInputShouCiDengJiShiJian();
		isInputQiangZhiBaoFeiNianXian();
		isInputSuoYouRen();
		isInputPaiFangBiaoZhun();
		isInputBanZhengZhuangTai();
		isInputBianGengJiLu();
		isInputGouZhiShui();
		isInputJiaoQiangXianYouXiaoQi();
		isInputNianJianYouXiaoQi();
		isInputTongXingFeiYouXiaoQi();
		isInputWeiZhang();
		isInputPaiZhaoEDU();
		isInputYuanChangYaoShi();
		isInputSuiCheGongJu();
		isInputShuoMingShu();
		isInputBaoYangShouCe();
		isPaiZhao();

		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace,
				"SaveEnrolment");
		soapParameter.addProperty("ResourceID",
				new AppPreference(context).getTaskId());
		soapParameter.addProperty("License", License);
		soapParameter.addProperty("BelongingTo", BelongingTo);
		soapParameter.addProperty("IdentificationCode", IdentificationCode);
		soapParameter.addProperty("Nature", Nature);
		soapParameter.addProperty("Category", carleixing);
		soapParameter.addProperty("BrandModel", BrandModel);
		soapParameter.addProperty("EngineSerialNum", EngineSerialNum);
		soapParameter.addProperty("ManufactureDate", ManufactureDate);
		soapParameter.addProperty("FirstEnrolmentDate", FirstEnrolmentDate);
		soapParameter.addProperty("ScrapDate", ScrapDate);
		soapParameter.addProperty("OwnerType", OwnerType);
		soapParameter.addProperty("EmissionStandards", EmissionStandards);
		soapParameter.addProperty("RegistrationStatus", RegistrationStatus);
		soapParameter.addProperty("ChangedNum", ChangedNum);
		soapParameter.addProperty("ChangeType", ChangeType);
		soapParameter.addProperty("PurchaseTax", PurchaseTax);
		soapParameter.addProperty("InsuranceValidity", InsuranceValidity);
		soapParameter.addProperty("AnnualInspectionValidity",
				AnnualInspectionValidity);
		soapParameter.addProperty("TollsValidity", TollsValidity);
		soapParameter.addProperty("IllegalScore", IllegalScore);
		soapParameter.addProperty("IllegalMoney", IllegalMoney);
		soapParameter.addProperty("LicenceLines", LicenceLines);
		soapParameter.addProperty("OriginalKeys", OriginalKeys);
		soapParameter.addProperty("IsMissingTool", IsMissingTool);
		soapParameter.addProperty("MissingTools", MissingTools);
		soapParameter.addProperty("HasInstruction", HasInstruction);
		soapParameter.addProperty("HasMaintenanceManual", HasMaintenanceManua);
		soapParameter.addProperty("LicensePhoto", LicensePhoto);
		
		
		if(edit)
		{
			soapParameter.addProperty("IsFirstSave", "0");			
		}
		else
		{
			soapParameter.addProperty("IsFirstSave", "1");
		}
		
		

		mgr = new SoapMgr(context, null, null, SoapAction.SaveEnrolment,
				"SaveEnrolment", soapParameter, myhandler, true, false);
	}

	//检测车辆识别码是否符合
	private boolean checkCheLiangShiBieMa(String shibiema) {
		if (shibiema != null) {
			if (shibiema.length() == 17) {
				String result = shibiema.substring(9, 10);
				if (!result.equalsIgnoreCase("U")
						&& !result.equalsIgnoreCase("0")
						&& !result.equalsIgnoreCase("Z")) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		return false;
	}

	// 检测出厂日期是否大于当前日期
	private boolean isCheckChushangDate(String chuchangdate) {

		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM");
		long day = 0;
		String nowdate = myFormatter.format(new Date());
		try {
			Date chuchang_date = myFormatter.parse(chuchangdate);
			Date now_date = myFormatter.parse(nowdate);
			day = chuchang_date.getTime() - now_date.getTime()
					;
		} catch (Exception e) {
			return true;
		}
		if (day <= 0) {
			return true;
		} else {
			return false;
		}

	}

	// 检测首次注册登记日期
	private boolean isCheckzhuCeDate(String zhuCeDate, String chuchangDate) {

		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM");
		long day = 0;
		long day2 = 0;
		String nowdate = myFormatter.format(new Date());
		try {
			Date zhuce_date = myFormatter.parse(zhuCeDate);
			Date now_date = myFormatter.parse(nowdate);
			Date chuchang_date3 = myFormatter.parse(chuchangDate);
			day = zhuce_date.getTime() - now_date.getTime()
					;
			day2 = zhuce_date.getTime() - chuchang_date3.getTime()
					;
		} catch (Exception e) {
			return true;
		}
		if (day <= 0) {
			if (day2 >= 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	// 交强险有效期检测
	private boolean checkJiaoQiangXianDate(String jiaoQiangXianDate,
			String chuchangDate) {

		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		long day2 = 0;
		try {
			Date jiaoqiangxian_date = myFormatter.parse(jiaoQiangXianDate);
			Date chuchang_date = myFormatter.parse(chuchangDate);
			day = jiaoqiangxian_date.getTime() - getNextYear(myFormatter.format(new Date()));
			day2 = jiaoqiangxian_date.getTime() - chuchang_date.getTime()
					;
		} catch (Exception e) {
			return true;
		}
		if (day2 > 0) {
			if (day <= 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	// 年检有效期检测
	private boolean checknianjianDate(String nianjianDate, String zhuceDate) {

		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		long day2 = 0;
		try {
			Date nianjian_date = myFormatter.parse(nianjianDate);
			Date zhuceDate_date = myFormatter.parse(zhuceDate);
			day = nianjian_date.getTime() - getNext2Year(myFormatter.format(new Date()));
			day2 = nianjian_date.getTime() - zhuceDate_date.getTime();
		} catch (Exception e) {
			return true;
		}
		if (day2 > 0) {
			if (day <= 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}
	
	private boolean checkQianzhiBaoFei()
	{
		if(!Nature.equals("非营运")&&!carleixing.equals("小型车"))
		{
			if(ScrapDate.equals(""))
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		
		return true;
	}

	//获取明年日期
	private long getNextYear(String date) {

		long time = 0;

		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date now_date = myFormatter.parse(date);
			now_date.getYear();
			int nextyear = now_date.getYear() + 1;
			now_date.setYear(nextyear);
			time = now_date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	//获取后年日期
	private long getNext2Year(String date) {

		long time = 0;

		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date now_date = myFormatter.parse(date);
			now_date.getYear();
			int nextyear = now_date.getYear() + 2;
			now_date.setYear(nextyear);
			time = now_date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}
	
	/**
	 * 检测提交模式下的输入数据格式
	 * */
	
	private boolean checkDate()
	{
		if(checkCheLiangShiBieMa(sx_edit_car_shibie_numer.getText().toString().trim()))
		{
			if(checkQianzhiBaoFei())
			{
				if(isCheckChushangDate(ManufactureDate))
				{
					if(isCheckzhuCeDate(FirstEnrolmentDate, ManufactureDate))
					{
						if(checkJiaoQiangXianDate(InsuranceValidity, ManufactureDate))
						{
							
							if(checknianjianDate(AnnualInspectionValidity, FirstEnrolmentDate))
							{
								return true;
							}
							else
							{
								ToastUtilMgr
								.TextToast(
										context,
										getString(R.string.AnnualInspectionValidity_error),
										Toast.LENGTH_SHORT);	
							}
						}
						else
						{
							ToastUtilMgr
							.TextToast(
									context,
									getString(R.string.InsuranceValidity_error),
									Toast.LENGTH_SHORT);	
						}
					}
					
					else{
						ToastUtilMgr
						.TextToast(
								context,
								getString(R.string.FirstEnrolmentDate_error),
								Toast.LENGTH_SHORT);
					}
					
				}
				else
				{
					ToastUtilMgr
					.TextToast(
							context,
							getString(R.string.ManufactureDate_error),
							Toast.LENGTH_SHORT);	
				}
			}
			else
			{
				ToastUtilMgr
				.TextToast(
						context,
						getString(R.string.ScrapDate_error),
						Toast.LENGTH_SHORT);
			}
		}
		else
		{
			ToastUtilMgr
			.TextToast(
					context,
					getString(R.string.IdentificationCode_error),
					Toast.LENGTH_SHORT);
			
		}
		return false;
	}
	
	/**
	 * 获得车牌归属地
	 * @param license
	 */
	private void GetLicenseAttribution(String license)
	{
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "GetLicenseAttribution");
		soapParameter.addProperty("License", license);			
		MyHandler myhandler =  new MyHandler()
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
						
						String Name = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("LicenseAttribution")).getProperty("Name").toString();

						if(!Name.trim().equals(""))
						{
							sx_edit_car_guishudi.setText(Name);
						}
					}
					if(Retcode.equals("4"))
					{
						final MineAlert alert = new MineAlert(context);
						alert.createAlertOneButton(false,"因服务器数据量太少，获取失败", 
								new View.OnClickListener() {								
									@Override
									public void onClick(View v) {
										alert.dimiss();
									}
								});
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
						SoapAction.GetLicenseAttribution , 
						"GetLicenseAttribution",
						soapParameter,
						myhandler,
						true,
						false
				);
	}
	
	
	/**
	 * 获得强制报废年限
	 * @param ManufactureDate   出厂日期
	 * @param FirstEnrolmentDate  首次注册日期
	 * @param Category          车辆类型
	 * @param Nature            使用性质
	 */
	private void GetDiscardedLimit(String ManufactureDate,String FirstEnrolmentDate,String Category,String Nature)
	{
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "GetDiscardedLimit");
		soapParameter.addProperty("ManufactureDate", ManufactureDate);
		soapParameter.addProperty("FirstEnrolmentDate", FirstEnrolmentDate);
		soapParameter.addProperty("Category", Category);
		soapParameter.addProperty("Nature", Nature);
		MyHandler myhandler =  new MyHandler()
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
						
						String LimitDate = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("DiscardedLimit")).getProperty("LimitDate").toString();

						if(!LimitDate.trim().equals(""))
						{		
							if(LimitDate.trim().equals("anyType{}"))
							{
								final MineAlert alert = new MineAlert(context);
								alert.createAlertOneButton(false,"无强制报废年限!", 
										new View.OnClickListener() {								
											@Override
											public void onClick(View v) {
												alert.dimiss();
											}
										});
							}
							
							//去掉日
							LimitDate = LimitDate.substring(0,LimitDate.lastIndexOf("-"));
							
							
							
							sx_edit_car_baofei_date.setText(LimitDate.equals("anyType{}")?"":LimitDate);
						}
						
					}
					if(Retcode.equals("4"))
					{
						final MineAlert alert = new MineAlert(context);
						alert.createAlertOneButton(false,"因服务器数据量太少，获取失败", 
								new View.OnClickListener() {								
									@Override
									public void onClick(View v) {
										alert.dimiss();
									}
								});
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
						SoapAction.GetDiscardedLimit , 
						"GetDiscardedLimit",
						soapParameter,
						myhandler,
						true,
						false
				);
	}
	
	
	
	/**
	 * 对拍照后的图片进行ocr识别
	 * @param License   编码后的图片字符串
	 */
	private void getLicenseRecognition(String License)
	{
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "LicenseRecognition");
		soapParameter.addProperty("Photo", License);
		MyHandler myhandler =  new MyHandler()
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
						//车牌号
						String PlateNo = ((SoapObject)backSoapObject.getProperty(0)).getProperty("PlateNo").toString();
						//类型
						String VehicleType = ((SoapObject)backSoapObject.getProperty(0)).getProperty("VehicleType").toString();
						//使用性质
						String UseCharacter = ((SoapObject)backSoapObject.getProperty(0)).getProperty("UseCharacter").toString();
						//品牌型号
						String Model = ((SoapObject)backSoapObject.getProperty(0)).getProperty("Model").toString();
						//车辆识别代码
						String VIN = ((SoapObject)backSoapObject.getProperty(0)).getProperty("VIN").toString();
						//发动机号码
						String EngineNo = ((SoapObject)backSoapObject.getProperty(0)).getProperty("EngineNo").toString();
						
						sx_edit_car_number.setText((PlateNo.equals("anyType{}")?"":PlateNo));
						
						if(VehicleType.equals("小型车"))
						{
							sx_edit_car_type1.setChecked(true);
						}
						if(VehicleType.equals("大型车"))
						{
							sx_edit_car_type2.setChecked(true);
						}
						
//						if(UseCharacter.equals("非营运"))
//						{
//							sx_xingzhi_spineer.setse
//							sx_xingzhi_radion1.setChecked(true);
//						}
//						if(UseCharacter.equals("营转非"))
//						{
//							sx_xingzhi_radion2.setChecked(true);
//						}
//						if(UseCharacter.equals("营运"))
//						{
//							sx_xingzhi_radion3.setChecked(true);
//						}
//						
						
						for(int i = 0 ;i < sx_xingzhi_spineer.getAdapter().getCount(); i++)
						{
							if(UseCharacter.equals(sx_xingzhi_spineer.getItemAtPosition(i).toString()))
							{
								sx_xingzhi_spineer.setSelection(i);
							}							
						}
						
						
						
						
						
						if(UseCharacter.equals("非营运") && VehicleType.equals("小型车"))
						{
							sx_edit_car_baofei_date.setText("");
						}
						
						
						sx_edit_car_pinpai_xinghao.setText((Model.equals("anyType{}")?"":Model));
						
						sx_edit_car_shibie_numer.setText((VIN.equals("anyType{}")?"":VIN));
						
						sx_edit_car_fadongji_number.setText((EngineNo.equals("anyType{}")?"":EngineNo));
						
					}
					else
					{
						final MineAlert alert = new MineAlert(context);
						alert.createAlertOneButton(false,"图像识别失败", 
								new View.OnClickListener() {								
									@Override
									public void onClick(View v) {
										alert.dimiss();
									}
								});
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
						SoapAction.LicenseRecognition , 
						"LicenseRecognition",
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
		String vin = sx_edit_car_shibie_numer.getText().toString().trim();
		new AppPreference(context).setVIN(vin);
		
		Log.e("ShouXuDengJiAcitivity。java saveData()", "保存手续登记信息");
		//获得数据
		License = sx_edit_car_number.getText().toString().trim();
		isInputChepaiGuiShuDi();
		isCarleixing();
		isInputShiBieDaiHao();
		isInputShiYongXingZhi();
		isInputPinPaiXingHao();
		isInputFaDongJiHao();
		isInputChuChangRiQi();
		isInputShouCiDengJiShiJian();
		isInputQiangZhiBaoFeiNianXian();
		isInputSuoYouRen();
		isInputPaiFangBiaoZhun();
		isInputBanZhengZhuangTai();
		isInputBianGengJiLu();
		isInputGouZhiShui();
		isInputJiaoQiangXianYouXiaoQi();
		isInputNianJianYouXiaoQi();
		isInputTongXingFeiYouXiaoQi();
		isInputWeiZhang();
		isInputPaiZhaoEDU();
		isInputYuanChangYaoShi();
		isInputSuiCheGongJu();
		isInputShuoMingShu();
		isInputBaoYangShouCe();
		isPaiZhao();
		
		ShouxuDengjiDB db = new ShouxuDengjiDB(context);
		long rowID = db.insert(new AppPreference(context).getTaskId(), 
				License, 
				BelongingTo, 
				IdentificationCode, 
				Nature, 
				carleixing, 
				BrandModel, 
				EngineSerialNum,
				ManufactureDate,
				FirstEnrolmentDate, 
				ScrapDate, 
				OwnerType, 
				EmissionStandards, 
				RegistrationStatus, 
				ChangedNum, 
				ChangeType, 
				PurchaseTax, 
				InsuranceValidity, 
				AnnualInspectionValidity, 
				TollsValidity, 
				IllegalScore, 
				IllegalMoney, 
				LicenceLines, 
				OriginalKeys, 
				IsMissingTool, 
				MissingTools, 
				HasInstruction, 
				HasMaintenanceManua, 
				LicensePhoto);		
		db.close();
		Log.e("ShouXuDengJiAcitivity。java saveData()", "NEW ROW ID:"+rowID);
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
	 */
	private void initUIDataFromDB()
	{
		ShouxuDengjiDB db = new ShouxuDengjiDB(context);
		Cursor cursor = db.selectById(new AppPreference(context).getTaskId());
		
		if(cursor.moveToFirst() == false)
		{			
			return;
		}
		
		
		String ResourceID = cursor.getString(0);
		String License = cursor.getString(1);
		String BelongingTo = cursor.getString(2);
		String IdentificationCode = cursor.getString(3);
		String Nature = cursor.getString(4);
		String Category = cursor.getString(5);
		String BrandModel = cursor.getString(6);
		String EngineSerialNum = cursor.getString(7);
		String ManufactureDate = cursor.getString(8);
		String FirstEnrolmentDate = cursor.getString(9);
		String ScrapDate = cursor.getString(10);
		String OwnerType = cursor.getString(11);
		String EmissionStandards = cursor.getString(12);
		String RegistrationStatus = cursor.getString(13);
		String ChangedNum = cursor.getString(14);
		String ChangeType = cursor.getString(15);
		String PurchaseTax = cursor.getString(16);
		String InsuranceValidity = cursor.getString(17);
		String AnnualInspectionValidity = cursor.getString(18);
		String TollsValidity = cursor.getString(19);
		String IllegalScore = cursor.getString(20);
		String IllegalMoney = cursor.getString(21);
		String LicenceLines = cursor.getString(22);
		String OriginalKeys = cursor.getString(23);
		String IsMissingTool = cursor.getString(24);
		String MissingTools = cursor.getString(25);
		String HasInstruction = cursor.getString(26);
		String HasMaintenanceManual = cursor.getString(27);
		String LicensePhoto = cursor.getString(28);
		db.close();
		
		sx_edit_car_number.setText(License);
		sx_edit_car_guishudi.setText(BelongingTo);
		sx_edit_car_shibie_numer.setText(IdentificationCode);
//		if(Nature.equals("非运营"))
//		{
//			sx_xingzhi_radion1.setChecked(true);
//		}
//		if(Nature.equals("营转非"))
//		{
//			sx_xingzhi_radion2.setChecked(true);
//		}
//		if(Nature.equals("营运"))
//		{
//			sx_xingzhi_radion3.setChecked(true);
//		}
		
		
		
		for(int i = 0 ;i < sx_xingzhi_spineer.getAdapter().getCount(); i++)
		{
			if(Nature.equals(sx_xingzhi_spineer.getItemAtPosition(i).toString()))
			{
				sx_xingzhi_spineer.setSelection(i);
			}							
		}
		
		
		
		
		if(Category.equals("小型车"))
		{
			sx_edit_car_type1.setChecked(true);
		}
		if(Category.equals("大型车"))
		{
			sx_edit_car_type2.setChecked(true);
		}
		sx_edit_car_pinpai_xinghao.setText(BrandModel);
		sx_edit_car_fadongji_number.setText(EngineSerialNum);
		sx_edit_car_chuchang_date.setText(ManufactureDate);
		sx_edit_car_zhuce_date.setText(FirstEnrolmentDate);
		sx_edit_car_baofei_date.setText(ScrapDate);		
		if(OwnerType.equals("0"))
		{
			sx_suoyouren_radion1.setChecked(true);
		}
		if(OwnerType.equals("1"))
		{
			sx_suoyouren_radion2.setChecked(true);
		}
		if(OwnerType.equals("2"))
		{
			sx_suoyouren_radion3.setChecked(true);
		}
		if(EmissionStandards.equals("0"))
		{
			sx_paifang_radion1.setChecked(true);
		}
		if(EmissionStandards.equals("1"))
		{
			sx_paifang_radion2.setChecked(true);
		}
		if(EmissionStandards.equals("2"))
		{
			sx_paifang_radion3.setChecked(true);
		}
		if(EmissionStandards.equals("3"))
		{
			sx_paifang_radion4.setChecked(true);
		}
		if(EmissionStandards.equals("4"))
		{
			sx_paifang_radion5.setChecked(true);
		}
		if(RegistrationStatus.equals("0"))
		{
			sx_banzheng_radion1.setChecked(true);
		}
		if(RegistrationStatus.equals("1"))
		{
			sx_banzheng_radion2.setChecked(true);
		}
		if(RegistrationStatus.equals("2"))
		{
			sx_banzheng_radion3.setChecked(true);
		}
		if(RegistrationStatus.equals("3"))
		{
			sx_banzheng_radion4.setChecked(true);
		}
		if(ChangedNum.equals("0"))
		{
			sx_biangeng_radion1.setChecked(true);
		}
		if(!ChangedNum.equals("0") && !ChangedNum.equals("-1"))
		{
			sx_biangeng_radion2.setChecked(true);
			
			if(!ChangedNum.equals("-1"))
			{
				sx_biangeng_cishu.setText(ChangedNum);
			}
			
		}
		if(!ChangeType.equals(""))
		{
			String[] types = ChangeType.split(",");
			for(String type : types)
			{
				if(type.equals("0"))
				{
					sx_biangeng_checkbox1.setChecked(true);
				}
				if(type.equals("1"))
				{
					sx_biangeng_checkbox2.setChecked(true);
				}
				if(type.equals("2"))
				{
					sx_biangeng_checkbox3.setChecked(true);
				}
			}
		}		
		if(PurchaseTax.equals("0"))
		{
			sx_gouzhi_radion1.setChecked(true);
		}
		if(PurchaseTax.equals("1"))
		{
			sx_gouzhi_radion2.setChecked(true);
		}
		if(PurchaseTax.equals("2"))
		{
			sx_gouzhi_radion3.setChecked(true);
		}
		sx_edit_jiaoqiangxian_date.setText(InsuranceValidity);
		sx_edit_nianjian_date.setText(AnnualInspectionValidity);
		sx_edit_tongxingfei_date.setText(TollsValidity);
		
		if(!IllegalScore.equals("-1"))
		{
			sx_edit_fen.setText(IllegalScore);
		}
		else
		{
			sx_edit_fen.setText("");
		}
		
		if(!IllegalMoney.equals("-1"))
		{
			sx_edit_yuan.setText(IllegalMoney);
		}
		else
		{
			sx_edit_yuan.setText("");
		}				
		
		if(LicenceLines.equals("0"))
		{
			sx_paizhao_edu_radion1.setChecked(true);
		}
		if(LicenceLines.equals("1"))
		{
			sx_paizhao_edu_radion2.setChecked(true);
		}
		if(OriginalKeys.equals("0"))
		{
			sx_yaoshi_radion2.setChecked(true);
		}
		if(!OriginalKeys.equals("0"))
		{
			sx_yaoshi_radion1.setChecked(true);
			sx_yaoshi_ba.setText(OriginalKeys);
		}
		if(IsMissingTool.equals("0"))
		{
			sx_gongju_radion1.setChecked(true);
		}
		if(IsMissingTool.equals("1"))
		{
			sx_gongju_radion2.setChecked(true);
		}
		sx_gongju_lost.setText(MissingTools);
		if(HasInstruction.equals("1"))
		{
			sx_shoumingshu_radion1.setChecked(true);
		}
		if(HasInstruction.equals("0"))
		{
			sx_shoumingshu_radion2.setChecked(true);
		}
		if(HasMaintenanceManual.equals("1"))
		{
			sx_baoyang_radion1.setChecked(true);
		}
		if(HasMaintenanceManual.equals("0"))
		{
			sx_baoyang_radion2.setChecked(true);
		}
		
		this.LicensePhoto = LicensePhoto;
		
		
		if(bm!=null)
		{
			if(!bm.isRecycled())
			{
				bm.recycle();
				bm = null;
			}
		}
		bm = PictureUtil.stringtoBitmap(LicensePhoto);
		paizhao_image.setImageBitmap(bm);
		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		if(bm!=null)
		{
			if(!bm.isRecycled())
			{
				bm.recycle();
				bm = null;
			}
		}
		

		System.gc();
	}

	
	/**
	 * 获得排放标准
	 * @param BrandModel   出厂日期
	 */
	private void GetEmission(String BrandModel)
	{
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "GetEmission");
		soapParameter.addProperty("BrandModel", BrandModel);
		MyHandler myhandler =  new MyHandler()
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
						
						String Emission = ((SoapObject)backSoapObject.getProperty(0)).getProperty("Emission").toString();

						if(!Emission.trim().equals(""))
						{
							if(Emission.trim().equals("anyType{}"))
							{
								final MineAlert alert = new MineAlert(context);
								alert.createAlertOneButton(false,"未获取到该型号排放标准信息!", 
										new View.OnClickListener() {								
											@Override
											public void onClick(View v) {
												alert.dimiss();
											}
										});
							}
							else
							{
								if(Emission.equals("1"))
								{
									sx_paifang_radion1.setChecked(true);
								}
								if(Emission.equals("2"))
								{
									sx_paifang_radion2.setChecked(true);
								}
								if(Emission.equals("3"))
								{
									sx_paifang_radion3.setChecked(true);
								}
								if(Emission.equals("4"))
								{
									sx_paifang_radion4.setChecked(true);
								}
								if(Emission.equals("5"))
								{
									sx_paifang_radion5.setChecked(true);
								}
							}							
						}						
					}
					else
					{
						final MineAlert alert = new MineAlert(context);
						alert.createAlertOneButton(false,"获取失败", 
								new View.OnClickListener() {								
									@Override
									public void onClick(View v) {
										alert.dimiss();
									}
								});
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
						SoapAction.GetEmission , 
						"GetEmission",
						soapParameter,
						myhandler,
						true,
						false
				);
	}
	
}
