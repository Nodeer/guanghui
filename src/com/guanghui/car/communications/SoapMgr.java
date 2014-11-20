package com.guanghui.car.communications;
import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.guanghui.car.R;
import com.guanghui.car.config.AppConfig;
import com.guanghui.car.ui.LoadingProgressDialog;



/**
 * SOAP传输协议处理类
 *
 * @author 张云
 *
 */
public class SoapMgr {
	
	//变量声明
	private Context context;	
	private String nameSpace;	
	private String url;
	private String methodName;
	private String rootName;
	private SoapObject parameter;
	private LoadingProgressDialog progressDialog;
	//外部传值进来的Handler对象
	private MyHandler handler;
	//内部处理的Handler对象   用来下载失败弹窗用
	private MyHandler mHandler;
		
	
	//服务器处理后的SoapObject对象
	private SoapObject serviceBackSoapObject;
		
	//是否显示弹窗
	private boolean showdialog = true;
	
	

	/**
	 * 构造函数，并执行交互处理
	 * @param context   	Context对象
	 * @param nameSpace		命名空间   可以传入null，   则默认为:"http://tempuri.org/"
	 * @param url			服务地址   可以传入null，   则默认为:"http://www.yuekexitong.com/WebService.asmx"
	 * @param methodName	方法名字   例："GetNurseInfo"
	 * @param rootName      根节点的字符串     例："struNuser"
	 * @param Property      参数集合   SoapObject对象
	 * @param handler       Handler对象   用来和界面同步   在handler对象中进行获取操作后的SoapObject对象
	 * @param showdialog    是否显示进度条Dialog，适用于后台读取信息条数的服务
	 */
	public SoapMgr(
			final Context context,
			String nameSpace,
			String url,
			String methodName,
			String rootName,
			SoapObject parameter,
			final MyHandler handler,
			final boolean showdialog
			)
	{
		this.context = context;
		if(nameSpace!=null)
			{this.nameSpace = nameSpace;}
		else
			{this.nameSpace = SoapAction.nameSpace;}
		
		if(url!=null)
			{this.url = url;}
		else
			{this.url = SoapAction.url;}
		
		this.showdialog = showdialog;
		this.methodName = methodName;
		this.rootName = rootName;
		this.parameter = parameter;
		this.handler = handler;
		init(context,nameSpace,url,methodName,rootName,parameter,handler,showdialog,false);
	}
	
	
	/**
	 * 构造函数，并执行交互处理
	 * @param context   	Context对象
	 * @param nameSpace		命名空间   可以传入null，   则默认为:"http://tempuri.org/"
	 * @param url			服务地址   可以传入null，   则默认为:"http://www.yuekexitong.com/WebService.asmx"
	 * @param methodName	方法名字   例："GetNurseInfo"
	 * @param rootName      根节点的字符串     例："struNuser"
	 * @param Property      参数集合   SoapObject对象
	 * @param handler       Handler对象   用来和界面同步   在handler对象中进行获取操作后的SoapObject对象
	 * @param showdialog       是否显示进度条Dialog，适用于后台读取信息条数的服务
	 * @param showErrorDialog  是否显示错误的Dialog
	 */
	public SoapMgr(
			final Context context,
			String nameSpace,
			String url,
			String methodName,
			String rootName,
			SoapObject parameter,
			final MyHandler handler,
			final boolean showdialog,
			final boolean showErrorDialog
			)
	{
		this.context = context;
		if(nameSpace!=null)
			{this.nameSpace = nameSpace;}
		else
			{this.nameSpace = SoapAction.nameSpace;}
		
		if(url!=null)
			{this.url = url;}
		else
			{this.url = SoapAction.url;}
		
		this.showdialog = showdialog;
		this.methodName = methodName;
		this.rootName = rootName;
		this.parameter = parameter;
		this.handler = handler;
		init(context,nameSpace,url,methodName,rootName,parameter,handler,showdialog,showErrorDialog);
	}
	
	/**
	 * 初始化数据
	 */
	public void init(final Context context,String nameSpace,String url,String methodName,String rootName,SoapObject parameter,final MyHandler handler,final boolean showdialog,final boolean showErrorDialog)
	{		
		
		//实例化内部Handler
		mHandler = new MyHandler() {
				@Override
				public void success(Message msg) {
					//成功，滚动框消失
					if(showdialog)
					{
						stopProgressDialog();
					}
				}
				@Override
				public void failed(Message msg) {
					//滚动框消失
					if(showdialog)
					{
						if(!((Activity)context).isFinishing())
						{
							stopProgressDialog();
							
							Message ms = new Message();
							ms.what = MyHandler.MSG_FAILED;
							Bundle bundle = new Bundle();
							bundle.putString("info", msg.getData().getString("info"));
							ms.setData(bundle);
							handler.sendMessage(ms);
							//弹窗提示用户服务器操作失败
							final MineAlert alert = new MineAlert(context);
							alert.createAlertTwoButton(false,
									R.drawable.dialog_icon,
									context.getString(R.string.prompt),
									msg.getData().getString("info"), 
									context.getString(R.string.try_again),
									context.getString(R.string.cancel), 
									new View.OnClickListener() {										
										@Override
										public void onClick(View v) {
											alert.dimiss();
											//点击确定按钮重新操作    
                                            Excute();
										}
									}, new View.OnClickListener() {										
										@Override
										public void onClick(View v) {
											alert.dimiss();
										}
									});
						}						
					}
					if(showErrorDialog)
					{
						if(!((Activity)context).isFinishing())
						{
							Message ms = new Message();
							ms.what = MyHandler.MSG_FAILED;
							Bundle bundle = new Bundle();
							bundle.putString("info", msg.getData().getString("info"));
							ms.setData(bundle);
							handler.sendMessage(ms);
							//弹窗提示用户服务器操作失败
							
							final MineAlert alert = new MineAlert(context);
							alert.createAlertTwoButton(false,
									R.drawable.dialog_icon,
									context.getString(R.string.prompt),
									msg.getData().getString("info"), 
									context.getString(R.string.try_again),
									context.getString(R.string.cancel), 
									new View.OnClickListener() {										
										@Override
										public void onClick(View v) {
											alert.dimiss();
											Excute();
											
										}
									}, new View.OnClickListener() {
										@Override
										public void onClick(View v) {
											alert.dimiss();
										}
									});
						}
					}
					
				}
			};
			
			Excute();
	}
	
	/**
	 * 与服务器进行交互处理
	 * @return 
	 */
	public void Excute()
	{
		if(showdialog)
		{
			if(!((Activity)context).isFinishing())
			{
				startProgressDialog();
			}
		}
		
		//启动线程连接服务器进行处理
		new Thread() {
			public void run() {
				
				//SoapObject soapObject = new SoapObject(nameSpace, methodName);
				
				
				
				//放入参数
				//soapObject.addProperty(rootName, parameter);
			
				
				Log.e("request:", parameter.toString());
				
				
								
				//设置超时时间为60秒
				int timeout = AppConfig.soapTimeout;
				//int timeout = 1;
				//采用自己重写的类，系统的HttpTransportSE类无法设置超时时间
				//MyAndroidHttpTransport  ht = new MyAndroidHttpTransport (url, timeout);				
				HttpTransportSE   ht = new HttpTransportSE (url, timeout);
				
				//soap版本号  一般用1.1就够
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				//设置请求的SoapObject对象
				envelope.bodyOut = parameter;
				//implicitTypes  这句很重要，否则会出错
				envelope.implicitTypes = true;
				//服务为.net平台的
				envelope.dotNet = true;
				envelope.setOutputSoapObject(parameter);
				
				
				try {		
					//与服务器交互
					ht.call(nameSpace + methodName, envelope);
					
					//处理后获得的SoapObject对象
					SoapObject result = (SoapObject) envelope.bodyIn;
					
					
					Log.e("response:", result.toString());
					
					
												
					setServiceBackSoapObject(result);
					
						
					
					//------------------------------------------------
					mHandler.sendEmptyMessage(MyHandler.MSG_SUCCESS);
					
					handler.sendEmptyMessage(MyHandler.MSG_SUCCESS);
					
					
				}
				catch (IOException e) {
					//连接超时
					//Log.e("soapMgr.java getResultSoapObject() SocketException:",e.getMessage());
					//---------------------------------------
					Message msg = new Message();
					msg.what = MyHandler.MSG_FAILED;
					Bundle bundle = new Bundle();
					bundle.putString("info", context.getString(R.string.service_back_time_out));
					msg.setData(bundle);
					mHandler.sendMessage(msg);
				}
				catch (Exception e) {
					//其他未知错误
					Log.e( "soapMgr.java getResultSoapObject() Exception:",e.getMessage());					
					//---------------------------------------
					Message msg = new Message();
					msg.what = MyHandler.MSG_FAILED;
					Bundle bundle = new Bundle();
					bundle.putString("info", context.getString(R.string.service_back_error));
					msg.setData(bundle);
					mHandler.sendMessage(msg);
				}				
				
			}
		}.start();
		
	}
	
	/**
	 * 获取服务器处理后返回的SoapObject对象数据，用于MyHandler里回调的时候获取，不在回调中获取则获取出来为空对象
	 * @return   SoapObject对象数据
	 */
	public SoapObject getServiceBackSoapObject() {
		return serviceBackSoapObject;
	}

	/**
	 * 设置服务器处理后返回的SoapObject对象数据
	 * @param serviceBackSoapObject   serviceBackSoapObject
	 */
	public void setServiceBackSoapObject(SoapObject serviceBackSoapObject) {
		this.serviceBackSoapObject = serviceBackSoapObject;
	}
	
	
	private void startProgressDialog(){
        if (progressDialog == null){
            progressDialog = LoadingProgressDialog.createDialog(context);
            progressDialog.setMessage(context.getString(R.string.process_dialog_message));
            //设置滚动条是否按返回键取消
    		progressDialog.setCancelable(false);	
        }
        try
        {
        	progressDialog.show();
        }
        catch(Exception ex)
        {
        	progressDialog = null;
        }
        
    }
	
    private void stopProgressDialog(){
        if (progressDialog != null){
            
            try
            {
            	progressDialog.dismiss();
                progressDialog = null;
            }
            catch(Exception ex)
            {
            	progressDialog = null;
            }
        }
    }
    
}
