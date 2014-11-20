

package com.guanghui.car.adapter ;


import java.util.HashMap;
import java.util.List;

import com.guanghui.car.jingmicheck.Constans;
import org.ksoap2.serialization.SoapObject;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.guanghui.car.CheckTaskPreviewActivity2;
import com.guanghui.car.CheckTaskPreviewActivity3;
import com.guanghui.car.R;
import com.guanghui.car.R.drawable;
import com.guanghui.car.R.string;
import com.guanghui.car.ShouXuDengJiAcitivity;
import com.guanghui.car.TaskInfoActivity;
import com.guanghui.car.common.ToastUtilMgr;
import com.guanghui.car.communications.MineAlert;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;
import com.guanghui.car.config.AppPreference;
/**
 * 我的任务界面List适配器
 * @author 田大魁
 * */
public class MytaskAdapter extends BaseAdapter
{

    /**
     * 获取任务信息的tag
     * */
	public static final String TASKID="taskid";
    public static final String USERNAME="userName";
    public static final String TELEPHONE="telephone";
    public static final String AUTOTYPE="autoType";
    public static final String AUTOPLATE="autoPlate";
    public static final String OUTLET="outlet";
    public static final String AREA="area";
    public static final String MEETTIME="meetTime";
    public static final String VIN="vin";    
    public static final String FINISHTIME="finishTime";    
    public static final String STATE="state";
    public static final String USERID="UseID";
    private Context context ;

    private List<HashMap<String,String>> data ;

    private LayoutInflater layoutInflater ;
    private InitWidget initWidget = null ;
    
    private SoapMgr mgr2;
    
    public MytaskAdapter ( Context context , List<HashMap<String,String>> data )
    {

        this.context = context ;
        this.data = data ;
        
    }

    @ Override
    public int getCount()
    {
        return data.size() ;
    }

    @ Override
    public Object getItem(int position)
    {
        return data.get(position) ;
    }

    @ Override
    public long getItemId(int position)
    {
        return position ;
    }

    @ Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
    	this.layoutInflater = LayoutInflater.from(context) ;
    	
        OnClickListener clickListener = new OnClickListener()
        {
            
            @ Override
            public void onClick(View view)
            {
                switch ( view.getId() )
                {
                    case R.id.operate_textV :
                    	String butStr2 = initWidget.operate_textV.getText().toString();
                    	if(butStr2.equals("查看"))
                    	{
                    		Intent intent = new Intent(context,CheckTaskPreviewActivity2.class);
                            String taskId = data.get(position).get(TASKID);
                            String UseId=data.get(position).get(USERID);
                            Log.d("###","UseId:"+UseId);
                            AppPreference appPreference = new AppPreference(context);
                            appPreference.setTaskId(taskId);
                            intent.putExtra(TASKID, taskId);
                            context.startActivity(intent);
                    	}
                    	
                    	
                    	
                    	
                        break ;
                    case R.id.operate_button:
                        	
                            final String taskId = data.get(position).get(TASKID);
                        Constans.user_token = taskId;
                        String UseId = data.get(position).get(USERID);
                            final String state = data.get(position).get(STATE);
                            
                            
                            Intent intent;
                            
                            if(!state.equals("0"))
                            {
                            	//继续执行弹窗
                            	final MineAlert alert = new MineAlert(context);
    							alert.createAlertTwoButton(true,
    									R.drawable.dialog_icon,
    									context.getString(R.string.prompt),
    									"是否继续执行任务?", 
    									"继续任务",
    									"取消任务", 
    									new View.OnClickListener() {										
    										@Override
    										public void onClick(View v) {
    											alert.dimiss();
    											//继续任务
    											//FLAG值  如果为1 则跳转到 ShouXuDengJiAcitivity
    											//如果为2 则跳转到 ShouXuDengJiAcitivity
    											String flag = state;
    											
    											if(flag.equals("1"))
    											{
    												Intent intent = new Intent(context,ShouXuDengJiAcitivity.class);
        											intent.putExtra(TASKID, taskId);
        			                                intent.putExtra(STATE, state);
        			                                context.startActivity(intent);
    											}
    											if(flag.equals("2"))
    											{
    												Intent intent = new Intent(context,ShouXuDengJiAcitivity.class);
        											intent.putExtra(TASKID, taskId);
        			                                intent.putExtra(STATE, state);
        			                                context.startActivity(intent);
    											}
    											if(flag.equals("4"))
    											{
    												new AppPreference(context).setVIN(((String)data.get(position).get(VIN)));    
    												new AppPreference(context).setTaskId(taskId);
    												Intent intent = new Intent(context,CheckTaskPreviewActivity3.class);        			                                
        			                                context.startActivity(intent);
    											}
    											
    			                                
    										}
    									}, new View.OnClickListener() {										
    										@Override
    										public void onClick(View v) {
    											alert.dimiss();
    											//取消任务
    											final Dialog dialog = new Dialog(context,R.style.CustomDialogStyle);  
    											dialog.getWindow().setContentView(R.layout.canceldetection_dialog);
    											
    											final EditText edittext = ((EditText)dialog.findViewById(R.id.canceldetection_dialog_edit));
    											dialog.show();
    										
    											
    											
    											((Button)dialog.findViewById(R.id.canceldetection_dialog_btn_ok)).setOnClickListener(new View.OnClickListener() {
													
													@Override
													public void onClick(View v) {
														dialog.dismiss();
														
														
														
														SetResourceStatus(taskId,edittext.getText().toString().trim());
													}
												});
    											
    										}
    									});
                            	
                            	
                            	
                            }
                            else
                            {
                            	intent = new Intent(context,TaskInfoActivity.class);
                            	intent.putExtra(TASKID, taskId);
                                intent.putExtra(STATE, state);
                                context.startActivity(intent);
                            }
                            
                            Log.d("###","UseId:"+UseId);
                            AppPreference appPreference = new AppPreference(context);
                            appPreference.setTaskId(taskId);
                            
                            
                        
                        
                        break;
                    default :
                        break ;
                }
                
            }
        };

            initWidget = new InitWidget() ;
            convertView = layoutInflater.inflate(R.layout.mytask_list_item, null) ;
            initWidget.taskId = ( TextView ) convertView.findViewById(R.id.taskId_textV) ;
            initWidget.userName = ( TextView ) convertView.findViewById(R.id.username_textV) ;
            initWidget.telephone = ( TextView ) convertView.findViewById(R.id.telephone_textV) ;
            initWidget.autoType = ( TextView ) convertView.findViewById(R.id.auto_type_textV) ;
            initWidget.autoPlate = ( TextView ) convertView.findViewById(R.id.auto_plate_textV) ;
            initWidget.outlet = ( TextView ) convertView.findViewById(R.id.outlet_textV) ;
            initWidget.area = ( TextView ) convertView.findViewById(R.id.area_textV) ;
            initWidget.meetTime = ( TextView ) convertView.findViewById(R.id.meetTime_textV) ;
            initWidget.state = ( TextView ) convertView.findViewById(R.id.state_textV) ;
            initWidget.operate_textV = ( TextView ) convertView.findViewById(R.id.operate_textV) ;
            initWidget.operate_textV.setOnClickListener(clickListener);
            initWidget.area_textjiance_time = ( TextView ) convertView.findViewById(R.id.area_textjiance_time) ;
            
            initWidget.operate_but = ( Button ) convertView.findViewById(R.id.operate_button) ;
            initWidget.operate_but.setOnClickListener(clickListener);
            
            
            
            convertView.setTag(initWidget) ;
            

        // 绑定数据
        initWidget.taskId.setText(((String)data.get(position).get(TASKID))) ;
        initWidget.userName.setText(((String)data.get(position).get(USERNAME)));
        initWidget.telephone.setText(((String)data.get(position).get(TELEPHONE)));
        initWidget.autoType.setText(((String)data.get(position).get(AUTOTYPE)));
        initWidget.autoPlate.setText(((String)data.get(position).get(AUTOPLATE)));
        initWidget.outlet.setText(((String)data.get(position).get(OUTLET)));
        initWidget.area.setText(((String)data.get(position).get(AREA)));
        initWidget.meetTime.setText(((String)data.get(position).get(MEETTIME)));
        initWidget.area_textjiance_time.setText(((String)data.get(position).get(FINISHTIME)));
        stateTagJudge(data.get(position).get(STATE).toString(),initWidget.state,initWidget.operate_textV,initWidget.operate_but);
        return convertView ;
    }
    
    /**
     * 根据返回的状态码进行UI不同的操作类
     * 
     * 
     * */
    private void  stateTagJudge(String stateTag,TextView stateText,TextView poerateText,Button operateBut){
        if(stateTag.equals("0")){
            //状态栏   未接收
        	stateText.setText(string.unreceived);
        	//操作栏按钮 接收任务
        	poerateText.setVisibility(View.GONE);
        	operateBut.setVisibility(View.VISIBLE);
        	operateBut.setText(string.receive_task);
        	operateBut.setBackgroundResource(drawable.btn_mytask_operate_receive);
        	
        }else if(stateTag.equals("1")){
            //状态栏      已接收   未检测
        	stateText.setText(string.unchecked);
        	//操作栏按钮 继续执行
        	poerateText.setVisibility(View.GONE);
            operateBut.setVisibility(View.VISIBLE);
            operateBut.setText(string.continue_task);
            operateBut.setBackgroundResource(drawable.btn_style_one);
        }else if(stateTag.equals("2")){
            //已接收   正在检测
        	stateText.setText(string.checking);
        	//操作栏按钮 继续执行
        	poerateText.setVisibility(View.GONE);
            operateBut.setVisibility(View.VISIBLE);
            operateBut.setText(string.continue_task);
            operateBut.setBackgroundResource(drawable.btn_style_one);
        }else if(stateTag.equals("3")){
            //已接收   检测完毕
        	stateText.setText(string.checked);
        	//操作栏
        	operateBut.setVisibility(View.GONE);
        	poerateText.setVisibility(View.VISIBLE);
        	poerateText.setText(string.look_up);
        	poerateText.setTextColor(Color.RED);
        }else if(stateTag.equals("4")){
            //待定价
        	stateText.setText(string.checkdingjia);
        	//操作栏
        	poerateText.setVisibility(View.GONE);
            operateBut.setVisibility(View.VISIBLE);
            operateBut.setText(string.continue_task);
            operateBut.setBackgroundResource(drawable.btn_style_one);
        }
    }
    public final class InitWidget
    {
        public TextView taskId , userName , telephone , autoType , autoPlate , outlet , area , meetTime , state , operate_textV ,area_textjiance_time;
        public Button operate_but ;
    }
    
    
    
    
    /**
     * 更改任务状态
     */
    private void SetResourceStatus(String ID,String reason)
    {		
		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "CancelDetection");
		soapParameter.addProperty("ResourceID", ID);
		soapParameter.addProperty("Reason", reason);				
		MyHandler myhandler =  new MyHandler()
		{
			@Override
			public void success(Message msg) {
				SoapObject backSoapObject = mgr2.getServiceBackSoapObject();						
				if(backSoapObject!=null)
				{
					String Retcode = ((SoapObject)backSoapObject.getProperty(0)).getProperty("Retcode").toString();
					//0成功，1失败
					if(Retcode.equals("0"))
					{						
						//成功取消任务
						Intent intent = new Intent();
						   intent.setAction("com.guanghui.car.CancelDetection");
						   //发送 一个无序广播
						   context.sendBroadcast(intent);
						
					}
					else
					{						
						ToastUtilMgr.TextToast(context, context.getResources().getString(R.string.service_back_error), ToastUtilMgr.LENGTH_LONG);
					}
				}
			}
			@Override
			public void failed(Message msg) {				
				
			}
		};
		mgr2 = new SoapMgr(context,
						null, 
						null, 
						SoapAction.CancelDetection , 
						"CancelDetection",
						soapParameter,
						myhandler,
						true,
						false
				);
    }
}
