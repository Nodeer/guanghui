package com.guanghui.car;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.guanghui.car.communications.MineAlert;

/**
 * 拍照
 *
 * @author zhangyun
 */
public class TakePhotoActivity extends Activity implements SurfaceHolder.Callback {

    private Context context = this;
    private static String fileName;
    private static String fileDir;
    private SurfaceView surfaceView;   //相机画布
    private SurfaceHolder surfaceHolder;
    private Button takePicView, exitView;

    private Camera mCamera;    //照相机
    private String title;

    private RelativeLayout tabke_photo_ly_1;
    private RelativeLayout tabke_photo_ly_2;
    private TextView take_photo_activity_name2;


    private int ImageWidth = 640;
    private int ImageHeight = 480;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_photo_activity);

        fileName = this.getIntent().getStringExtra("fileName");
        fileDir = this.getIntent().getStringExtra("fileDir");
        title = this.getIntent().getStringExtra("title");

        tabke_photo_ly_1 = (RelativeLayout) this.findViewById(R.id.tabke_photo_ly_1);
        tabke_photo_ly_2 = (RelativeLayout) this.findViewById(R.id.tabke_photo_ly_2);

        take_photo_activity_name2 = (TextView) this.findViewById(R.id.take_photo_activity_name2);
        if (title.equals("行驶证")) {

            tabke_photo_ly_2.setVisibility(View.VISIBLE);
            tabke_photo_ly_1.setVisibility(View.GONE);
            take_photo_activity_name2.setVisibility(View.VISIBLE);
            ((TextView) this.findViewById(R.id.take_photo_activity_name)).setText(title);
        } else {
            tabke_photo_ly_1.setVisibility(View.VISIBLE);
            tabke_photo_ly_2.setVisibility(View.GONE);
            take_photo_activity_name2.setVisibility(View.GONE);
            ((TextView) this.findViewById(R.id.take_photo_activity_name)).setText(title);
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);  //横屏


        ImageWidth = getIntent().getIntExtra("ImageWidth", 640);
        ImageHeight = getIntent().getIntExtra("ImageHeight", 480);


        Log.e("ImageWidth", ImageWidth + "");
        Log.e("ImageHeight", ImageHeight + "");

        /**
         * 获取Button并且设置事件监听
         */
        takePicView = (Button) this.findViewById(R.id.takepic);
        takePicView.setOnClickListener(TakePicListener);
        exitView = (Button) this.findViewById(R.id.exit);
        exitView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        surfaceView = (SurfaceView) this.findViewById(R.id.surface_camera);
        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        surfaceView.getHolder().setFixedSize(ImageWidth, ImageHeight); //设置Surface分辨率


        surfaceView.getHolder().setKeepScreenOn(true);// 屏幕常亮  


        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);


        surfaceView.setZOrderOnTop(false);
        surfaceHolder.setFormat(PixelFormat.TRANSPARENT);


        checkSoftStage();  //首先检测SD卡是否存在
    }

    /**
     * 检测手机是否存在SD卡,网络连接是否打开
     */
    private void checkSoftStage() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {  //判断是否存在SD卡
            File file = new File(fileDir);
            if (!file.exists()) {
                file.mkdir();
            }
        } else {
            new AlertDialog.Builder(this).setMessage("检测到手机没有存储卡！请插入手机存储卡再开启本应用。")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).show();
        }
    }

    /**
     * 点击拍照按钮,启动拍照
     */
    private final OnClickListener TakePicListener = new OnClickListener() {
        @Override
        public void onClick(View v) {

            try {
                surfaceHolder = surfaceView.getHolder();
                surfaceHolder.addCallback(TakePhotoActivity.this);

                mCamera.autoFocus(new AutoFoucus());  //自动对焦
            } catch (Exception ex) {
                final MineAlert alert = new MineAlert(context);
                alert.createAlertOneButton(false, "相机异常，请重试!",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alert.dimiss();
                                finish();
                            }
                        });
            }

        }
    };

    /**
     * 自动对焦后拍照
     */
    private final class AutoFoucus implements AutoFocusCallback {
        @Override
        public void onAutoFocus(boolean success, Camera camera) {
            //if(success && mCamera!=null){
            if (mCamera != null) {
                try {
                    mCamera.takePicture(mShutterCallback, null, mPictureCallback);
                } catch (Exception ex) {
                    Log.e("takePicture error:", ex.getMessage());
                }
            }
        }
    }

    /**
     * 重点对象、 此处实例化了一个本界面的PictureCallback
     * 当用户拍完一张照片的时候触发，这时候对图片处理并保存操作。
     */
    private final PictureCallback mPictureCallback = new PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            try {
                File file = new File(fileDir, fileName);
                Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);

                //false的话失真
                Bitmap newBM = bm.createScaledBitmap(bm, ImageWidth, ImageHeight, true);

                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                newBM.compress(Bitmap.CompressFormat.JPEG, 60, bos);
                bos.flush();
                bos.close();

                if (bm != null) {
                    bm.recycle();
                    bm = null;
                }
                if (newBM != null) {
                    newBM.recycle();
                    newBM = null;
                }

                Intent intent = new Intent();
                intent.putExtra("path", fileDir + "/" + fileName);
                TakePhotoActivity.this.setResult(Activity.RESULT_OK, intent);
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    /**
     * 在相机快门关闭时候的回调接口，通过这个接口来通知用户快门关闭的事件，
     * 普通相机在快门关闭的时候都会发出响声，根据需要可以在该回调接口中定义各种动作， 例如：使设备震动
     */
    private final ShutterCallback mShutterCallback = new ShutterCallback() {
        public void onShutter() {
            Log.d("ShutterCallback", "...onShutter...");
        }
    };

    @Override
    /**
     * 初始化相机参数，比如相机的参数: 像素, 大小,格式
     */
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

        mCamera.setDisplayOrientation(0);

        Camera.Parameters param = mCamera.getParameters();


        param.setPictureFormat(PixelFormat.JPEG);
        param.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        param.setPreviewFrameRate(5);  //设置每秒显示4帧
        param.setJpegQuality(100); // 设置照片质量

        mCamera.setParameters(param);

    }

    @Override
    /**
     * 打开相机,设置预览
     */
    public void surfaceCreated(SurfaceHolder holder) {

        try {
            mCamera = Camera.open(); // 打开摄像头
            mCamera.setPreviewDisplay(holder); // 设置用于显示拍照影像的SurfaceHolder对象
            mCamera.startPreview(); // 开始预览
        } catch (Exception e) {
            mCamera.release();
            mCamera = null;
        }
    }

    @Override
    /**
     * 预览界面被关闭时，或者停止相机拍摄;释放相机资源
     */
    public void surfaceDestroyed(SurfaceHolder holder) {
        mCamera.stopPreview();
        if (mCamera != null) {
            mCamera.release(); // 释放照相机
            mCamera = null;
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
    }


}