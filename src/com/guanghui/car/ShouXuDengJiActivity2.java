package com.guanghui.car;

import java.util.HashMap;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.guanghui.car.common.ToastUtilMgr;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;
import com.guanghui.car.config.AppPreference;
import com.guanghui.car.ui.DateTimePickDialogUtil;

/**
 * 手续登记界面2
 * 显示13-20项为空的部分
 * @author zhangyun
 *
 */
public class ShouXuDengJiActivity2 extends Activity{

	private Button activity_shouxu_dengji2_return;
	private List<HashMap> data;

	private String NullFields ;
	//
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
		
		private LinearLayout activity_shouxu_dengji2_13,activity_shouxu_dengji2_14,activity_shouxu_dengji2_14_2,activity_shouxu_dengji2_15,activity_shouxu_dengji2_16,activity_shouxu_dengji2_17,activity_shouxu_dengji2_18,activity_shouxu_dengji2_19,activity_shouxu_dengji2_20;
		private TextView activity_shouxu_dengji2_13_line,activity_shouxu_dengji2_14_line,activity_shouxu_dengji2_15_line,activity_shouxu_dengji2_16_line,activity_shouxu_dengji2_17_line,activity_shouxu_dengji2_18_line,activity_shouxu_dengji2_19_line,activity_shouxu_dengji2_20_line;
		
		
		
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
		
	//
	private SoapMgr mgr;
	private SoapObject backSoapObject;
	private Context context = this;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_shouxu_dengji2);
		new BuildTab(context,1) ;
		
		activity_shouxu_dengji2_return = (Button)this.findViewById(R.id.activity_shouxu_dengji2_return);
		
		NullFields = this.getIntent().getStringExtra("NullFields");
		
		
		activity_shouxu_dengji2_return.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		
		
		initBottom();
		initUI();
		
		
		
		
		
		if(NullFields.indexOf("13")==-1)
		{
			activity_shouxu_dengji2_13.setVisibility(View.GONE);
			activity_shouxu_dengji2_13_line.setVisibility(View.GONE);
		}
		if(NullFields.indexOf("14")==-1)
		{
			activity_shouxu_dengji2_14.setVisibility(View.GONE);
			activity_shouxu_dengji2_14_2.setVisibility(View.GONE);
			activity_shouxu_dengji2_14_line.setVisibility(View.GONE);
		}
		if(NullFields.indexOf("15")==-1)
		{
			activity_shouxu_dengji2_15.setVisibility(View.GONE);
			activity_shouxu_dengji2_15_line.setVisibility(View.GONE);
		}
		if(NullFields.indexOf("16")==-1)
		{
			activity_shouxu_dengji2_16.setVisibility(View.GONE);
			activity_shouxu_dengji2_16_line.setVisibility(View.GONE);
		}
		if(NullFields.indexOf("17")==-1)
		{
			activity_shouxu_dengji2_17.setVisibility(View.GONE);
			activity_shouxu_dengji2_17_line.setVisibility(View.GONE);
		}
		if(NullFields.indexOf("18")==-1)
		{
			activity_shouxu_dengji2_18.setVisibility(View.GONE);
			activity_shouxu_dengji2_18_line.setVisibility(View.GONE);
		}
		if(NullFields.indexOf("19")==-1)
		{
			activity_shouxu_dengji2_19.setVisibility(View.GONE);
			activity_shouxu_dengji2_19_line.setVisibility(View.GONE);
		}
		if(NullFields.indexOf("20")==-1)
		{
			activity_shouxu_dengji2_20.setVisibility(View.GONE);
			activity_shouxu_dengji2_20_line.setVisibility(View.GONE);
		}
		
		
		
		
	}
	
	OnClickListener timeEditClick = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.sx_edit_jiaoqiangxian_date:
				DateTimePickDialogUtil dateTimePicKDialog4 = new DateTimePickDialogUtil(
						ShouXuDengJiActivity2.this, sx_edit_jiaoqiangxian_date,1);
				break;
			case R.id.sx_edit_nianjian_date:
				DateTimePickDialogUtil dateTimePicKDialog5 = new DateTimePickDialogUtil(
						ShouXuDengJiActivity2.this, sx_edit_nianjian_date,1);
				break;
			case R.id.sx_edit_tongxingfei_date:
				DateTimePickDialogUtil dateTimePicKDialog6 = new DateTimePickDialogUtil(
						ShouXuDengJiActivity2.this, sx_edit_tongxingfei_date,1);
				break;
			}
		}
	};
	
	
	/**
	 * 初始化界面
	 */
	private void initUI()
	{
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
				sx_biangeng_cishu = (EditText) findViewById(R.id.sx_biangeng_cishu);
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
				// 购置税
				sx_gouzhi_radion1 = (RadioButton) findViewById(R.id.sx_gouzhi_radion1);
				sx_gouzhi_radion2 = (RadioButton) findViewById(R.id.sx_gouzhi_radion2);
				sx_gouzhi_radion3 = (RadioButton) findViewById(R.id.sx_gouzhi_radion3);

				// 牌照额度
				sx_paizhao_edu_radion1 = (RadioButton) findViewById(R.id.sx_paizhao_edu_radion1);
				sx_paizhao_edu_radion2 = (RadioButton) findViewById(R.id.sx_paizhao_edu_radion2);
				
				
				activity_shouxu_dengji2_13 = (LinearLayout) findViewById(R.id.activity_shouxu_dengji2_13);
				activity_shouxu_dengji2_14 = (LinearLayout) findViewById(R.id.activity_shouxu_dengji2_14);
				activity_shouxu_dengji2_14_2 = (LinearLayout) findViewById(R.id.activity_shouxu_dengji2_14_2);
				activity_shouxu_dengji2_15 = (LinearLayout) findViewById(R.id.activity_shouxu_dengji2_15);
				activity_shouxu_dengji2_16 = (LinearLayout) findViewById(R.id.activity_shouxu_dengji2_16);
				activity_shouxu_dengji2_17 = (LinearLayout) findViewById(R.id.activity_shouxu_dengji2_17);
				activity_shouxu_dengji2_18 = (LinearLayout) findViewById(R.id.activity_shouxu_dengji2_18);
				activity_shouxu_dengji2_19 = (LinearLayout) findViewById(R.id.activity_shouxu_dengji2_19);
				activity_shouxu_dengji2_20 = (LinearLayout) findViewById(R.id.activity_shouxu_dengji2_20);
				
				
				activity_shouxu_dengji2_13_line = (TextView) findViewById(R.id.activity_shouxu_dengji2_13_line);
				activity_shouxu_dengji2_14_line = (TextView) findViewById(R.id.activity_shouxu_dengji2_14_line);
				activity_shouxu_dengji2_15_line = (TextView) findViewById(R.id.activity_shouxu_dengji2_15_line);
				activity_shouxu_dengji2_16_line = (TextView) findViewById(R.id.activity_shouxu_dengji2_16_line);
				activity_shouxu_dengji2_17_line = (TextView) findViewById(R.id.activity_shouxu_dengji2_17_line);
				activity_shouxu_dengji2_18_line = (TextView) findViewById(R.id.activity_shouxu_dengji2_18_line);
				activity_shouxu_dengji2_19_line = (TextView) findViewById(R.id.activity_shouxu_dengji2_19_line);
				activity_shouxu_dengji2_20_line = (TextView) findViewById(R.id.activity_shouxu_dengji2_20_line);
	}

	
	
	private void initBottom() {
		((Button)findViewById(R.id.stop_save)).setVisibility(View.GONE);
		((Button)findViewById(R.id.comfirm_communit)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				
				sendEnrolment();
				
			}
		});
	}
	
	
	
	private boolean check()
	{
		boolean n13 = true,n14 = true,n15 = true,n16 = true,n17 = true,n18 = true,n19 = true,n20 = true;
		if(NullFields.indexOf("13")!=-1)
		{
			if (isInputBanZhengZhuangTai()) {
			
			}else {
					ToastUtilMgr.TextToast(context,getString(R.string.without_txt13),Toast.LENGTH_SHORT);
					return false;
			}
		}
		
		if(NullFields.indexOf("14")!=-1)
		{
			if (isInputBianGengJiLu()) {
				
			}else {
					ToastUtilMgr.TextToast(context,getString(R.string.without_txt14),Toast.LENGTH_SHORT);
					return false;
			}
		}
		
		if(NullFields.indexOf("15")!=-1)
		{
			if (isInputGouZhiShui()) {
				
			}else {
					ToastUtilMgr.TextToast(context,getString(R.string.without_txt15),Toast.LENGTH_SHORT);
					return false;
			}
		}
		
		if(NullFields.indexOf("16")!=-1)
		{
			if (isInputJiaoQiangXianYouXiaoQi()) {
				
			}else {
					ToastUtilMgr.TextToast(context,getString(R.string.without_txt16),Toast.LENGTH_SHORT);
					return false;
			}
		}
		
		if(NullFields.indexOf("17")!=-1)
		{
			if (isInputNianJianYouXiaoQi()) {
				
			}else {
					ToastUtilMgr.TextToast(context,getString(R.string.without_txt17),Toast.LENGTH_SHORT);
					return false;
			}
		}
		
		if(NullFields.indexOf("18")!=-1)
		{
			if (isInputTongXingFeiYouXiaoQi()) {
				
			}else {
					ToastUtilMgr.TextToast(context,getString(R.string.without_txt18),Toast.LENGTH_SHORT);
					return false;
			}	
		}
			
		if(NullFields.indexOf("19")!=-1)
		{
			if (isInputWeiZhang()) {
				
			}else {
					ToastUtilMgr.TextToast(context,getString(R.string.without_txt19),Toast.LENGTH_SHORT);
					return false;
			}
		}
		
		if(NullFields.indexOf("20")!=-1)
		{
			if (isInputPaiZhaoEDU()) {
				
			}else {
					ToastUtilMgr.TextToast(context,getString(R.string.without_txt20),Toast.LENGTH_SHORT);
					return false;
			}
		}
		
		
		if(n13 && n14 && n15 && n16 && n17 && n18 && n19 && n20)
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
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
			return true;
		}
		return false;
	}

	/**
	 * 检查变更记录是否输入
	 * */
	private boolean isInputBianGengJiLu() {
		if (!sx_biangeng_radion1.isChecked()
				&& !sx_biangeng_radion2.isChecked()) {
			return false;
		}
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
			return true;
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
				return true;
			}
			return false;
		}
		return false;
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
			return true;
		}
		return false;
	}

	/**
	 * 检查交强险有效期是否输入
	 * */
	private boolean isInputJiaoQiangXianYouXiaoQi() {
		String jiaoQiangXian = sx_edit_jiaoqiangxian_date.getText().toString()
				.trim();
		if (!jiaoQiangXian.equals("")) {
			InsuranceValidity = jiaoQiangXian;
			return true;
		}
		return false;
	}

	/**
	 * 检查年检有效期是否输入
	 * */
	private boolean isInputNianJianYouXiaoQi() {
		String youXiaoQi = sx_edit_nianjian_date.getText().toString().trim();
		if (!youXiaoQi.equals("")) {
			AnnualInspectionValidity = youXiaoQi;
			return true;
		}
		return false;
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
			return true;
		}
		return false;
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
			return true;
		}
		return false;
	}

	
	
	
	
	
	/**
	 * 发送手续登记信息
	 * */

	private void sendEnrolment() {
		
		boolean b= check();
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
					// 0成功，1失败
					if (Retcode.equals("0")) {
						ToastUtilMgr.TextToast(context, "数据提交成功",
								ToastUtilMgr.LENGTH_SHORT);
						
							finish();

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

		isInputBanZhengZhuangTai();
		isInputBianGengJiLu();
		isInputGouZhiShui();
		isInputJiaoQiangXianYouXiaoQi();
		isInputNianJianYouXiaoQi();
		isInputTongXingFeiYouXiaoQi();
		isInputWeiZhang();
		isInputPaiZhaoEDU();

		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace,
				"SaveEnrolment2");
		soapParameter.addProperty("ResourceID",
				new AppPreference(context).getTaskId());
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
		mgr = new SoapMgr(context, null, null, SoapAction.SaveEnrolment2,
				"SaveEnrolment2", soapParameter, myhandler, true, false);
	}
	

}
