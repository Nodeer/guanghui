package com.guanghui.car.jingmicheck;

import java.io.File;
import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.guanghui.car.BuildTab;
import com.guanghui.car.CarCheckActivity;
import com.guanghui.car.CheckTaskPreviewActivity;
import com.guanghui.car.R;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;
import com.guanghui.car.config.AppPreference;
import com.guanghui.car.jingmicheck.fragment.BaseCheckFragment;
import com.guanghui.car.jingmicheck.fragment.GuJiaCheckFragment;
import com.guanghui.car.jingmicheck.fragment.NeiShiCheckFragment;
import com.guanghui.car.jingmicheck.fragment.WaiGuanCheckFragment;
import com.guanghui.car.jingmicheck.fragment.ZhuangZhiCheckFragment;
import com.guanghui.car.jingmicheck.model.DefectDepartModel;
import com.guanghui.car.jingmicheck.util.FileUtil;
import com.guanghui.car.jingmicheck.util.SavePhotoModelInterface;
import com.guanghui.car.jingmicheck.widget.DefectListItemView;
import com.guanghui.car.jingmicheck.widget.FragmentTabHost;
import com.guanghui.car.jingmicheck.widget.FragmentTabHost.CtripTabHostListener;

/**
 * 主页面
 */
public class JingMiCarCheckActivity extends FragmentActivity implements OnClickListener {

	private Context  context = this;
    public static final int TAKE_PHOTO = 1020;
    public static final int RE_TAKE_PHOTO = 1021;

    private Button mTakePhotoBtn;
    private String mPhotoFileName;
    private FragmentTabHost mTabHost;

    
    private MyHandler myhandler;
	private SoapMgr mgr;
	private SoapObject backSoapObject;
	
	//判断是否广汇认证车
    boolean isAuthCar = true;//默认是广汇认证车
    
    private Button mSaveBtn;
    private Button next_btn;
    private SavePhotoModelInterface mTakePhotoNameModel;
    private OnClickListener mOnTabClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tab_waiguan:
                    mTabHost.setCurrentTabByTag("waiguan");
                    break;
                case R.id.tab_neishi:
                    mTabHost.setCurrentTabByTag("neishi");

                    break;
                case R.id.tab_gujia:
                    mTabHost.setCurrentTabByTag("gujia");

                    break;
                case R.id.tab_zhuangzhi:
                    mTabHost.setCurrentTabByTag("zhuangzhi");

                    break;

                default:
                    break;
            }

        }
    };
    private CtripTabHostListener mCtripTabHostListener = new CtripTabHostListener() {

        @Override
        public void onTabClick(int postion, String tag) {
            System.out.println("onTabClick");
        }

        @Override
        public void onTabChange(int postion, String tag) {
            System.out.println("onTabChange");

        }
    };
    private SavePhotoModelInterface mReTakePhotoModel;
    private ImageView mShowPhotoImgView;
    
    
    
    private boolean edit;
    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_check_main_layout);
        
        
        
        
        isAuthCar = true;//默认是广汇认证车
        
        new BuildTab(JingMiCarCheckActivity.this,1) ;
        initTitle();
        
        mTabHost = (FragmentTabHost) findViewById(R.id.tabhost);
        mTabHost.setUp(this, getSupportFragmentManager());

        initTabs();
		
		

        

    }

    private void initTabs() {
    	try
    	{
    		mTabHost.addTab("waiguan", WaiGuanCheckFragment.class, null, R.id.tab_waiguan);
            mTabHost.addTab("neishi", NeiShiCheckFragment.class, null, R.id.tab_neishi);
            mTabHost.addTab("gujia", GuJiaCheckFragment.class, null, R.id.tab_gujia);
            mTabHost.addTab("zhuangzhi", ZhuangZhiCheckFragment.class, null, R.id.tab_zhuangzhi);
            mTabHost.setUp(this, getSupportFragmentManager());
            mTabHost.findViewById(R.id.tab_waiguan).setOnClickListener(mOnTabClickListener);
            mTabHost.findViewById(R.id.tab_neishi).setOnClickListener(mOnTabClickListener);
            mTabHost.findViewById(R.id.tab_gujia).setOnClickListener(mOnTabClickListener);
            mTabHost.findViewById(R.id.tab_zhuangzhi).setOnClickListener(mOnTabClickListener);
            mTabHost.findViewById(R.id.tab_waiguan).setSelected(true);
            mTabHost.setCtripTabHostListener(mCtripTabHostListener);
            
            
            
            mTabHost.setCurrentTabByTag("waiguan");
    	}
    	catch(Exception ex)
    	{
    		//Log.e("JingMiCarCheckActivity initTabs error:", ex.getMessage());
    	}
        
    }

    public void takePhote(Button btn, SavePhotoModelInterface model) {
        mTakePhotoBtn = btn;
        mTakePhotoNameModel = model;
        startActivityForResult(new Intent(this,CameraActivity.class),TAKE_PHOTO);
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        String sdCardPath = Environment.getExternalStorageDirectory().getPath();
//        File file = new File(sdCardPath + "/carcheck");
//        if (!file.exists()) {
//            file.mkdirs();// 创建文件夹
//        }
//        mPhotoFileName = file.getAbsolutePath() + "/img" + System.currentTimeMillis() + ".jpg";
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mPhotoFileName)));
//        startActivityForResult(intent, TAKE_PHOTO);
    }

    public void showPhoto(SavePhotoModelInterface photoModel) {
        final PopupWindow window = new PopupWindow(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setFocusable(true);
        View view = getLayoutInflater().inflate(R.layout.imgpop_layout, null);
        window.setContentView(view);
        mShowPhotoImgView = (ImageView) view.findViewById(R.id.img);
        mShowPhotoImgView.setOnClickListener(this);
        Button retake = (Button) view.findViewById(R.id.retake_photo);
        retake.setOnClickListener(this);
        retake.setTag(photoModel);

        byte[] byts = Base64.decode(photoModel.getImgPath(),Base64.DEFAULT);
        mShowPhotoImgView.setImageBitmap(BitmapFactory.decodeByteArray(byts,0,byts.length));

//        mShowPhotoImgView.setImageURI(Uri.fromFile(new File(photoModel.getImgPath())));
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        window.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        
        
        
        	 if (resultCode == Activity.RESULT_OK) {
                 if (requestCode == TAKE_PHOTO) {
                     mTakePhotoBtn.setVisibility(View.VISIBLE);
//                     mTakePhotoNameModel.setImgPath(data.getStringExtra("path"));
                     mTakePhotoNameModel.setImgPath(data.getStringExtra("img"));
                     mTakePhotoBtn = null;
                     mTakePhotoNameModel = null;
                     mPhotoFileName = null;
                 } else if (requestCode == RE_TAKE_PHOTO) {
                     mReTakePhotoModel.setImgPath(data.getStringExtra("img"));
                     mShowPhotoImgView.setImageURI(Uri.fromFile(new File(mReTakePhotoModel.getImgPath())));
                     byte[] byts = Base64.decode(mReTakePhotoModel.getImgPath(), Base64.DEFAULT);
                     mShowPhotoImgView.setImageBitmap(BitmapFactory.decodeByteArray(byts,0,byts.length));
                 }

             }else{
            	 if(edit)
            	 {
            		 //
                	 Log.e("requestCode", requestCode+"");
                	 Log.e("resultCode", resultCode+"");
                	 
                	 int IsGHAuth = 1;
                	 if(this.isAuthCar == true)
                	 {
                		 IsGHAuth = 1;
                	 }
                	 else
                	 {
                		 IsGHAuth = 0;
                	 }
                	 
                	 CheckTaskPreviewActivity.IsGHAuth = IsGHAuth;
                	 
                     finish();
            	 }
            	
             }
        
        
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            BaseCheckFragment fra = (BaseCheckFragment) mTabHost.getCurrentTab().fragment;
            if (fra.checkDefect()) {
                if(fra.isEditting()){
                    fra.saveLastDefect();
                }
                return super.onKeyDown(keyCode, event);
            } else {
                Toast to = Toast.makeText(this, "缺陷没有选择完全.", Toast.LENGTH_SHORT);
                to.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL,0,150);
                to.show();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.retake_photo:
                reTakePhoto((SavePhotoModelInterface) v.getTag());
                break;
            case R.id.save_btn:
                saveDefectToJson();
                break;
            case R.id.next_btn:
            	SavePrecision();
                break;
                
        }
    }

    
    private void goAllDefectPage() {
    	
    	
    	if(edit)
    	{
    		Intent it = new Intent(this,CarCheckActivity.class);
            
            it.putExtra("IsGHAuth", isAuthCar==true?1:0);
            it.putExtra("edit", edit );
            
            startActivityForResult(it, 5);
    	}
    	else
    	{
    		Intent it = new Intent(this,CarCheckActivity.class);
            
            it.putExtra("IsGHAuth", isAuthCar==true?1:0);
            it.putExtra("edit", edit );
            
            startActivity(it);
    	}
    	
        
    }
    
    
    /**
     * 保存当前tab的检测项到json
     */
    private void saveDefectToJson() {
        BaseCheckFragment fra =  (BaseCheckFragment) mTabHost.getCurrentTab().fragment;
        ArrayList<DefectDepartModel> modelList = new ArrayList<DefectDepartModel>();
        int size = fra.defectModelList.size();
        for(int i=0;i<size;i++){
            DefectDepartModel model = fra.defectModelList.get(i);
            if(model.hasDefect){
                modelList.add(model);
            }
        }

        Gson gson = new Gson();

        System.out.println("列表转json开始:" + System.currentTimeMillis());
        String str = gson.toJson(modelList);
        System.out.println("列表转json结束:" + System.currentTimeMillis());
        FileUtil.printToNewFile(str, "jsonList.txt");

    }

    private void reTakePhoto(SavePhotoModelInterface tag) {
        mReTakePhotoModel = tag;
        startActivityForResult(new Intent(this,CameraActivity.class),RE_TAKE_PHOTO);
    }
    
    
    private void initTitle() {
		((TextView) findViewById(R.id.title_renwu_btn1))
				.setBackgroundDrawable(JingMiCarCheckActivity.this.getResources().getDrawable(
						R.drawable.title_bg));
		((TextView) findViewById(R.id.title_renwu_btn2))
				.setBackgroundDrawable(JingMiCarCheckActivity.this.getResources().getDrawable(
						R.drawable.title_bg));
		((TextView) findViewById(R.id.title_renwu_btn3))
				.setBackgroundDrawable(JingMiCarCheckActivity.this.getResources().getDrawable(
						R.drawable.bg));

	}    
    
    /**
   	 * 读取该型号的相关信息，初始化控件
   	 */
   	private void SavePrecision()
   	{
   	   ArrayList<DefectDepartModel> waiguanDefects = new ArrayList<DefectDepartModel>();
	   ArrayList<DefectDepartModel> neishiDefects = new ArrayList<DefectDepartModel>();
	   ArrayList<DefectDepartModel> gujiaDefects = new ArrayList<DefectDepartModel>();
	   ArrayList<DefectDepartModel> zhuangzhiDefects = new ArrayList<DefectDepartModel>();
	   for (int i = 0; i < 4; i++) {
	       FragmentTabHost.TabInfo tab = mTabHost.mTabs.get(i);
	       if (tab.fragment != null) {
	           BaseCheckFragment fra = (BaseCheckFragment) tab.fragment;
	           for (DefectDepartModel model : fra.defectModelList) {
	               if (model.hasDefect) {
	                   switch (i) {
	                       case 0:
	                           waiguanDefects.add(model);
	                           break;
	                       case 1:
	                           neishiDefects.add(model);
	                           break;
	                       case 2:
	                           gujiaDefects.add(model);
	                           break;
	                       case 3:
	                           zhuangzhiDefects.add(model);
	                           break;
	                   }
	               }
	           }
	       }
	   }
	   
	   Gson gson = new Gson();
       String AppearanceText = gson.toJson(waiguanDefects);
       String InteriorText = gson.toJson(neishiDefects);
       String FrameworkText = gson.toJson(gujiaDefects);
       String InstallationText = gson.toJson(zhuangzhiDefects);
       
       if(zhuangzhiDefects != null && gujiaDefects != null)
       {
    	   if(zhuangzhiDefects.size() ==0 && gujiaDefects.size() ==0)
    	   {
    		   isAuthCar = true;
    	   }
       }
       //骨架里面有认证检测项
       for(DefectDepartModel model:gujiaDefects){
    	   Log.e("gujia1", "gujia");
           if(!model.isHasAuthDefect()){
        	   Log.e("gujia2", "gujia");
               isAuthCar = false;
           }
           else
           {
        	   Log.e("gujia3", "gujia");
           }
       }
       //装置里面有认证检测项
       for(DefectDepartModel model:zhuangzhiDefects){
    	   Log.e("zhuangzhi1", "zhuangzhi");
           if(!model.isHasAuthDefect()){
        	   Log.e("zhuangzhi2", "zhuangzhi");
               isAuthCar = false;
           }
           else
           {
        	   Log.e("zhuangzhi3", "zhuangzhi");
           }
       }
       
   	 
   		final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "SavePrecision");
   		soapParameter.addProperty("ResourceID", new AppPreference(context).getTaskId());
   		soapParameter.addProperty("AppearanceText", AppearanceText);
   		soapParameter.addProperty("InteriorText", InteriorText);
   		soapParameter.addProperty("FrameworkText",  FrameworkText);
   		soapParameter.addProperty("InstallationText",InstallationText);
   		soapParameter.addProperty("IsGHAuth", isAuthCar==true?1:0);
   		
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
   						goAllDefectPage();
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
   						SoapAction.SavePrecision , 
   						"SavePrecision",
   						soapParameter,
   						myhandler,
   						true,
   						false
   				);
   	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		
		edit = this.getIntent().getBooleanExtra("edit", false);
        
        new BuildTab(JingMiCarCheckActivity.this,1) ;
        initTitle();
        
        Constans.initLayoutParams();
        mSaveBtn = (Button) findViewById(R.id.save_btn);
        mSaveBtn.setOnClickListener(this);
        
        
        next_btn = (Button) findViewById(R.id.next_btn);
        next_btn.setOnClickListener(this);
        
        
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constans.sScreenWidth = dm.widthPixels;	
	} 
    
   	
   	
   	
   	
   	
    
    
}
