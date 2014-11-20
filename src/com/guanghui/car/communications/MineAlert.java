package com.guanghui.car.communications;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.guanghui.car.R;

/**
 * 自定义的 AlertDialog 工具类
 * @author 张云
 *
 */
public class MineAlert {
		private AlertDialog.Builder builder;
		private String posBtntxt,negBtntxt;
		private Context context;
		private AlertDialog dialog;
		private View view;
		
		/**
		 * 构造函数
		 * @param context Context对象
		 */
		public MineAlert(Context context){
			this.context=context;
			onCreateBuilder(context);
		}
		
		/**
		 * 建立Builder
		 * @param context Context对象
		 */
		private void onCreateBuilder(Context context){
			if(builder==null){
				builder=new AlertDialog.Builder(context);
			}
		}
		
		/**
		 * 初始化按钮文本
		 * @param PosButtonText  确定文本
		 * @param NegButtonText  取消文本
		 */
		private void initBtn(String PosButtonText,String NegButtonText){
			if("".equals(PosButtonText)){
				//确定
				posBtntxt= context.getResources().getString(R.string.ok);
			}else{
				posBtntxt=PosButtonText;
			}
			if("".equals(NegButtonText)){
				//取消
				negBtntxt= context.getResources().getString(R.string.cancel);
			}else{
				negBtntxt=NegButtonText;
			}
		}
		
		/**
		 * 初始化Icon
		 * @param icon   资源文件中的icon  int值
		 */
		private void initIcon(int icon){
			if(icon>0){
				builder.setIcon(icon);
			}
		}
		
		/**
		 * 初始化监听
		 * @param PosButtonText  确定文本
		 * @param NegButtonText  取消文本
		 * @param posListener    确定事件
		 * @param negListener    取消事件
		 */
		private void initListener(String PosButtonText,String NegButtonText,DialogInterface.OnClickListener posListener,
						DialogInterface.OnClickListener negListener){
			initBtn(PosButtonText,NegButtonText);
			if(posBtntxt!=null){
				builder.setPositiveButton(posBtntxt, posListener);
			}
			if(negBtntxt!=null){
				builder.setNegativeButton(negBtntxt,negListener);
			}
		}
		
		/**
		 * 初始化消息内容
		 * @param message    消息内容
		 */
		private void initMessage(String message){
			if(message!=null && !"".equals(message)){
				builder.setMessage(message);
			}
		}
		
		/**
		 * 初始化标题内容
		 * @param title    标题内容
		 */
		private void initTitle(String title) {
			// TODO Auto-generated method stub
			if(title!=null && !"".equals(title)){
				builder.setTitle(title);
			}
		}	
		
		/**创建Dialog
		 * @param title		标题
		 * @param message		显示的主题内容
		 * @param icon	(int)图标资源
		 * @param PosButtonText  确定按钮文字，不设置默认为 “确定”
		 * @param NegButtonText	取消按钮文字，不设置默认为  "取消"
		 * @param posListener		确定按钮响应事件， 如果没有 “确定” 按钮，则将此参数设置为：null
		 * @param negListener		取消按钮相应事件， 如果没有 “取消” 按钮，则将此参数设置为：null 
		 */
		public  void createAlert(String title,String message,int icon,
				String PosButtonText,String NegButtonText,
				DialogInterface.OnClickListener posListener, DialogInterface.OnClickListener negListener){
				initBtn(PosButtonText,NegButtonText);
				initTitle(title);
				initIcon(icon);
				initMessage(message);
				initListener(PosButtonText,NegButtonText,posListener,negListener);
		}
		
		
		/**
		 * 显示自定义的对话框
		 * @param Cancelable
		 * @param icon
		 * @param title
		 * @param message
		 * @param posButtonText
		 * @param negButtonText
		 * @param posListener
		 * @param negListener
		 * @return
		 */
		public AlertDialog createAlertTwoButton(boolean Cancelable,int icon,String title,String message,String posButtonText,String negButtonText,
				View.OnClickListener posListener,View.OnClickListener negListener){
				dialog = new AlertDialog.Builder(context).create();
				dialog.show();
				dialog.getWindow().setContentView(R.layout.dialog_two_button);
				dialog.setCancelable(Cancelable);
				if(icon>0)
				{
					((ImageView)dialog.findViewById(R.id.dialog_two_icon)).setBackgroundResource(icon);
				}				
				((TextView)dialog.findViewById(R.id.dialog_one_button_title)).setText(title);
				((TextView)dialog.findViewById(R.id.dialog_one_button_message)).setText(message);
				((Button)dialog.findViewById(R.id.dialog_two_button_ok)).setText(posButtonText);
				((Button)dialog.findViewById(R.id.dialog_two_button_cancel)).setText(negButtonText);		
				((Button)dialog.findViewById(R.id.dialog_two_button_ok)).setOnClickListener(posListener);
				((Button)dialog.findViewById(R.id.dialog_two_button_cancel)).setOnClickListener(negListener);				
				return dialog;
		}
		
		/**
		 * 显示自定义的对话框（确定,取消两个按钮）
		 * @param Cancelable
		 * @param message
		 * @param posListener
		 * @param negListener
		 */
		public void createAlertTwoButton(boolean Cancelable,String message,View.OnClickListener posListener,View.OnClickListener negListener){
				dialog = new AlertDialog.Builder(context).create();
				dialog.show();
				dialog.getWindow().setContentView(R.layout.dialog_two_button);
				dialog.setCancelable(Cancelable);
				((TextView)dialog.findViewById(R.id.dialog_one_button_message)).setText(message);
				((Button)dialog.findViewById(R.id.dialog_two_button_ok)).setOnClickListener(posListener);
				((Button)dialog.findViewById(R.id.dialog_two_button_cancel)).setOnClickListener(negListener);
		}
		
		
		/**
		 * 显示自定义的对话框
		 * @param Cancelable
		 * @param icon
		 * @param title
		 * @param message
		 * @param posButtonText
		 * @param listener
		 * @return
		 */
		public AlertDialog createAlertOneButton(boolean Cancelable,int icon,String title,String message,String posButtonText,
				View.OnClickListener listener){
				dialog = new AlertDialog.Builder(context).create();
				dialog.show();
				dialog.getWindow().setContentView(R.layout.dialog_two_button);
				dialog.setCancelable(Cancelable);
				if(icon>0)
				{
					((ImageView)dialog.findViewById(R.id.dialog_one_icon)).setBackgroundResource(icon);
				}	
				((TextView)dialog.findViewById(R.id.dialog_one_button_title)).setText(title);
				((TextView)dialog.findViewById(R.id.dialog_one_button_message)).setText(message);
				((Button)dialog.findViewById(R.id.dialog_two_button_ok)).setText(posButtonText);		
				((Button)dialog.findViewById(R.id.dialog_two_button_ok)).setOnClickListener(listener);				
				return dialog;
		}
		
		
		
		/**
		 * 显示自定义的对话框（确定一个按钮）
		 * @param Cancelable
		 * @param message
		 * @param listener
		 */
		public void createAlertOneButton(boolean Cancelable,String message,View.OnClickListener listener){
				dialog = new AlertDialog.Builder(context).create();
				dialog.show();
				dialog.getWindow().setContentView(R.layout.dialog_one_button);
				dialog.setCancelable(Cancelable);
				((TextView)dialog.findViewById(R.id.dialog_one_button_message)).setText(message);
				((Button)dialog.findViewById(R.id.dialog_one_button_ok)).setOnClickListener(listener);
		}
		
		/**
		 * 显示
		 */
		public void show(){
			builder.show();
		}
		
		
		/**
		 * 隐藏
		 */
		public void dimiss(){
			dialog.dismiss();
		}
		
}
