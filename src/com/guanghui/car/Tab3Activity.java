package com.guanghui.car;

import java.io.File;
import java.util.HashMap;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.guanghui.car.common.UpdateManager;
import com.guanghui.car.communications.MineAlert;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;
import com.guanghui.car.config.AppConfig;
import com.guanghui.car.config.AppPreference;

/**
 * 个人设置界面
 * @author zhangyun
 *
 */
public class Tab3Activity extends Activity{

	private Context context = this;
	private EditText activity_tab3_edit1;
	private EditText activity_tab3_edit2;
	private EditText activity_tab3_edit3;
	private Button activity_tab3_btn1;
	private Button activity_tab3_btn2;
	private MyHandler myhandler;
	private SoapMgr mgr;
	private SoapObject backSoapObject;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_tab3);
		activity_tab3_edit1 = (EditText)this.findViewById(R.id.activity_tab3_edit1);
		activity_tab3_edit2 = (EditText)this.findViewById(R.id.activity_tab3_edit2);
		activity_tab3_edit3 = (EditText)this.findViewById(R.id.activity_tab3_edit3);
		activity_tab3_btn1 = (Button)this.findViewById(R.id.activity_tab3_btn1);
		activity_tab3_btn2 = (Button)this.findViewById(R.id.activity_tab3_btn2);
		
		activity_tab3_btn1.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				//隐藏软键盘
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);  
				imm.hideSoftInputFromWindow(activity_tab3_edit1.getWindowToken(), 0);
				imm.hideSoftInputFromWindow(activity_tab3_edit2.getWindowToken(), 0);
				imm.hideSoftInputFromWindow(activity_tab3_edit3.getWindowToken(), 0);
				
				
				String str1 = activity_tab3_edit1.getText().toString();
				String str2 = activity_tab3_edit2.getText().toString();
				String str3 = activity_tab3_edit3.getText().toString();
				if(str1.equals("") || str2.equals("") || str3.equals(""))
				{
					final MineAlert alert = new MineAlert(context);
					alert.createAlertOneButton(false,"请输入完整的三个密码，不能为空!", 
							new View.OnClickListener() {								
								@Override
								public void onClick(View v) {
									alert.dimiss();
								}
							});
					return;
				}
				if(!str2.equals(str3))
				{
					final MineAlert alert = new MineAlert(context);
					alert.createAlertOneButton(false,"两次新密码不一致，请重新输入!", 
							new View.OnClickListener() {								
								@Override
								public void onClick(View v) {
									alert.dimiss();
								}
							});
					return;
				}
				
				ModifyPassword(new AppPreference(context).getLoginUserID(),str1,str2);
				
				
			}
		});
		
		activity_tab3_btn2.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				checkApkVer();
			}
		});
		
	}
	
	

	/**
     * 修改用户密码
     */
    private void ModifyPassword(String UserID,String Password,final String NewPassword)
    {		
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "ModifyPassword");
		soapParameter.addProperty("UserID", UserID);
		soapParameter.addProperty("Password", Password);
		soapParameter.addProperty("NewPassword", NewPassword);	
		myhandler =  new MyHandler()
		{
			@Override
			public void success(Message msg) {
				backSoapObject = mgr.getServiceBackSoapObject();						
				if(backSoapObject!=null)
				{
					String Retcode = ((SoapObject)backSoapObject.getProperty(0)).getProperty("Retcode").toString();
					//0成功，1上传数据格式不正确，2数据库出错，3用户ID不存在或旧密码不正确
					if(Retcode.equals("0"))
					{
						final MineAlert alert = new MineAlert(context);
						alert.createAlertOneButton(false,"修改密码成功,请牢记新密码:"+NewPassword+"", 
								new View.OnClickListener() {								
									@Override
									public void onClick(View v) {
										alert.dimiss();
										
										activity_tab3_edit1.setText("");
										activity_tab3_edit2.setText("");
										activity_tab3_edit3.setText("");
										
									}
								});
						return;
					}
					if(Retcode.equals("1"))
					{
						final MineAlert alert = new MineAlert(context);
						alert.createAlertOneButton(false,"上传数据格式不正确!", 
								new View.OnClickListener() {								
									@Override
									public void onClick(View v) {
										alert.dimiss();
									}
								});
						return;
					}
					if(Retcode.equals("2"))
					{
						final MineAlert alert = new MineAlert(context);
						alert.createAlertOneButton(false,"服务器数据库出错!", 
								new View.OnClickListener() {								
									@Override
									public void onClick(View v) {
										alert.dimiss();
									}
								});
						return;
					}
					if(Retcode.equals("3"))
					{
						final MineAlert alert = new MineAlert(context);
						alert.createAlertOneButton(false,"用户ID不存在或旧密码不正确!", 
								new View.OnClickListener() {								
									@Override
									public void onClick(View v) {
										alert.dimiss();
									}
								});
						return;
					}
					if(!Retcode.equals("0")&&!Retcode.equals("1")&&!Retcode.equals("2")&&!Retcode.equals("3"))
					{
						final MineAlert alert = new MineAlert(context);
						alert.createAlertOneButton(false,"设置新密码失败!", 
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
						SoapAction.ModifyPassword , 
						"ModifyPassword",
						soapParameter,
						myhandler,
						true,
						false
				);
    }
    
    
    /**
     * 检测是否需要更新程序
     */
    private void checkApkVer()
    {        
        
        String versionName = "";
        try
        {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionName = context.getPackageManager().getPackageInfo("com.guanghui.car", 0).versionName;
        } catch (NameNotFoundException e)
        {
            e.printStackTrace();
        }
       
        final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "CheckVersion");
        soapParameter.addProperty("verison", versionName);
        
       //如果下载apk失败，用来继续读取所有门店信息的handler
        final Handler handler = new Handler()
        {
            @ Override
            public void handleMessage(Message msg)
            {
                super.handleMessage(msg) ;
                final MineAlert alert = new MineAlert(context);
				alert.createAlertOneButton(false,"下载新版本APK失败,请重试!", 
						new View.OnClickListener() {								
							@Override
							public void onClick(View v) {
								alert.dimiss();
							}
						});		 
            }            
        };
        
        myhandler =  new MyHandler()
        {
            @Override
            public void success(Message msg) 
            {
                backSoapObject = mgr.getServiceBackSoapObject();                        
                if(backSoapObject!=null)
                {
                    String HasNew = ((SoapObject)backSoapObject.getProperty(0)).getProperty("HasNew").toString();
                    final String Version = ((SoapObject)backSoapObject.getProperty(0)).getProperty("Verison").toString();
                    final String Url = ((SoapObject)backSoapObject.getProperty(0)).getProperty("Url").toString();
                    final String Content = ((SoapObject)backSoapObject.getProperty(0)).getProperty("Content").toString();
                    if(HasNew.trim().equals("true"))
                    {
                        //更新
                        final AlertDialog dialog = new AlertDialog.Builder(context).create();                    
                        dialog.show();
                        dialog.setCancelable(false);
                        dialog.getWindow().setContentView(R.layout.apk_update_confirm);
                        
                        ((Button)dialog.findViewById(R.id.apk_update_confirm_ok)).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                
                                //判断是否已有该文件了
                                File apkfile = new File(AppConfig.CacheDir + "/" + "apk"+Version+".apk");
                                if(apkfile.exists())
                                {
                                	apkfile.delete();
                                }
                                
                                
                                
                                
                                //下载apk文件
                                HashMap<String, String> mHashMap = new HashMap<String, String>();                                
                                mHashMap.put("name","apk"+Version+".apk");
                                mHashMap.put("url",Url);
                                mHashMap.put("Content",Content);
                                UpdateManager update = new UpdateManager(Tab3Activity.this,mHashMap,handler);
                                update.showDownloadDialog();                                
                            }
                        });                    
                        ((Button)dialog.findViewById(R.id.apk_update_confirm_cancel)).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //稍后更新
                                dialog.dismiss();
                            }
                        });
                    }
                    else
                    {
                    	final MineAlert alert = new MineAlert(context);
    					alert.createAlertOneButton(false,"此版本已是最新！", 
    							new View.OnClickListener() {								
    								@Override
    								public void onClick(View v) {
    									alert.dimiss();
    								}
    							});		        
                    }
                    
                }
            }
            @ Override
            public void failed(Message msg)
            {                
            }
        };
        mgr = new SoapMgr(context,
                null, 
                null, 
                SoapAction.CheckVersion , 
                "CheckVersion",
                soapParameter,
                myhandler,
                true,
                false
        );
        
    }


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//隐藏软键盘
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);  
		imm.hideSoftInputFromWindow(activity_tab3_edit1.getWindowToken(), 0);
		imm.hideSoftInputFromWindow(activity_tab3_edit2.getWindowToken(), 0);
		imm.hideSoftInputFromWindow(activity_tab3_edit3.getWindowToken(), 0);
	}
	
	
	
    
    
    
}
