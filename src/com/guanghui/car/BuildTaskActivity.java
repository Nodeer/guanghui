

package com.guanghui.car ;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.guanghui.car.adapter.RegionsAdapter;
import com.guanghui.car.adapter.RegionsInfo;
import com.guanghui.car.adapter.ShopsAdapter;
import com.guanghui.car.adapter.ShopsInfo;
import com.guanghui.car.common.DateTime;
import com.guanghui.car.common.ToastUtilMgr;
import com.guanghui.car.communications.MineAlert;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;
import com.guanghui.car.config.AppPreference;
import com.guanghui.car.ui.AllCapTransformationMethod;
import com.guanghui.car.ui.DateTimePickDialogUtil;

/**
 * 新建任务界面
 * 
 * */
public class BuildTaskActivity extends Activity
{

    private Context mContext = BuildTaskActivity.this ;
    
    private Button back_btn;

    private static final String tag = "BuildTaskActivity" ;

    private RadioButton buidtask_radiogroup1_radio1,buidtask_radiogroup1_radio2,buidtask_radiogroup1_radio3,buidtask_radiogroup1_radio4,buidtask_radiogroup1_radio5,buidtask_radiogroup1_radio6;
    private EditText buidtask_radiogroup1_radio7;

    private EditText saler_editT , auto_type_editT , chepai_editT , customer_name_editT , telephone_editT , meetTime_editT , checkPlace_editT ,vin_editT;

    private Spinner bumen_spinner,district_spinner , outlet_spinner ,yixiang_spinner ;

    private Button submit_btn ;
    private EditText dengjiren;
    
    private EditText jiancequyu_editT,jiancemendian_editT,jianceren_editT;

    @ Override
    protected void onCreate(Bundle savedInstanceState)
    {

        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_buildtask) ;
        new BuildTab(mContext,1) ;
        init() ;
        
        vin_editT.setVisibility(View.GONE);
        
        
        dengjiren.setText( new AppPreference(mContext).getLoginUserName());
    }

    private void init()
    {
    	jiancequyu_editT = ( EditText ) findViewById(R.id.jiancequyu_editT) ;
    	jiancemendian_editT = ( EditText ) findViewById(R.id.jiancemendian_editT) ;
    	jianceren_editT = ( EditText ) findViewById(R.id.jianceren_editT) ;
    	
    	
    	
    	jiancequyu_editT.setText(new AppPreference(mContext).getRegionName());
    	jiancemendian_editT.setText(new AppPreference(mContext).getShopName());
    	jianceren_editT.setText(new AppPreference(mContext).getLoginUserName());
    	
    	
        saler_editT = ( EditText ) findViewById(R.id.saler_editT) ;
        auto_type_editT = ( EditText ) findViewById(R.id.auto_type_editT) ;
        chepai_editT = ( EditText ) findViewById(R.id.chepai_editT) ;
        vin_editT = ( EditText ) findViewById(R.id.vin_editT) ;
        chepai_editT.setTransformationMethod(new AllCapTransformationMethod());
        dengjiren = ( EditText ) findViewById(R.id.dengjiren) ;
        buidtask_radiogroup1_radio1 = (RadioButton)this.findViewById(R.id.buidtask_radiogroup1_radio1);
        buidtask_radiogroup1_radio2 = (RadioButton)this.findViewById(R.id.buidtask_radiogroup1_radio2);
        buidtask_radiogroup1_radio3 = (RadioButton)this.findViewById(R.id.buidtask_radiogroup1_radio3);
        buidtask_radiogroup1_radio4 = (RadioButton)this.findViewById(R.id.buidtask_radiogroup1_radio4);
        buidtask_radiogroup1_radio5 = (RadioButton)this.findViewById(R.id.buidtask_radiogroup1_radio5);
        buidtask_radiogroup1_radio6 = (RadioButton)this.findViewById(R.id.buidtask_radiogroup1_radio6);
        buidtask_radiogroup1_radio7 = (EditText)this.findViewById(R.id.buidtask_radiogroup1_radio7);
        
        customer_name_editT = ( EditText ) findViewById(R.id.customer_name_editT) ;
        telephone_editT = ( EditText ) findViewById(R.id.telephone_editT) ;
        meetTime_editT = ( EditText ) findViewById(R.id.meet_time_editT) ;
        meetTime_editT.setOnClickListener(onClickListener) ;
        checkPlace_editT = ( EditText ) findViewById(R.id.checkSpace_editT) ;
        bumen_spinner = ( Spinner ) findViewById(R.id.bumen_spinner) ;
        district_spinner = ( Spinner ) findViewById(R.id.district_spinner) ;
        outlet_spinner = ( Spinner ) findViewById(R.id.outlet_spinner) ;
        yixiang_spinner = ( Spinner ) findViewById(R.id.yixiang_spinner) ;
        submit_btn = ( Button ) findViewById(R.id.submit_btn) ;
        submit_btn.setOnClickListener(onClickListener) ;
        back_btn = (Button) findViewById(R.id.activity_mytasks_btn_shuaxin) ;
        back_btn.setOnClickListener(onClickListener) ;
        
        
        List<String> adapterStringList1 = new ArrayList<String>();		
        adapterStringList1.add("单卖");
        adapterStringList1.add("以旧换旧");
        adapterStringList1.add("以旧换新");
        ArrayAdapter yixiangadapter = new ArrayAdapter(mContext,R.layout.spinner_item,adapterStringList1);
        yixiangadapter.setDropDownViewResource(R.layout.spinner_item);
        yixiang_spinner.setAdapter(yixiangadapter);
        
        List<String> adapterStringList2 = new ArrayList<String>();		
        adapterStringList2.add("二手车部");
        adapterStringList2.add("销售部");
        adapterStringList2.add("售后部");
        adapterStringList2.add("其他部门");
        ArrayAdapter yixiangadapter2 = new ArrayAdapter(mContext,R.layout.spinner_item,adapterStringList2);
        yixiangadapter2.setDropDownViewResource(R.layout.spinner_item);
        bumen_spinner.setAdapter(yixiangadapter2);
        
        
        getRegions();
        
        
        
        
        
        setjiaodian();
        
        
        meetTime_editT.setText(DateTime.getNowDate2());
        checkPlace_editT.setText(new AppPreference(mContext).getShopName());
        
    }

    OnClickListener onClickListener = new View.OnClickListener()
    {

        @ Override
        public void onClick(View view)
        {

            switch ( view.getId() )
            {
                case R.id.meet_time_editT :
                    DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(BuildTaskActivity.this,meetTime_editT,true) ;
                    break ;
                case R.id.submit_btn :
                    submitNewTask() ;
                    break ;                    
                case R.id.activity_mytasks_btn_shuaxin:
                	finish();
                	break;
                default :
                    break ;
            }
        }
    } ;

    

    OnItemSelectedListener onDistrictSelectedListener = new OnItemSelectedListener()
    {

        @ Override
        public void onItemSelected(AdapterView < ? > parent, View view, int position, long id)
        {

            Log.d(tag, "District监听被触发") ;
            String selectId = regionsList.get(position).getId() ;
            Log.d(tag, "选择了：" + regionsList.get(position).getName()) ;
            List < ShopsInfo > list = new ArrayList < ShopsInfo >() ;
            for ( int i = 0 ; i < shopsList.size() ; i++ )
            {
                if (shopsList.get(i).getRegionId().equals(selectId))
                {
                    list.add(shopsList.get(i)) ;
                }
            }
            if (list.size() > 0)
            {
                ShopsAdapter shopsAdapter = new ShopsAdapter(mContext, list) ;                

                outlet_spinner.setAdapter(shopsAdapter) ;
            }
            else
            {
                ShopsAdapter shopsAdapter = new ShopsAdapter(mContext, new ArrayList<ShopsInfo>()) ;
                outlet_spinner.setAdapter(shopsAdapter) ;
            }
        }

        @ Override
        public void onNothingSelected(AdapterView < ? > arg0)
        {
        }
    };
    


    /**
     * 获取区域列表
     * */
    private SoapObject regionsSoapParameter ;

    private SoapMgr regionsMgr ;


    private List < RegionsInfo > regionsList ;

    private void getRegions()
    {
        regionsSoapParameter = new SoapObject(SoapAction.nameSpace, SoapAction.GetRegionsList) ;
        MyHandler getRegionsHandler = new MyHandler()
        {

            @ Override
            public void success(Message msg)
            {

            	int cur = 0;
            	
                SoapObject backSoapObject = regionsMgr.getServiceBackSoapObject() ;
                String retcode = (( SoapObject ) backSoapObject.getProperty(0)).getProperty("Retcode").toString() ;
                // 0成功，1失败
                if (retcode.equals("0"))
                {
                    regionsList = new ArrayList < RegionsInfo >() ;
                    SoapObject items = (( SoapObject ) (( SoapObject ) backSoapObject.getProperty(0)).getProperty("Items")) ;
                    Log.d(tag, "item count:" + items.getPropertyCount()) ;
                    for ( int i = 0 ; i < items.getPropertyCount() ; i++ )
                    {
                        // 获得来源信息
                        String id = (( SoapObject ) items.getProperty(i)).getProperty("Id").toString() ;
                        String name = (( SoapObject ) items.getProperty(i)).getProperty("Name").toString() ;
                        String comments = (( SoapObject ) items.getProperty(i)).getProperty("Comments").toString() ;
                        RegionsInfo regionsInfo = new RegionsInfo() ;
                        regionsInfo.setId(id) ;
                        regionsInfo.setName(name) ;
                        regionsInfo.setComments(comments) ;
                        
                        
                        if(id.equals(new AppPreference(mContext).getRegionID()))
                        {
                        	cur = i;
                        }                        
                        regionsList.add(regionsInfo) ;
                    }
                    RegionsAdapter regionsAdapter = new RegionsAdapter(mContext, regionsList) ;
                    district_spinner.setAdapter(regionsAdapter) ;
                    
                    
                    district_spinner.setSelection(cur);
                    
                    
                    
                    district_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                    {

						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {
							getShops(regionsList.get(arg2).getId());
						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {							
						}
                    	
                    });
                    
                    
                    
                    
                }
                else
                {
                    ToastUtilMgr.TextToast(mContext, getString(R.string.server_error1), ToastUtilMgr.LENGTH_LONG) ;
                }
            }

            @ Override
            public void failed(Message msg)
            {
            	ToastUtilMgr.TextToast(mContext, "获取区域信息失败", ToastUtilMgr.LENGTH_LONG) ;
            }
        } ;
        regionsMgr = new SoapMgr(mContext, null, null, SoapAction.GetRegionsList, SoapAction.GetRegionsList, regionsSoapParameter, getRegionsHandler, true, false) ;
    	
    	
    }

    /**
     * 获取门店列表
     * */
    private SoapObject shopsSoapParameter ;

    private SoapMgr shopsMgr ;


    private List < ShopsInfo > shopsList ;

    private void getShops(String RegionID)
    {
    	//GetRegions
        shopsSoapParameter = new SoapObject(SoapAction.nameSpace, SoapAction.GetShopsList) ;
        // 获取全部门店列表
        shopsSoapParameter.addProperty("RegionID", RegionID) ;
        MyHandler getShopsHandler = new MyHandler()
        {
            @ Override
            public void success(Message msg)
            {
            	int cur = 0;
                SoapObject backSoapObject = shopsMgr.getServiceBackSoapObject() ;
                String retcode = (( SoapObject ) backSoapObject.getProperty(0)).getProperty("Retcode").toString() ;
                // 0成功，1失败
                if (retcode.equals("0"))
                {
                    shopsList = new ArrayList < ShopsInfo >() ;
                    SoapObject items = (( SoapObject ) (( SoapObject ) backSoapObject.getProperty(0)).getProperty("Items")) ;
                    Log.d(tag, "item count:" + items.getPropertyCount()) ;
                    for ( int i = 0 ; i < items.getPropertyCount() ; i++ )
                    {
                        // 获得门店信息
                        String id = (( SoapObject ) items.getProperty(i)).getProperty("Id").toString() ;
                        String regionId = (( SoapObject ) items.getProperty(i)).getProperty("RegionID").toString() ;
                        String regionName = (( SoapObject ) items.getProperty(i)).getProperty("RegionName").toString() ;
                        String name = (( SoapObject ) items.getProperty(i)).getProperty("Name").toString() ;
                        String comments = (( SoapObject ) items.getProperty(i)).getProperty("Comments").toString() ;

                        ShopsInfo shopsInfo = new ShopsInfo() ;
                        shopsInfo.setId(id) ;
                        shopsInfo.setRegionId(regionId) ;
                        shopsInfo.setRegionName(regionName) ;
                        shopsInfo.setName(name) ;
                        shopsInfo.setComments(comments) ;
                        if(id.equals(new AppPreference(mContext).getShopID()))
                        {
                        	cur = i;
                        }
                        shopsList.add(shopsInfo) ;
                    }
                    ShopsAdapter shopsAdapter = new ShopsAdapter(mContext, shopsList) ;
                    outlet_spinner.setAdapter(shopsAdapter) ;                    
                    
                    outlet_spinner.setSelection(cur);
                }
                else
                {
                    ToastUtilMgr.TextToast(mContext, getString(R.string.server_error1), ToastUtilMgr.LENGTH_LONG) ;
                }
            }

            @ Override
            public void failed(Message msg)
            {
            	ToastUtilMgr.TextToast(mContext, "获取门店信息失败", ToastUtilMgr.LENGTH_LONG) ;
            }
        } ;
        shopsMgr = new SoapMgr(mContext, null, null, SoapAction.GetShopsList, SoapAction.GetShopsList, shopsSoapParameter, getShopsHandler, true, false) ;
    }

   

    /**
     * 提交新建的任务
     * */
    private void submitNewTask()
    {

        if (checkInput())
        {
            sendNewTask() ;
        }
    }

    /**
     * 封装新建任务信息并发送
     * */
    private SoapObject commitSoapObj ;

    private SoapMgr commitSoapMgr ;

    private void sendNewTask()
    {

        final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, SoapAction.NewResource) ;
        soapParameter.addProperty("UserID", new AppPreference(mContext).getLoginUserID()) ;

        
        
        
        String RegionlID;        
        if(district_spinner.getCount()!=0)
        {
        	RegionlID = regionsList.get(district_spinner.getSelectedItemPosition()).getId() ;
        }        
        else
        {
        	Toast.makeText(mContext, "区域信息为空", Toast.LENGTH_SHORT).show();
        	return;
        }
        String ShopID;        
        if(outlet_spinner.getCount()!=0)
        {
        	ShopID = shopsList.get(outlet_spinner.getSelectedItemPosition()).getId() ;
        }        
        else
        {
        	Toast.makeText(mContext, "门店信息为空", Toast.LENGTH_SHORT).show();
        	return;
        }
        
        String Seller = saler_editT.getText().toString().trim() ;
        String CarCatalog = auto_type_editT.getText().toString().trim() ;
        String CarLicense = chepai_editT.getText().toString().trim() ;
        String CustomerName = customer_name_editT.getText().toString().trim() ;
        String CustomerMobile = telephone_editT.getText().toString().trim() ;
        String AppointmentTime = meetTime_editT.getText().toString().trim() ;
        String AppointmentAddress = checkPlace_editT.getText().toString().trim() ;
        String VIN = vin_editT.getText().toString().trim() ;
        String Intention = "1";
        
             
        
        
        if(yixiang_spinner.getSelectedItem().toString().equals("单卖"))
        {
        	Intention = "1";
        }
        if(yixiang_spinner.getSelectedItem().toString().equals("以旧换旧"))
        {
        	Intention = "2";
        }
        if(yixiang_spinner.getSelectedItem().toString().equals("以旧换新"))
        {
        	Intention = "3";
        }
        
        
        String Comments = " " ;

        
        String SourceID = "1";
        if(buidtask_radiogroup1_radio1.isChecked())
        {
        	SourceID = "1";
        }
        if(buidtask_radiogroup1_radio2.isChecked())
        {
        	SourceID = "2";
        }
        if(buidtask_radiogroup1_radio3.isChecked())
        {
        	SourceID = "3";
        }
        if(buidtask_radiogroup1_radio4.isChecked())
        {
        	SourceID = "4";
        }
        if(buidtask_radiogroup1_radio5.isChecked())
        {
        	SourceID = "5";
        }
        if(buidtask_radiogroup1_radio6.isChecked())
        {
        	SourceID = "6";
        }
        if(!buidtask_radiogroup1_radio7.getText().toString().trim().equals(""))
        {
        	SourceID = "7";
        }
        soapParameter.addProperty("SourceID", SourceID) ;
        soapParameter.addProperty("OtherSource", buidtask_radiogroup1_radio7.getText().toString().trim()) ;
        soapParameter.addProperty("CustomerName", CustomerName) ;
        soapParameter.addProperty("CustomerMobile", CustomerMobile) ;
        
        if(buidtask_radiogroup1_radio6.isChecked())
        {
        	SourceID = "6";
        }
        if(!buidtask_radiogroup1_radio7.getText().toString().trim().equals(""))
        {
        	SourceID = "7";
        }
        
        soapParameter.addProperty("Intention", Intention) ;
        soapParameter.addProperty("CarCatalog", CarCatalog) ;
        soapParameter.addProperty("CarLicense",CarLicense.toUpperCase());
        soapParameter.addProperty("VIN", VIN) ;        
        soapParameter.addProperty("RegionID", RegionlID) ;
        soapParameter.addProperty("ShopID", ShopID) ;
        soapParameter.addProperty("ProviderPerson", Seller) ;
        
        String bumen = "1";
        if(bumen_spinner.getSelectedItem().toString().equals("二手车部"))
        {
        	bumen = "1";
        }
        if(bumen_spinner.getSelectedItem().toString().equals("销售部"))
        {
        	bumen = "2";
        }
        if(bumen_spinner.getSelectedItem().toString().equals("售后部"))
        {
        	bumen = "3";
        }
        if(bumen_spinner.getSelectedItem().toString().equals("其他部门"))
        {
        	bumen = "4";
        }
        soapParameter.addProperty("ProviderDepartment", bumen) ;
        
        soapParameter.addProperty("AppointmentTime", AppointmentTime) ;
        soapParameter.addProperty("AppointmentAddress", AppointmentAddress) ;
        
        soapParameter.addProperty("Comments", Comments) ;

        
        
        
        
        
        MyHandler commitHandler = new MyHandler()
        {

            @ Override
            public void success(Message msg)
            {

                commitSoapObj = commitSoapMgr.getServiceBackSoapObject() ;
                if (commitSoapObj != null)
                {
                    String Retcode = (( SoapObject ) commitSoapObj.getProperty(0)).getProperty("Retcode").toString() ;
                    // 0成功，1失败
                    if (Retcode.equals("0"))
                    {
                        //返回任务列表界面
                        setResult(200);
                        finish() ;
                    }
                    if (Retcode.equals("500"))
                    {
                        //表示VIN码重复了
                    	final MineAlert alert = new MineAlert(mContext);
						alert.createAlertOneButton(false,"该车型的商机任务正在进行，不能新建商机任务!", 
								new View.OnClickListener() {								
									@Override
									public void onClick(View v) {
										alert.dimiss();
									}
								});
                    }
                    if (!Retcode.equals("500")&&!Retcode.equals("0"))
                    {
                        ToastUtilMgr.TextToast(mContext, getString(R.string.server_error1), ToastUtilMgr.LENGTH_LONG) ;
                    }
                }
            }

            @ Override
            public void failed(Message msg)
            {

            }
        } ;
        commitSoapMgr = new SoapMgr(mContext, null, null, SoapAction.NewResource, SoapAction.NewResource, soapParameter, commitHandler, true, false) ;
    }

    /**
     * 验证输入
     * */
    private boolean checkInput()
    {

        String nullValue = "" ;
        String saler = saler_editT.getText().toString().trim() ;
        String auto_type = auto_type_editT.getText().toString().trim() ;
        String chepai = chepai_editT.getText().toString().trim() ;
        String customer = customer_name_editT.getText().toString().trim() ;
        String telephone = telephone_editT.getText().toString().trim() ;
        String meetTime = meetTime_editT.getText().toString().trim() ;
        String checkPlace = checkPlace_editT.getText().toString().trim() ;
        String VIN = vin_editT.getText().toString().trim() ;
        if (!saler.equals(nullValue))
        {
            if (!auto_type.equals(nullValue))
            {
                if (!chepai.equals(nullValue))
                {
                    if (!customer.equals(nullValue))
                    {
                        if (!telephone.equals(nullValue))
                        {
                            if (isMobileNO(telephone) || isTelephone(telephone))
                            {
                                if (isChepai(chepai))
                                {
                                    if (!meetTime.equals(nullValue))
                                    {
                                        if (!checkPlace.equals(nullValue))
                                        {
//                                            if (!VIN.equals(nullValue))
//                                            {
//                                                return true ;
//                                            }
//                                            else
//                                            {
//                                                ToastUtilMgr.TextToast(mContext, getString(R.string.withoutCheckVIN), ToastUtilMgr.LENGTH_LONG) ;
//                                            }
                                        	return true;
                                        }
                                        else
                                        {
                                            ToastUtilMgr.TextToast(mContext, getString(R.string.withoutCheckPlace), ToastUtilMgr.LENGTH_LONG) ;
                                        }
                                    }
                                    else
                                    {
                                        ToastUtilMgr.TextToast(mContext, getString(R.string.withoutMeetTime), ToastUtilMgr.LENGTH_LONG) ;
                                    }

                                }
                            }
                            else
                            {
                                ToastUtilMgr.TextToast(mContext, getString(R.string.errorMobileNum), ToastUtilMgr.LENGTH_LONG) ;
                            }
                        }
                        else
                        {
                            ToastUtilMgr.TextToast(mContext, getString(R.string.withoutTelephone), ToastUtilMgr.LENGTH_LONG) ;
                        }
                    }
                    else
                    {
                        ToastUtilMgr.TextToast(mContext, getString(R.string.withoutCustomer), ToastUtilMgr.LENGTH_LONG) ;
                    }
                }
                else
                {
                    ToastUtilMgr.TextToast(mContext, getString(R.string.withoutChepai), ToastUtilMgr.LENGTH_LONG) ;
                }
            }
            else
            {
                // 请输入车型
                ToastUtilMgr.TextToast(mContext, getString(R.string.withoutCheXing), ToastUtilMgr.LENGTH_LONG) ;
            }

        }
        else
        {
            ToastUtilMgr.TextToast(mContext, getString(R.string.withoutSaler), ToastUtilMgr.LENGTH_LONG) ;
        }

        return false ;
    }

    private boolean isChepai(String chepai)
    {

        // 车牌号格式验证
    	
		Log.e("车牌", chepai.toUpperCase());
		
        String vehicleNoStyle = "^[\u4e00-\u9fa5]{1}[A-Z0-9]{6}$" ;
        Pattern pattern = Pattern.compile(vehicleNoStyle) ;
        Matcher matcher = pattern.matcher(chepai.toUpperCase()) ;
        if (matcher.matches())
        {
            return true ;
        }
        else
        {
            ToastUtilMgr.TextToast(mContext, getString(R.string.errorChepai), ToastUtilMgr.LENGTH_LONG) ;
        }
        return false ;
    }

    /**
     * 
     * 验证手机号码
     * */
    private boolean isMobileNO(String mobiles)
    {

        String mobileNumStyle = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$" ;
        Pattern p = Pattern.compile(mobileNumStyle) ;
        Matcher matcher = p.matcher(mobiles.trim()) ;
        return matcher.matches() ;
    }

    /**
     * 验证电话号码
     * */
    private boolean isTelephone(String phonenumber)
    {

        String phone = "0\\d{2,3}\\d{7,8}" ;
        Pattern p = Pattern.compile(phone) ;
        Matcher m = p.matcher(phonenumber.trim()) ;
        return m.matches() ;
    }
    
    
    private void setjiaodian()
    {    	
    	saler_editT.setNextFocusDownId(R.id.auto_type_editT);
    	auto_type_editT.setNextFocusDownId(R.id.chepai_editT);
    	chepai_editT.setNextFocusDownId(R.id.customer_name_editT);
    	customer_name_editT.setNextFocusDownId(R.id.telephone_editT);
    	telephone_editT.setNextFocusDownId(R.id.checkSpace_editT);
    	
    }
}
