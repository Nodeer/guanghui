package com.guanghui.car;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.guanghui.car.common.PictureUtil;
import com.guanghui.car.common.ToastUtilMgr;
import com.guanghui.car.communications.MineAlert;
import com.guanghui.car.communications.MyHandler;
import com.guanghui.car.communications.SoapAction;
import com.guanghui.car.communications.SoapMgr;
import com.guanghui.car.config.AppConfig;
import com.guanghui.car.config.AppPreference;
import com.guanghui.car.db.ChexingDengjiDB;
import com.guanghui.car.db.NewChexingDengjiDB;
import com.guanghui.car.jingmicheck.Constans;
import com.guanghui.car.jingmicheck.JingMiCarCheckActivity;

public class CarConfigurationDetailActivity extends Activity {

    private Button saveButton;
    private Button commitButton;
    private ImageView red;
    private ImageView green;
    private ImageView blue;
    private ImageView black;
    private ImageView white;
    private ImageView grey;
    private ImageView yellow;
    private ImageView orange;
    private ImageView brown;
    private ImageView purpe;
    private ImageView choiced;
    private TextView others;
    private CheckBox choiced_checked;
    private EditText configuration_km;
    private EditText configuration_miles;
    private CheckBox InstrumentIsChanged_radiobtn;

    private ImageView photo1;
    private ImageView photo2;
    private ImageView photo3;
    private ImageView photo4;
    private ImageView photo5;
    private ImageView photo6;
    private ImageView photo7;
    private ImageView photo8;
    private ImageView photo9;
    private ImageView photo10;
    private ImageView photo11;
    private ImageView photo12;
    private ImageView photo13;
    private ImageView photo14;
    private ImageView photo15;
    private ImageView photo16;
    private ImageView photo17;
    private ImageView photo18;
//    private Button photo1_btn;
//    private Button photo2_btn;
//    private Button photo3_btn;
//    private Button photo4_btn;


    private EditText GuidePrice_edit;
    private EditText LowestPrice_edit;
    private Button newPrice_btn;
    private EditText UsedPriceLow_edit;
    private EditText UsedPriceHigh_edit;
    private Button twoPrice_btn;
    private Context context = this;

    // 获取数据
    private String xinghaoID;
    private String oc;
    private String mc;
    private String DisplayKm = "0";
    private String DisplayMiles = "0";
    private String GuidePrice = "0";
    private String LowestPrice = "0";
    private String UsedPriceLow = "0";
    private String UsedPriceHigh = "0";
    private String photostr1 = "";
    private String photostr2 = "";
    private String photostr3 = "";
    private String photostr4 = "";
    private String photostr5 = "";
    private String photostr6 = "";
    private String photostr7 = "";
    private String photostr8 = "";
    private String photostr9 = "";
    private String photostr10 = "";
    private String photostr11 = "";
    private String photostr12 = "";
    private String photostr13 = "";
    private String photostr14 = "";
    private String photostr15 = "";
    private String photostr16 = "";
    private String photostr17 = "";
    private String photostr18 = "";

    private final String[] photeStrArray = {"左前45度", "驾驶位左侧(含左B柱)", "左前门内侧", "里程表", "后排座左侧", "中控与方向盘", "左侧机身", "后备箱整体", "后备箱底板", "右后45度", "右侧车身", "后排座右侧", "副驾驶右侧(含右B柱)", "出厂铭牌", "发动机整体", "发动机机舱右侧", "发动机机舱左侧", "轮毂"};

    private String singleCameraImgPath1;
    private String singleCameraImgPath2;
    private String singleCameraImgPath3;
    private String singleCameraImgPath4;
    private String singleCameraImgPath5;
    private String singleCameraImgPath6;
    private String singleCameraImgPath7;
    private String singleCameraImgPath8;
    private String singleCameraImgPath9;
    private String singleCameraImgPath10;
    private String singleCameraImgPath11;
    private String singleCameraImgPath12;
    private String singleCameraImgPath13;
    private String singleCameraImgPath14;
    private String singleCameraImgPath15;
    private String singleCameraImgPath16;
    private String singleCameraImgPath17;
    private String singleCameraImgPath18;

    private String InstrumentIsChanged = "0";

    private String color = "-1";
    private int paizhaoIndex;

    private MyHandler myhandler;
    private SoapMgr mgr;
    private SoapObject backSoapObject;

    private Bitmap bm1, bm2, bm3, bm4, bm5, bm6, bm7, bm8, bm9, bm10, bm11, bm12, bm13, bm14, bm15, bm16, bm17, bm18;

    private boolean edit;

    private String HistoryResourceID;


    /**
     * 拍出来虽然那么大，但是会进行两倍的压缩  变成 800x600
     */
    private int ImageWidth = 1600;
    private int ImageHeight = 1200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_configuration_detail);
        new BuildTab(context, 1);

        HistoryResourceID = new AppPreference(context).getHistoryResourceID();

        edit = this.getIntent().getBooleanExtra("edit", false);

        //这里user_token用错了.应该以任务为单位进行存储
//        if (new AppPreference(context).getLoginUserID().trim().equals("")) {
//            Constans.user_token = 0;
//        } else {
//            Constans.user_token = Integer.parseInt(new AppPreference(context).getLoginUserID().trim());
//        }


        xinghaoID = this.getIntent().getStringExtra("xinghaoID");

        oc = this.getIntent().getStringExtra("oc");
        mc = this.getIntent().getStringExtra("mc");

        red = (ImageView) findViewById(R.id.img_red);
        green = (ImageView) findViewById(R.id.img_green);
        blue = (ImageView) findViewById(R.id.img_blue);
        black = (ImageView) findViewById(R.id.img_black);
        white = (ImageView) findViewById(R.id.img_white);
        grey = (ImageView) findViewById(R.id.img_grey);
        yellow = (ImageView) findViewById(R.id.img_yellow);
        orange = (ImageView) findViewById(R.id.img_orange);
        brown = (ImageView) findViewById(R.id.img_brown);
        purpe = (ImageView) findViewById(R.id.img_purpe);
        choiced = (ImageView) findViewById(R.id.choiced_color);
        others = (TextView) findViewById(R.id.text_others);
        choiced_checked = (CheckBox) findViewById(R.id.choiced_checked);
        configuration_km = (EditText) findViewById(R.id.configuration_km);
        configuration_miles = (EditText) findViewById(R.id.configuration_miles);
        InstrumentIsChanged_radiobtn = (CheckBox) findViewById(R.id.InstrumentIsChanged_radiobtn);
        photo1 = (ImageView) findViewById(R.id.photo1_img);
        photo2 = (ImageView) findViewById(R.id.photo2_img);
        photo3 = (ImageView) findViewById(R.id.photo3_img);
        photo4 = (ImageView) findViewById(R.id.photo4_img);
        photo5 = (ImageView) findViewById(R.id.photo5_img);
        photo6 = (ImageView) findViewById(R.id.photo6_img);
        photo7 = (ImageView) findViewById(R.id.photo7_img);
        photo8 = (ImageView) findViewById(R.id.photo8_img);
        photo9 = (ImageView) findViewById(R.id.photo9_img);
        photo10 = (ImageView) findViewById(R.id.photo10_img);
        photo11 = (ImageView) findViewById(R.id.photo11_img);
        photo12 = (ImageView) findViewById(R.id.photo12_img);
        photo13 = (ImageView) findViewById(R.id.photo13_img);
        photo14 = (ImageView) findViewById(R.id.photo14_img);
        photo15 = (ImageView) findViewById(R.id.photo15_img);
        photo16 = (ImageView) findViewById(R.id.photo16_img);
        photo17 = (ImageView) findViewById(R.id.photo17_img);
        photo18 = (ImageView) findViewById(R.id.photo18_img);
//        photo1_btn = (Button) findViewById(R.id.photo1_btn);
//        photo2_btn = (Button) findViewById(R.id.photo2_btn);
//        photo3_btn = (Button) findViewById(R.id.photo3_btn);
//        photo4_btn = (Button) findViewById(R.id.photo4_btn);
        GuidePrice_edit = (EditText) findViewById(R.id.GuidePrice_edit);
        LowestPrice_edit = (EditText) findViewById(R.id.LowestPrice_edit);
        newPrice_btn = (Button) findViewById(R.id.newPrice_btn);
        UsedPriceLow_edit = (EditText) findViewById(R.id.UsedPriceLow_edit);
        UsedPriceHigh_edit = (EditText) findViewById(R.id.UsedPriceHigh_edit);
        twoPrice_btn = (Button) findViewById(R.id.twoPrice_btn);
        red.setOnClickListener(mListener);
        green.setOnClickListener(mListener);
        blue.setOnClickListener(mListener);
        black.setOnClickListener(mListener);
        white.setOnClickListener(mListener);
        grey.setOnClickListener(mListener);
        yellow.setOnClickListener(mListener);
        orange.setOnClickListener(mListener);
        brown.setOnClickListener(mListener);
        purpe.setOnClickListener(mListener);
        InstrumentIsChanged_radiobtn.setOnClickListener(mListener);
        photo1.setOnClickListener(mListener);
        photo2.setOnClickListener(mListener);
        photo3.setOnClickListener(mListener);
        photo4.setOnClickListener(mListener);
        photo5.setOnClickListener(mListener);
        photo6.setOnClickListener(mListener);
        photo7.setOnClickListener(mListener);
        photo8.setOnClickListener(mListener);
        photo9.setOnClickListener(mListener);
        photo10.setOnClickListener(mListener);
        photo11.setOnClickListener(mListener);
        photo12.setOnClickListener(mListener);
        photo13.setOnClickListener(mListener);
        photo14.setOnClickListener(mListener);
        photo15.setOnClickListener(mListener);
        photo16.setOnClickListener(mListener);
        photo17.setOnClickListener(mListener);
        photo18.setOnClickListener(mListener);
        newPrice_btn.setOnClickListener(mListener);
        twoPrice_btn.setOnClickListener(mListener);
        others.setOnClickListener(mListener);
        initBottom();
        initTitle();


        configuration_km.setNextFocusDownId(R.id.configuration_miles);
        configuration_miles.setNextFocusDownId(R.id.GuidePrice_edit);
        GuidePrice_edit.setNextFocusDownId(R.id.LowestPrice_edit);
        LowestPrice_edit.setNextFocusDownId(R.id.UsedPriceLow_edit);
        UsedPriceLow_edit.setNextFocusDownId(R.id.UsedPriceHigh_edit);


        GuidePrice_edit.setText("0");
        LowestPrice_edit.setText("0");
        UsedPriceLow_edit.setText("0");
        UsedPriceHigh_edit.setText("0");


        if (!HistoryResourceID.equals("0")) {
            ChexingDengjiDB db = new ChexingDengjiDB(context);
            if (!db.isExistById(new AppPreference(context).getTaskId())) {
                //当前任务数据为空，则说明是第一次进入该任务
                initUIDataFromDB(HistoryResourceID);
            } else {
                initUIDataFromDB(new AppPreference(context).getTaskId());
            }
            db.close();
        } else {
            initUIDataFromDB(new AppPreference(context).getTaskId());
        }


    }

    private void initBottom() {
        saveButton = (Button) findViewById(R.id.stop_save);
        commitButton = (Button) findViewById(R.id.comfirm_communit);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saveData()) {
                    final MineAlert alert = new MineAlert(context);
                    alert.createAlertOneButton(false, "已成功保存",
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    alert.dimiss();
                                }
                            });
                } else {
                    final MineAlert alert = new MineAlert(context);
                    alert.createAlertOneButton(false, "保存失败",
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    alert.dimiss();
                                }
                            });
                }
            }
        });
        commitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saveData()) {
                    DisplayKm = configuration_km.getText().toString().trim();
                    DisplayMiles = configuration_miles.getText().toString().trim();
                    if (DisplayMiles.trim().equals("")) {
                        DisplayMiles = "0";
                    }
                    GuidePrice = GuidePrice_edit.getText().toString().trim();
                    LowestPrice = LowestPrice_edit.getText().toString().trim();
                    UsedPriceLow = UsedPriceLow_edit.getText().toString().trim();
                    UsedPriceHigh = UsedPriceHigh_edit.getText().toString().trim();

                    if (color.equals("-1")) {
                        Toast.makeText(context, "请选择颜色", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (DisplayKm.equals("") && DisplayMiles.equals("")) {
                        Toast.makeText(context, "请输入里程数,公里或英里!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (DisplayKm.trim().equals("")) {
                        DisplayKm = "0";
                    }
                    if (DisplayMiles.trim().equals("")) {
                        DisplayMiles = "0";
                    }

                    //拍18张照片太累了...这里是测试代码..复制第一张照片到剩下的...
//                    photostr2 = photostr1;
//                    photostr3 = photostr1;
//                    photostr4 = photostr1;
//                    photostr5 = photostr1;
//                    photostr6 = photostr1;
//                    photostr7 = photostr1;
//                    photostr8 = photostr1;
//                    photostr9 = photostr1;
//                    photostr10 = photostr1;
//                    photostr11 = photostr1;
//                    photostr12 = photostr1;
//                    photostr13 = photostr1;
//                    photostr14 = photostr1;
//                    photostr15 = photostr1;
//                    photostr16 = photostr1;
//                    photostr17 = photostr1;
//                    photostr18 = photostr1;

                    if (photostr1.equals("") || photostr2.equals("") || photostr3.equals("") || photostr4.equals("") || photostr5.equals("") || photostr6.equals("") || photostr7.equals("") || photostr8.equals("") || photostr9.equals("") || photostr10.equals("") || photostr11.equals("") || photostr12.equals("") || photostr13.equals("") || photostr14.equals("") || photostr15.equals("") || photostr16.equals("") || photostr17.equals("") || photostr18.equals("")) {
                        Toast.makeText(context, "请拍摄完整的十八张照片", Toast.LENGTH_SHORT).show();
                        return;
                    }
//					if(GuidePrice.equals(""))
//					{
//						Toast.makeText(context, "请输入厂商指导价", Toast.LENGTH_SHORT).show();
//						return;
//					}
//					if(LowestPrice.equals(""))
//					{
//						Toast.makeText(context, "请输入当地最低价", Toast.LENGTH_SHORT).show();
//						return;
//					}
//					if(UsedPriceLow.equals("") || UsedPriceHigh.equals(""))
//					{
//						Toast.makeText(context, "请输入完整的二手车价格", Toast.LENGTH_SHORT).show();
//						return;
//					}
                    SaveResourceModel2();
                } else {
                    Toast.makeText(context, "数据未成功保存，请重试!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initTitle() {
        ((TextView) findViewById(R.id.title_renwu_btn1))
                .setBackgroundDrawable(context.getResources().getDrawable(
                        R.drawable.title_bg));
        ((TextView) findViewById(R.id.title_renwu_btn2))
                .setBackgroundDrawable(context.getResources().getDrawable(
                        R.drawable.bg));
        ((TextView) findViewById(R.id.title_renwu_btn3))
                .setBackgroundDrawable(context.getResources().getDrawable(
                        R.drawable.title_bg));
    }

    private OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.img_red:
                    choiced.setBackgroundColor(context.getResources().getColor(
                            R.color.configuration_red));
                    choiced_checked.setChecked(true);

                    color = "0";
                    break;
                case R.id.img_green:
                    choiced_checked.setChecked(true);
                    choiced.setBackgroundColor(context.getResources().getColor(
                            R.color.configuration_green));
                    color = "1";
                    break;
                case R.id.img_blue:
                    choiced.setBackgroundColor(context.getResources().getColor(
                            R.color.configuration_blue));
                    choiced_checked.setChecked(true);
                    color = "2";
                    break;
                case R.id.img_black:
                    choiced.setBackgroundColor(context.getResources().getColor(
                            R.color.configuration_black));
                    choiced_checked.setChecked(true);
                    color = "3";
                    break;
                case R.id.img_white:
                    choiced.setBackgroundColor(context.getResources().getColor(
                            R.color.configuration_white));
                    choiced_checked.setChecked(true);
                    color = "4";
                    break;
                case R.id.img_grey:
                    choiced.setBackgroundColor(context.getResources().getColor(
                            R.color.configuration_grey));
                    choiced_checked.setChecked(true);
                    color = "5";
                    break;
                case R.id.img_yellow:
                    choiced.setBackgroundColor(context.getResources().getColor(
                            R.color.configuration_yellow));
                    choiced_checked.setChecked(true);
                    color = "6";
                    break;
                case R.id.img_orange:
                    choiced.setBackgroundColor(context.getResources().getColor(
                            R.color.configuration_orange));
                    choiced_checked.setChecked(true);
                    color = "7";
                    break;
                case R.id.img_brown:
                    choiced.setBackgroundColor(context.getResources().getColor(
                            R.color.configuration_brown));
                    choiced_checked.setChecked(true);
                    color = "8";
                    break;
                case R.id.img_purpe:
                    choiced.setBackgroundColor(context.getResources().getColor(
                            R.color.configuration_purpe));
                    choiced_checked.setChecked(true);
                    color = "9";
                    break;
                case R.id.text_others:
                    choiced.setBackgroundColor(context.getResources().getColor(
                            R.color.transparent));
                    choiced_checked.setChecked(true);
                    color = "100";
                    break;


                case R.id.photo1_img:
                    paizhaoIndex = 0;
                    getPhotoFromCamera(paizhaoIndex);
                    break;
                case R.id.photo2_img:
                    paizhaoIndex = 1;
                    getPhotoFromCamera(paizhaoIndex);
                    break;
                case R.id.photo3_img:
                    paizhaoIndex = 2;
                    getPhotoFromCamera(paizhaoIndex);
                    break;
                case R.id.photo4_img:
                    paizhaoIndex = 3;
                    getPhotoFromCamera(paizhaoIndex);
                    break;
                case R.id.photo5_img:
                    paizhaoIndex = 4;
                    getPhotoFromCamera(paizhaoIndex);
                    break;
                case R.id.photo6_img:
                    paizhaoIndex = 5;
                    getPhotoFromCamera(paizhaoIndex);
                    break;
                case R.id.photo7_img:
                    paizhaoIndex = 6;
                    getPhotoFromCamera(paizhaoIndex);
                    break;
                case R.id.photo8_img:
                    paizhaoIndex = 7;
                    getPhotoFromCamera(paizhaoIndex);
                    break;
                case R.id.photo9_img:
                    paizhaoIndex = 8;
                    getPhotoFromCamera(paizhaoIndex);
                    break;
                case R.id.photo10_img:
                    paizhaoIndex = 9;
                    getPhotoFromCamera(paizhaoIndex);
                    break;
                case R.id.photo11_img:
                    paizhaoIndex = 10;
                    getPhotoFromCamera(paizhaoIndex);
                    break;
                case R.id.photo12_img:
                    paizhaoIndex = 11;
                    getPhotoFromCamera(paizhaoIndex);
                    break;
                case R.id.photo13_img:
                    paizhaoIndex = 12;
                    getPhotoFromCamera(paizhaoIndex);
                    break;
                case R.id.photo14_img:
                    paizhaoIndex = 13;
                    getPhotoFromCamera(paizhaoIndex);
                    break;
                case R.id.photo15_img:
                    paizhaoIndex = 14;
                    getPhotoFromCamera(paizhaoIndex);
                    break;
                case R.id.photo16_img:
                    paizhaoIndex = 15;
                    getPhotoFromCamera(paizhaoIndex);
                    break;
                case R.id.photo17_img:
                    paizhaoIndex = 16;
                    getPhotoFromCamera(paizhaoIndex);
                    break;
                case R.id.photo18_img:
                    paizhaoIndex = 17;
                    getPhotoFromCamera(paizhaoIndex);
                    break;
                case R.id.newPrice_btn:

                    Intent intent5 = new Intent();
                    intent5.setClass(context, CarPriceListActivity.class);
                    startActivity(intent5);

                    break;
                case R.id.twoPrice_btn:

                    GetUsedCarPrice(xinghaoID);


                    break;
                case R.id.InstrumentIsChanged_radiobtn:
                    if (InstrumentIsChanged_radiobtn.isChecked()) {
                        InstrumentIsChanged = "1";
                    } else {
                        InstrumentIsChanged = "0";
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private void getPhotoFromCamera(int index) {
        String fileName2 = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date()) + ".jpg";// 照片按时间格式yyyyMMddHHmmss命名
        // 该照片的绝对路径
        String filename = "";
        switch (index) {
            case 0:
                singleCameraImgPath1 = AppConfig.CacheDir + "/" + fileName2;
                filename = singleCameraImgPath1;
                break;
            case 1:
                singleCameraImgPath2 = AppConfig.CacheDir + "/" + fileName2;
                filename = singleCameraImgPath2;
                break;
            case 2:
                singleCameraImgPath3 = AppConfig.CacheDir + "/" + fileName2;
                filename = singleCameraImgPath3;
                break;
            case 3:
                singleCameraImgPath4 = AppConfig.CacheDir + "/" + fileName2;
                filename = singleCameraImgPath4;
                break;
            case 4:
                singleCameraImgPath5 = AppConfig.CacheDir + "/" + fileName2;
                filename = singleCameraImgPath5;
                break;
            case 5:
                singleCameraImgPath6 = AppConfig.CacheDir + "/" + fileName2;
                filename = singleCameraImgPath6;
                break;
            case 6:
                singleCameraImgPath7 = AppConfig.CacheDir + "/" + fileName2;
                filename = singleCameraImgPath7;
                break;
            case 7:
                singleCameraImgPath8 = AppConfig.CacheDir + "/" + fileName2;
                filename = singleCameraImgPath8;
                break;
            case 8:
                singleCameraImgPath9 = AppConfig.CacheDir + "/" + fileName2;
                filename = singleCameraImgPath9;
                break;
            case 9:
                singleCameraImgPath10 = AppConfig.CacheDir + "/" + fileName2;
                filename = singleCameraImgPath10;
                break;
            case 10:
                singleCameraImgPath11 = AppConfig.CacheDir + "/" + fileName2;
                filename = singleCameraImgPath11;
                break;
            case 11:
                singleCameraImgPath12 = AppConfig.CacheDir + "/" + fileName2;
                filename = singleCameraImgPath12;
                break;
            case 12:
                singleCameraImgPath13 = AppConfig.CacheDir + "/" + fileName2;
                filename = singleCameraImgPath13;
                break;
            case 13:
                singleCameraImgPath14 = AppConfig.CacheDir + "/" + fileName2;
                filename = singleCameraImgPath14;
                break;
            case 14:
                singleCameraImgPath15 = AppConfig.CacheDir + "/" + fileName2;
                filename = singleCameraImgPath15;
                break;
            case 15:
                singleCameraImgPath16 = AppConfig.CacheDir + "/" + fileName2;
                filename = singleCameraImgPath16;
                break;
            case 16:
                singleCameraImgPath17 = AppConfig.CacheDir + "/" + fileName2;
                filename = singleCameraImgPath17;
                break;
            case 17:
                singleCameraImgPath18 = AppConfig.CacheDir + "/" + fileName2;
                filename = singleCameraImgPath18;
                break;
        }
//        singleCameraImgPath2 = AppConfig.CacheDir + "/" + fileName2;
        File vFile2 = new File(filename);
        if (!vFile2.exists()) {
            File vDirPath = vFile2.getParentFile();
            vDirPath.mkdirs();
        }
        Intent intent2 = new Intent();
        intent2.putExtra("title", photeStrArray[index]);
        intent2.setClass(context, TakePhotoActivity.class);
        intent2.putExtra("fileDir", AppConfig.CacheDir);
        intent2.putExtra("fileName", fileName2);
        intent2.putExtra("ImageWidth", ImageWidth);
        intent2.putExtra("ImageHeight", ImageHeight);
        startActivityForResult(intent2, 0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (paizhaoIndex) {
                case 0:
                    if (bm1 != null) {
                        if (!bm1.isRecycled()) {
                            bm1.recycle();
                            bm1 = null;
                        }
                    }
                    bm1 = PictureUtil.getSmallBitmap(singleCameraImgPath1, 2);
                    photo1.setImageBitmap(bm1);
                    photostr1 = PictureUtil.bitmapToString(singleCameraImgPath1, true);
                    break;
                case 1:
                    if (bm2 != null) {
                        if (!bm2.isRecycled()) {
                            bm2.recycle();
                            bm2 = null;
                        }
                    }
                    bm2 = PictureUtil.getSmallBitmap(singleCameraImgPath2, 2);
                    photo2.setImageBitmap(bm2);
                    photostr2 = PictureUtil.bitmapToString(singleCameraImgPath2, true);
                    break;
                case 2:
                    if (bm3 != null) {
                        if (!bm3.isRecycled()) {
                            bm3.recycle();
                            bm3 = null;
                        }
                    }
                    bm3 = PictureUtil.getSmallBitmap(singleCameraImgPath3, 2);
                    photo3.setImageBitmap(bm3);
                    photostr3 = PictureUtil.bitmapToString(singleCameraImgPath3, true);
                    break;
                case 3:
                    if (bm4 != null) {
                        if (!bm4.isRecycled()) {
                            bm4.recycle();
                            bm4 = null;
                        }
                    }
                    bm4 = PictureUtil.getSmallBitmap(singleCameraImgPath4, 2);
                    photo4.setImageBitmap(bm4);
                    photostr4 = PictureUtil.bitmapToString(singleCameraImgPath4, true);
                    break;
                case 4:
                    if (bm5 != null) {
                        if (!bm5.isRecycled()) {
                            bm5.recycle();
                            bm5 = null;
                        }
                    }
                    bm5 = PictureUtil.getSmallBitmap(singleCameraImgPath5, 2);
                    photo5.setImageBitmap(bm5);
                    photostr5 = PictureUtil.bitmapToString(singleCameraImgPath5, true);
                    break;
                case 5:
                    if (bm6 != null) {
                        if (!bm6.isRecycled()) {
                            bm6.recycle();
                            bm6 = null;
                        }
                    }
                    bm6 = PictureUtil.getSmallBitmap(singleCameraImgPath6, 2);
                    photo6.setImageBitmap(bm6);
                    photostr6 = PictureUtil.bitmapToString(singleCameraImgPath6, true);
                    break;
                case 6:
                    if (bm7 != null) {
                        if (!bm7.isRecycled()) {
                            bm7.recycle();
                            bm7 = null;
                        }
                    }
                    bm7 = PictureUtil.getSmallBitmap(singleCameraImgPath7, 2);
                    photo7.setImageBitmap(bm7);
                    photostr7 = PictureUtil.bitmapToString(singleCameraImgPath7, true);
                    break;
                case 7:
                    if (bm8 != null) {
                        if (!bm8.isRecycled()) {
                            bm8.recycle();
                            bm8 = null;
                        }
                    }
                    bm8 = PictureUtil.getSmallBitmap(singleCameraImgPath8, 2);
                    photo8.setImageBitmap(bm8);
                    photostr8 = PictureUtil.bitmapToString(singleCameraImgPath8, true);
                    break;
                case 8:
                    if (bm9 != null) {
                        if (!bm9.isRecycled()) {
                            bm9.recycle();
                            bm9 = null;
                        }
                    }
                    bm9 = PictureUtil.getSmallBitmap(singleCameraImgPath9, 2);
                    photo9.setImageBitmap(bm9);
                    photostr9 = PictureUtil.bitmapToString(singleCameraImgPath9, true);
                    break;
                case 9:
                    if (bm10 != null) {
                        if (!bm10.isRecycled()) {
                            bm10.recycle();
                            bm10 = null;
                        }
                    }
                    bm10 = PictureUtil.getSmallBitmap(singleCameraImgPath10, 2);
                    photo10.setImageBitmap(bm10);
                    photostr10 = PictureUtil.bitmapToString(singleCameraImgPath10, true);
                    break;
                case 10:
                    if (bm11 != null) {
                        if (!bm11.isRecycled()) {
                            bm11.recycle();
                            bm11 = null;
                        }
                    }
                    bm11 = PictureUtil.getSmallBitmap(singleCameraImgPath11, 2);
                    photo11.setImageBitmap(bm11);
                    photostr11 = PictureUtil.bitmapToString(singleCameraImgPath11, true);
                    break;
                case 11:
                    if (bm12 != null) {
                        if (!bm12.isRecycled()) {
                            bm12.recycle();
                            bm12 = null;
                        }
                    }
                    bm12 = PictureUtil.getSmallBitmap(singleCameraImgPath12, 2);
                    photo12.setImageBitmap(bm12);
                    photostr12 = PictureUtil.bitmapToString(singleCameraImgPath12, true);
                    break;
                case 12:
                    if (bm13 != null) {
                        if (!bm13.isRecycled()) {
                            bm13.recycle();
                            bm13 = null;
                        }
                    }
                    bm13 = PictureUtil.getSmallBitmap(singleCameraImgPath13, 2);
                    photo13.setImageBitmap(bm13);
                    photostr13 = PictureUtil.bitmapToString(singleCameraImgPath13, true);
                    break;
                case 13:
                    if (bm14 != null) {
                        if (!bm14.isRecycled()) {
                            bm14.recycle();
                            bm14 = null;
                        }
                    }
                    bm14 = PictureUtil.getSmallBitmap(singleCameraImgPath14, 2);
                    photo14.setImageBitmap(bm14);
                    photostr14 = PictureUtil.bitmapToString(singleCameraImgPath14, true);
                    break;
                case 14:
                    if (bm15 != null) {
                        if (!bm15.isRecycled()) {
                            bm15.recycle();
                            bm15 = null;
                        }
                    }
                    bm15 = PictureUtil.getSmallBitmap(singleCameraImgPath15, 2);
                    photo15.setImageBitmap(bm15);
                    photostr15 = PictureUtil.bitmapToString(singleCameraImgPath15, true);
                    break;
                case 15:
                    if (bm16 != null) {
                        if (!bm16.isRecycled()) {
                            bm16.recycle();
                            bm16 = null;
                        }
                    }
                    bm16 = PictureUtil.getSmallBitmap(singleCameraImgPath16, 2);
                    photo16.setImageBitmap(bm16);
                    photostr16 = PictureUtil.bitmapToString(singleCameraImgPath16, true);
                    break;
                case 16:
                    if (bm17 != null) {
                        if (!bm17.isRecycled()) {
                            bm17.recycle();
                            bm17 = null;
                        }
                    }
                    bm17 = PictureUtil.getSmallBitmap(singleCameraImgPath17, 2);
                    photo17.setImageBitmap(bm17);
                    photostr17 = PictureUtil.bitmapToString(singleCameraImgPath17, true);
                    break;
                case 17:
                    if (bm18 != null) {
                        if (!bm18.isRecycled()) {
                            bm18.recycle();
                            bm18 = null;
                        }
                    }
                    bm18 = PictureUtil.getSmallBitmap(singleCameraImgPath18, 2);
                    photo18.setImageBitmap(bm18);
                    photostr18 = PictureUtil.bitmapToString(singleCameraImgPath18, true);
                    break;
            }
        }
    }

    /**
     * 保存车辆登记信息
     */
    private void SaveResourceModel2() {

        final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "SaveResourceModel2");
        soapParameter.addProperty("ResourceID", new AppPreference(context).getTaskId());
        soapParameter.addProperty("Color", color);
        soapParameter.addProperty("DisplayKm", DisplayKm);
        soapParameter.addProperty("DisplayMiles", DisplayMiles);
        soapParameter.addProperty("InstrumentIsChanged", InstrumentIsChanged);
        soapParameter.addProperty("GuidePrice", GuidePrice);
        soapParameter.addProperty("LowestPrice", LowestPrice);
        soapParameter.addProperty("UsedPriceLow", UsedPriceLow);
        soapParameter.addProperty("UsedPriceHigh", UsedPriceHigh);
        soapParameter.addProperty("Photo1", photostr1);
        soapParameter.addProperty("Photo2", photostr2);
        soapParameter.addProperty("Photo3", photostr3);
        soapParameter.addProperty("Photo4", photostr4);
        soapParameter.addProperty("Photo5", photostr5);
        soapParameter.addProperty("Photo6", photostr6);
        soapParameter.addProperty("Photo7", photostr7);
        soapParameter.addProperty("Photo8", photostr8);
        soapParameter.addProperty("Photo9", photostr9);
        soapParameter.addProperty("Photo10", photostr10);
        soapParameter.addProperty("Photo11", photostr11);
        soapParameter.addProperty("Photo12", photostr12);
        soapParameter.addProperty("Photo13", photostr13);
        soapParameter.addProperty("Photo14", photostr14);
        soapParameter.addProperty("Photo15", photostr15);
        soapParameter.addProperty("Photo16", photostr16);
        soapParameter.addProperty("Photo17", photostr17);
        soapParameter.addProperty("Photo18", photostr18);

        myhandler = new MyHandler() {
            @Override
            public void success(Message msg) {
                backSoapObject = mgr.getServiceBackSoapObject();
                if (backSoapObject != null) {
                    String Retcode = ((SoapObject) backSoapObject.getProperty(0)).getProperty("Retcode").toString();
                    //0成功，1失败
                    if (Retcode.equals("0")) {
                        ToastUtilMgr.TextToast(context, "数据提交成功", ToastUtilMgr.LENGTH_SHORT);


                        if (edit) {
                            Intent intent = new Intent();
                            intent.setClass(context, CarDengji.class);
                            setResult(1, intent);
                            finish();
                        } else {
                            Intent intent = new Intent();
                            intent.setClass(context, JingMiCarCheckActivity.class);
                            startActivity(intent);
                        }

                    } else {
                        ToastUtilMgr.TextToast(context, getString(R.string.server_error1), ToastUtilMgr.LENGTH_LONG);
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
                SoapAction.SaveResourceModel2,
                "SaveResourceModel2",
                soapParameter,
                myhandler,
                true,
                false
        );
    }


    /**
     * 获取最高价最低价的区间
     *
     * @param ModelID id
     */
    private void GetUsedCarPrice(String ModelID) {

        final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "GetUsedCarPrice");
        soapParameter.addProperty("UserID", new AppPreference(context).getLoginUserID());
        soapParameter.addProperty("ResourceID", new AppPreference(context).getTaskId());

        myhandler = new MyHandler() {
            @Override
            public void success(Message msg) {
                backSoapObject = mgr.getServiceBackSoapObject();
                if (backSoapObject != null) {
                    String Retcode = ((SoapObject) backSoapObject.getProperty(0)).getProperty("Retcode").toString();
                    //0成功，1失败
                    if (Retcode.equals("0")) {
                        String MinPrice = ((SoapObject) backSoapObject.getProperty(0)).getProperty("MinPrice").toString();
                        String MaxPrice = ((SoapObject) backSoapObject.getProperty(0)).getProperty("MaxPrice").toString();
                        UsedPriceLow_edit.setText(MinPrice.equals("anyType") ? "" : MinPrice);
                        UsedPriceHigh_edit.setText(MaxPrice.equals("anyType") ? "" : MaxPrice);
                    } else {
                        final MineAlert alert = new MineAlert(context);
                        alert.createAlertOneButton(false, "获取二手车价格失败",
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
                SoapAction.GetUsedCarPrice,
                "GetUsedCarPrice",
                soapParameter,
                myhandler,
                true,
                false
        );
    }


    /**
     * 从本地数据库读取数据对UI进行初始化
     * 任务ID
     */
    private void initUIDataFromDB(String taskID) {
        ChexingDengjiDB db = new ChexingDengjiDB(context);
        Cursor cursor = db.selectById(taskID);
        if (cursor.moveToFirst() == false) {
            return;
        }
        String ResourceID = cursor.getString(0);
        String ModelID = cursor.getString(1);
        String OptionConfiguration = cursor.getString(2);
        String ModConfiguration = cursor.getString(3);
        String Color = cursor.getString(4);
        String DisplayKm = cursor.getString(5);
        String DisplayMiles = cursor.getString(6);
        String InstrumentIsChanged = cursor.getString(7);
        String GuidePrice = cursor.getString(8).equals("") ? "0" : cursor.getString(8);
        String LowestPrice = cursor.getString(9).equals("") ? "0" : cursor.getString(9);
        String UsedPriceLow = cursor.getString(10).equals("") ? "0" : cursor.getString(10);
        String UsedPriceHigh = cursor.getString(11).equals("") ? "0" : cursor.getString(11);
        String Photo1 = cursor.getString(12);
        String Photo2 = cursor.getString(13);
        String Photo3 = cursor.getString(14);
        String Photo4 = cursor.getString(15);
        String Photo5 = cursor.getString(16);
        String Photo6 = cursor.getString(17);
        String Photo7 = cursor.getString(18);
        String Photo8 = cursor.getString(19);
        String Photo9 = cursor.getString(20);
        String Photo10 = cursor.getString(21);
        String Photo11 = cursor.getString(22);
        String Photo12 = cursor.getString(23);
        String Photo13 = cursor.getString(24);
        String Photo14 = cursor.getString(25);
        String Photo15 = cursor.getString(26);
        String Photo16 = cursor.getString(27);
        String Photo17 = cursor.getString(28);
        String Photo18 = cursor.getString(29);
        db.close();

        oc = OptionConfiguration;
        mc = ModConfiguration;

        if (Color.equals("0")) {
            choiced.setBackgroundColor(context.getResources().getColor(
                    R.color.transparent));
            choiced_checked.setChecked(false);
        }
        if (Color.equals("1")) {
            choiced.setBackgroundColor(context.getResources().getColor(
                    R.color.configuration_red));
            choiced_checked.setChecked(true);
        }
        if (Color.equals("2")) {
            choiced.setBackgroundColor(context.getResources().getColor(
                    R.color.configuration_green));
            choiced_checked.setChecked(true);
        }
        if (Color.equals("3")) {
            choiced.setBackgroundColor(context.getResources().getColor(
                    R.color.configuration_blue));
            choiced_checked.setChecked(true);
        }
        if (Color.equals("4")) {
            choiced.setBackgroundColor(context.getResources().getColor(
                    R.color.configuration_black));
            choiced_checked.setChecked(true);
        }
        if (Color.equals("5")) {
            choiced.setBackgroundColor(context.getResources().getColor(
                    R.color.configuration_white));
            choiced_checked.setChecked(true);
        }
        if (Color.equals("6")) {
            choiced.setBackgroundColor(context.getResources().getColor(
                    R.color.configuration_grey));
            choiced_checked.setChecked(true);
        }
        if (Color.equals("7")) {
            choiced.setBackgroundColor(context.getResources().getColor(
                    R.color.configuration_yellow));
            choiced_checked.setChecked(true);
        }
        if (Color.equals("8")) {
            choiced.setBackgroundColor(context.getResources().getColor(
                    R.color.configuration_orange));
            choiced_checked.setChecked(true);
        }
        if (Color.equals("9")) {
            choiced.setBackgroundColor(context.getResources().getColor(
                    R.color.configuration_brown));
            choiced_checked.setChecked(true);
        }
        if (Color.equals("10")) {
            choiced.setBackgroundColor(context.getResources().getColor(
                    R.color.configuration_purpe));
            choiced_checked.setChecked(true);
        }
        if (Color.equals("100")) {
            choiced.setBackgroundColor(context.getResources().getColor(
                    R.color.transparent));
            choiced_checked.setChecked(true);
        }
        this.color = Color;
        configuration_km.setText(DisplayKm);
        configuration_miles.setText(DisplayMiles);
        if (InstrumentIsChanged.equals("1")) {
            InstrumentIsChanged_radiobtn.setChecked(true);
        }
        if (InstrumentIsChanged.equals("0")) {
            InstrumentIsChanged_radiobtn.setChecked(false);
        }

        GuidePrice_edit.setText(GuidePrice);
        LowestPrice_edit.setText(LowestPrice);

        UsedPriceLow_edit.setText(UsedPriceLow);
        UsedPriceHigh_edit.setText(UsedPriceHigh);

        this.photostr1 = Photo1;
        bm1 = PictureUtil.stringtoBitmap(Photo1);
        photo1.setImageBitmap(bm1);

        this.photostr2 = Photo2;
        bm2 = PictureUtil.stringtoBitmap(Photo2);
        if (bm2 != null) {
            photo2.setImageBitmap(bm2);
        }

        this.photostr3 = Photo3;
        bm3 = PictureUtil.stringtoBitmap(Photo3);
        if (bm3 != null) {
            photo3.setImageBitmap(bm3);
        }

        this.photostr4 = Photo4;
        bm4 = PictureUtil.stringtoBitmap(Photo4);
        if (bm4 != null) {
            photo4.setImageBitmap(bm4);
        }

        this.photostr5 = Photo5;
        bm5 = PictureUtil.stringtoBitmap(Photo5);
        if (bm5 != null) {
            photo5.setImageBitmap(bm5);
        }

        this.photostr6 = Photo6;
        bm6 = PictureUtil.stringtoBitmap(Photo6);
        if (bm6 != null) {
            photo6.setImageBitmap(bm6);
        }

        this.photostr7 = Photo7;
        bm7 = PictureUtil.stringtoBitmap(Photo7);
        if (bm7 != null) {
            photo7.setImageBitmap(bm7);
        }

        this.photostr8 = Photo8;
        bm8 = PictureUtil.stringtoBitmap(Photo8);
        if (bm8 != null) {
            photo8.setImageBitmap(bm8);
        }

        this.photostr9 = Photo9;
        bm9 = PictureUtil.stringtoBitmap(Photo9);
        if (bm9 != null) {
            photo9.setImageBitmap(bm9);
        }

        this.photostr10 = Photo10;
        bm10 = PictureUtil.stringtoBitmap(Photo10);
        if (bm10 != null) {
            photo10.setImageBitmap(bm10);
        }

        this.photostr11 = Photo11;
        bm11 = PictureUtil.stringtoBitmap(Photo11);
        if (bm11 != null) {
            photo11.setImageBitmap(bm11);
        }

        this.photostr12 = Photo12;
        bm12 = PictureUtil.stringtoBitmap(Photo12);
        if (bm12 != null) {
            photo12.setImageBitmap(bm12);
        }

        this.photostr13 = Photo13;
        bm13 = PictureUtil.stringtoBitmap(Photo13);
        if (bm13 != null) {
            photo13.setImageBitmap(bm13);
        }

        this.photostr14 = Photo14;
        bm14 = PictureUtil.stringtoBitmap(Photo14);
        if (bm14 != null) {
            photo14.setImageBitmap(bm14);
        }

        this.photostr15 = Photo15;
        bm15 = PictureUtil.stringtoBitmap(Photo15);
        if (bm15 != null) {
            photo15.setImageBitmap(bm15);
        }

        this.photostr16 = Photo16;
        bm16 = PictureUtil.stringtoBitmap(Photo16);
        if (bm16 != null) {
            photo16.setImageBitmap(bm16);
        }

        this.photostr17 = Photo17;
        bm17 = PictureUtil.stringtoBitmap(Photo17);
        if (bm17 != null) {
            photo17.setImageBitmap(bm17);
        }

        this.photostr18 = Photo18;
        bm18 = PictureUtil.stringtoBitmap(Photo18);
        if (bm18 != null) {
            photo18.setImageBitmap(bm18);
        }

    }


    /**
     * 保存数据到本地数据库
     *
     * @return 是否成功
     */
    public boolean saveData() {
        if (InstrumentIsChanged_radiobtn.isChecked()) {
            InstrumentIsChanged = "1";
        } else {
            InstrumentIsChanged = "0";
        }
        Log.e("CarConfigurationDetailActivity。java saveData()", "保存车型登记信息");
        ChexingDengjiDB db = new ChexingDengjiDB(context);
        long rowID = db.insert(new AppPreference(context).getTaskId(),
                xinghaoID,
                oc,
                mc,
                color,
                configuration_km.getText().toString(),
                configuration_miles.getText().toString(),
                InstrumentIsChanged,
                GuidePrice_edit.getText().toString(),
                LowestPrice_edit.getText().toString(),
                UsedPriceLow_edit.getText().toString(),
                UsedPriceHigh_edit.getText().toString(),
                photostr1,
                photostr2,
                photostr3,
                photostr4,
                photostr5,
                photostr6,
                photostr7,
                photostr8,
                photostr9,
                photostr10,
                photostr11,
                photostr12,
                photostr13,
                photostr14,
                photostr15,
                photostr16,
                photostr17,
                photostr18
        );
        db.close();
        Log.e("CarConfigurationDetailActivity。java saveData()", "NEW ROW ID:" + rowID);
        if (rowID != -1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0 && event.getAction() == KeyEvent.ACTION_DOWN) {
            saveData();
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bm1 != null) {
            if (!bm1.isRecycled()) {
                bm1.recycle();
                bm1 = null;
            }
        }
        if (bm2 != null) {
            if (!bm2.isRecycled()) {
                bm2.recycle();
                bm2 = null;
            }
        }
        if (bm3 != null) {
            if (!bm3.isRecycled()) {
                bm3.recycle();
                bm3 = null;
            }
        }
        if (bm4 != null) {
            if (!bm4.isRecycled()) {
                bm4.recycle();
                bm4 = null;
            }
        }
        if (bm5 != null) {
            if (!bm5.isRecycled()) {
                bm5.recycle();
                bm5 = null;
            }
        }
        if (bm6 != null) {
            if (!bm6.isRecycled()) {
                bm6.recycle();
                bm6 = null;
            }
        }
        if (bm7 != null) {
            if (!bm7.isRecycled()) {
                bm7.recycle();
                bm7 = null;
            }
        }
        if (bm8 != null) {
            if (!bm8.isRecycled()) {
                bm8.recycle();
                bm8 = null;
            }
        }
        if (bm9 != null) {
            if (!bm9.isRecycled()) {
                bm9.recycle();
                bm9 = null;
            }
        }
        if (bm10 != null) {
            if (!bm10.isRecycled()) {
                bm10.recycle();
                bm10 = null;
            }
        }
        if (bm11 != null) {
            if (!bm11.isRecycled()) {
                bm11.recycle();
                bm11 = null;
            }
        }
        if (bm12 != null) {
            if (!bm12.isRecycled()) {
                bm12.recycle();
                bm12 = null;
            }
        }
        if (bm13 != null) {
            if (!bm13.isRecycled()) {
                bm13.recycle();
                bm13 = null;
            }
        }
        if (bm14 != null) {
            if (!bm14.isRecycled()) {
                bm14.recycle();
                bm14 = null;
            }
        }
        if (bm15 != null) {
            if (!bm15.isRecycled()) {
                bm15.recycle();
                bm15 = null;
            }
        }
        if (bm16 != null) {
            if (!bm16.isRecycled()) {
                bm16.recycle();
                bm16 = null;
            }
        }
        if (bm17 != null) {
            if (!bm17.isRecycled()) {
                bm17.recycle();
                bm17 = null;
            }
        }
        if (bm18 != null) {
            if (!bm18.isRecycled()) {
                bm18.recycle();
                bm18 = null;
            }
        }
        System.gc();
    }


    /**
     * 读取该型号的相关信息，初始化控件
     */
    private void initUIDataFromServer() {

        final SoapObject soapParameter = new SoapObject(SoapAction.nameSpace, "GetResourceModel");
        soapParameter.addProperty("ResourceID", new AppPreference(context).getTaskId());

        myhandler = new MyHandler() {
            @Override
            public void success(Message msg) {
                backSoapObject = mgr.getServiceBackSoapObject();
                if (backSoapObject != null) {
                    String Retcode = ((SoapObject) backSoapObject.getProperty(0)).getProperty("Retcode").toString();
                    //0成功，1失败
                    if (Retcode.equals("0")) {
                        String MinPrice = ((SoapObject) ((SoapObject) backSoapObject.getProperty(0)).getProperty("ResourceModel")).getProperty("LowestPrice").toString();
                        String GuidePrice = ((SoapObject) ((SoapObject) backSoapObject.getProperty(0)).getProperty("ResourceModel")).getProperty("GuidePrice").toString();
                        String UsedMinPrice = ((SoapObject) ((SoapObject) backSoapObject.getProperty(0)).getProperty("ResourceModel")).getProperty("UsedPriceLow").toString();
                        String UsedMaxPrice = ((SoapObject) ((SoapObject) backSoapObject.getProperty(0)).getProperty("ResourceModel")).getProperty("UsedPriceHigh").toString();
                        UsedPriceLow_edit.setText(UsedMinPrice.equals("anyType") ? "" : UsedMinPrice);
                        UsedPriceHigh_edit.setText(UsedMaxPrice.equals("anyType") ? "" : UsedMaxPrice);

                        GuidePrice_edit.setText(GuidePrice.equals("anyType") ? "" : GuidePrice);
                        LowestPrice_edit.setText(MinPrice.equals("anyType") ? "" : MinPrice);

                    } else {
                        final MineAlert alert = new MineAlert(context);
                        alert.createAlertOneButton(false, "获取该车型价格失败",
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
                SoapAction.GetResourceModel,
                "GetResourceModel",
                soapParameter,
                myhandler,
                true,
                false
        );
    }


}
