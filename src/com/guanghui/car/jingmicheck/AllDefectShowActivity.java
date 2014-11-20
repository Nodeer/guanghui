package com.guanghui.car.jingmicheck;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guanghui.car.R;
import com.guanghui.car.communications.MineAlert;
import com.guanghui.car.jingmicheck.model.DefectDepartModel;
import com.guanghui.car.jingmicheck.widget.DefectListItemView;
import com.guanghui.car.staticdata.JingMiCheckData;

/**
 * Created by Administrator on 14-5-6.
 */
public class AllDefectShowActivity extends Activity {
	private Context context = this;
    public LinearLayout mWaiguanShowListView,mNeishiShowListView,mGujiaListView,mZhuangzhiShowListView;
    private TextView authCarTextView;
    private Button btn_close;
    
    
    private String waiguan = JingMiCheckData.waiguan;
    private String neishi = JingMiCheckData.neishi;
    private String gujia = JingMiCheckData.gujia;
    private String zhuangzhi = JingMiCheckData.zhuangzhi;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_defect_layout);
        mWaiguanShowListView = (LinearLayout) findViewById(R.id.waiguan_show_layout);
        mNeishiShowListView = (LinearLayout) findViewById(R.id.neishi_show_layout);
        mGujiaListView = (LinearLayout) findViewById(R.id.gujia_show_layout);
        mZhuangzhiShowListView = (LinearLayout) findViewById(R.id.zhuangzhi_show_layout);
        authCarTextView = (TextView) findViewById(R.id.isAuthCar);
        btn_close = (Button)findViewById(R.id.btn_close);
        Gson gson = new Gson();

        btn_close.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AllDefectShowActivity.this.finish();
			}
		});
        
        try
        {
        	ArrayList<DefectDepartModel> waiguanDefects = (ArrayList<DefectDepartModel>) gson.fromJson(waiguan, new TypeToken<ArrayList<DefectDepartModel>>(){}.getType());
            ArrayList<DefectDepartModel> neishiDefects = (ArrayList<DefectDepartModel>) gson.fromJson(neishi, new TypeToken<ArrayList<DefectDepartModel>>(){}.getType());
            ArrayList<DefectDepartModel> gujiaDefects = (ArrayList<DefectDepartModel>) gson.fromJson(gujia, new TypeToken<ArrayList<DefectDepartModel>>(){}.getType());
            ArrayList<DefectDepartModel> zhuangzhiDefects = (ArrayList<DefectDepartModel>) gson.fromJson(zhuangzhi, new TypeToken<ArrayList<DefectDepartModel>>(){}.getType());
            boolean isAuthCar = true;//默认是广汇认证车
            for(DefectDepartModel model:waiguanDefects){
                mWaiguanShowListView.addView(new DefectListItemView(this,model));
            }
            for(DefectDepartModel model:neishiDefects){
                mNeishiShowListView.addView(new DefectListItemView(this,model));
            }
            //骨架里面有认证检测项
            for(DefectDepartModel model:gujiaDefects){
//                if(isAuthCar&&model.isHasAuthDefect()){
//                    isAuthCar = false;
//                }
                mGujiaListView.addView(new DefectListItemView(this,model));
            }
            //装置里面有认证检测项
            for(DefectDepartModel model:zhuangzhiDefects){
//                if(isAuthCar&&model.isHasAuthDefect()){
//                    isAuthCar = false;
//                }
                mZhuangzhiShowListView.addView(new DefectListItemView(this,model));
            }

//            if(isAuthCar){
//                authCarTextView.setText("广汇认证车:是");
//            }else{
//                authCarTextView.setText("广汇认证车:否");
//            }
            
            
            authCarTextView.setText("广汇认证车:"+getIntent().getStringExtra("renzheng"));
            
            
        }
        catch(Exception ex)
        {
        	AllDefectShowActivity.this.setContentView(R.layout.exit_activity);
        	final MineAlert alert = new MineAlert(context);
			alert.createAlertOneButton(false,"精密检测数据解析异常", 
					new View.OnClickListener() {								
						@Override
						public void onClick(View v) {
							alert.dimiss();							
							AllDefectShowActivity.this.finish();
						}
					});
        }
        
        
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		JingMiCheckData.clear();
		
	}
    
    
    
    
    
    
}
