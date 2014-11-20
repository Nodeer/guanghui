package com.guanghui.car.ui;

import com.guanghui.car.R;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;


public class DetailADialog {

	private AlertDialog dialog;
	
	private Button cancel;
	public DetailADialog(Context context) {
		super();
		// TODO Auto-generated constructor stub
		  dialog = new AlertDialog.Builder(context).create() ;
	        dialog.show() ;
	        dialog.getWindow().setContentView(R.layout.car_check_detail_a) ;
	        dialog.getWindow().setGravity(Gravity.CENTER) ;
	        dialog.setCanceledOnTouchOutside(false);
	        cancel = (Button) dialog.findViewById(R.id.a_cancel);
	        cancel.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});
	}

}
