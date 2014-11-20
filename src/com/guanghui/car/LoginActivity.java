package com.guanghui.car;

import java.io.File;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.guanghui.car.application.MyApplication;
import com.guanghui.car.common.NetManager;
import com.guanghui.car.communications.MineAlert;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;
import com.guanghui.car.config.AppPreference;
import com.guanghui.car.service.HeartbeatService;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

/**
 * 用户登录界面
 * @author zhangyun
 *
 */
public class LoginActivity extends Activity {
	
	private Context context = this;

	private EditText username;
	private EditText password;
	private Button btnLogin;
	private LinearLayout ly_loginOut;
	private String strUserName;
	private String strPassword;
	private RelativeLayout login_userName_rely2;
	private String state="";
	private TextView login_zhuxiao_name;
	
	private MyHandler myhandler;
	private SoapMgr mgr;
	private SoapObject backSoapObject;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		
		username = (EditText)this.findViewById(R.id.login_edt_username);
		password = (EditText)this.findViewById(R.id.login_edt_userpassword);
		btnLogin = (Button)this.findViewById(R.id.login_btn);
		login_zhuxiao_name = (TextView)this.findViewById(R.id.login_zhuxiao_name);
		ly_loginOut = (LinearLayout)this.findViewById(R.id.login_ly_login_out);
		login_userName_rely2 = (RelativeLayout)this.findViewById(R.id.login_userName_rely2);
		
		ly_loginOut.setVisibility(View.GONE);
		
		
		
		if(new AppPreference(context).getSuoDingState().equals("1") )
		{
			//锁定
			login_zhuxiao_name.setText(new AppPreference(context).getLoginUserName());
			ly_loginOut.setVisibility(View.VISIBLE);
			login_userName_rely2.setVisibility(View.GONE);
		}
		else
		{
			ly_loginOut.setVisibility(View.GONE);
		}
		
		if(this.getIntent().getExtras()!=null)
		{
			state = this.getIntent().getExtras().getString("state");
			if(state!=null)
			{
				//1锁定  0注销
				if(state.trim().equals("1"))
				{
					//锁定
					new AppPreference(context).setSuoDingState("1"); 
					login_zhuxiao_name.setText(new AppPreference(context).getLoginUserName());
					ly_loginOut.setVisibility(View.VISIBLE);
					login_userName_rely2.setVisibility(View.GONE);
				}
				if(state.trim().equals("0"))
				{
					//注销				
					ly_loginOut.setVisibility(View.GONE);
				}
			}
			
			
			String exit = this.getIntent().getExtras().getString("exit");
			if(exit!=null)
			{
				if(exit.trim().equals("exit"))
				{
					finish();
				}
			}
			
		}
		
		
		
		
		
				
		//用户登录
		btnLogin.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				//隐藏软键盘
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);  
				imm.hideSoftInputFromWindow(username.getWindowToken(), 0);
				imm.hideSoftInputFromWindow(password.getWindowToken(), 0);
				
				
				//判断是否已联网
				if(!NetManager.isNETConnect(context))
				{
					
					final MineAlert alert = new MineAlert(context);
					alert.createAlertOneButton(false,getString(R.string.login_error2), 
							new View.OnClickListener() {								
								@Override
								public void onClick(View v) {
									alert.dimiss();
								}
							});
					return;
				}
				
				
				
				
				
				if(state.equals("1"))
				{
					//锁定
					strUserName = new AppPreference(context).getLoginUserName();
				}
				if(state.equals("0"))
				{
					//注销
					strUserName = username.getText().toString().trim();
				}
				if(state.equals(""))
				{
					//正常登录
					strUserName = username.getText().toString().trim();
				}
				if(new AppPreference(context).getSuoDingState().equals("1") )
				{
					strUserName = new AppPreference(context).getLoginUserName();
				}
				strPassword = password.getText().toString().trim();
				if(strUserName.equals("") )
				{
					
					final MineAlert alert = new MineAlert(context);
					alert.createAlertOneButton(false,"请输入帐号", 
							new View.OnClickListener() {								
								@Override
								public void onClick(View v) {
									alert.dimiss();
								}
							});		
					return;
				}
				if(strPassword.equals(""))
				{
					final MineAlert alert2 = new MineAlert(context);
					alert2.createAlertOneButton(false,"请输入密码", 
							new View.OnClickListener() {								
								@Override
								public void onClick(View v) {
									alert2.dimiss();
								}
							});
					return;
				}
								
					if(getIntent().getExtras()!=null)
					{
						if(state.equals("1"))
						{
							//锁定
							getUserInfo(strUserName,strPassword);
						}
						if(state.equals("0"))
						{
							//注销
							getUserInfo(strUserName,strPassword);
						}
						if(state.equals(""))
						{
							//正常登录
							Intent intent1 = new Intent();
							intent1.setClass(context, ActivityMain.class);
							intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);         
							startActivity(intent1);
						}
					}
					else
					{
						//验证帐号密码是否正确						
						getUserInfo(strUserName,strPassword);
					}
				
			}
		});
	}

	/**
     * 获取用户的基本信息
     */
    private void getUserInfo(final String username,String password)
    {
    	String android_id="00000000000000";
    	TelephonyManager telephonemanage = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
    	try
    	{
    		android_id = telephonemanage.getDeviceId();
    	}
    	catch(Exception ex)
    	{
    		android_id = null;	
    	}    	
    	if(android_id == null)
    	{
    		android_id="00000000000000";
    	}
    	Log.e("LoginActivity.java android_id:", android_id);;
    	
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "Login");
		soapParameter.addProperty("username", username);
		soapParameter.addProperty("password", password);
		soapParameter.addProperty("serialNumber",  android_id);
		
		// 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo;
        String version = "";
		try {
			packInfo = packageManager.getPackageInfo(getPackageName(),0);
			version = packInfo.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			version = "";
		}
        
        
		soapParameter.addProperty("version",  version);
		
		
		
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
						new AppPreference(context).setSuoDingState("0"); 
						//获得该用户信息
						String RegionID = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("User")).getProperty("RegionID").toString();
						String RegionName = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("User")).getProperty("RegionName").toString();
						String ShopID = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("User")).getProperty("ShopID").toString();
						String ShopName = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("User")).getProperty("ShopName").toString();
						String Id = ((SoapObject)((SoapObject)backSoapObject.getProperty(0)).getProperty("User")).getProperty("Id").toString();
						
						new AppPreference(context).setRegionID(RegionID);
						new AppPreference(context).setRegionName(RegionName);
						new AppPreference(context).setShopID(ShopID);
						new AppPreference(context).setShopName(ShopName);
						
						
						//将用户ID号保存
						new AppPreference(context).setLoginUserID(Id);
						new AppPreference(context).setLoginUserName(username);
					
						
						//启动服务，心跳服务
						Intent serviceIntent = new Intent();
						serviceIntent.setClass(context, HeartbeatService.class);
						startService(serviceIntent);
						
							Intent intent = new Intent();
							intent.setClass(context, ActivityMain.class);
							intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(intent);
												
					}
					else
					{	
						if(state.trim().equals(""))
						{
							final MineAlert alert = new MineAlert(context);
							alert.createAlertOneButton(false,getString(R.string.username_or_password_error), 
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
							alert.createAlertOneButton(false,"密码错误!", 
									new View.OnClickListener() {								
										@Override
										public void onClick(View v) {
											alert.dimiss();
										}
									});
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
						SoapAction.Login , 
						"Login",
						soapParameter,
						myhandler,
						true,
						false
				);
    }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {		
		 // 按下键盘上返回按钮      
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	
        	
        	if(new AppPreference(context).getSuoDingState().equals("1") )
    		{
        		//如果是锁定的状态按返回键,清除堆栈里的所有数据，退出程序
        		Intent exitIntent = new Intent();
        		exitIntent.setClass(context, ExitAcitivity.class);
        		exitIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(exitIntent) ;
                finish() ;
                return true;
    		}
        	else
        	{
        		return super.onKeyDown(keyCode, event);
        	}
        	
        	
        }       
        else 
        {
        	return super.onKeyDown(keyCode, event);      
        }
	}
    
    
}
