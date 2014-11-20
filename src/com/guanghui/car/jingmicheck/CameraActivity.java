package com.guanghui.car.jingmicheck;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.guanghui.car.R;
import com.guanghui.car.jingmicheck.util.FileUtil;

/**
 * Created by b.xu on 14-4-8.
 * 照相的activity
 */
public class CameraActivity extends Activity implements View.OnClickListener {

    private Button takePhotoBtn = null;
    private LinearLayout cameraLayout = null;
    private Camera camera = null;
    // 继承surfaceView的自定义view 用于存放照相的图片
    private CameraView cv = null;
    private boolean isFocusing = false;
    private Camera.AutoFocusCallback back = new Camera.AutoFocusCallback() {
        @Override
        public void onAutoFocus(boolean success, Camera camera) {
            camera.takePicture(null, null, picture);
        }

    };

    // 回调用的picture，实现里边的onPictureTaken方法，其中byte[]数组即为照相后获取到的图片信息
    private Camera.PictureCallback picture = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            BufferedOutputStream stream = null;
            String sdCardPath = Environment.getExternalStorageDirectory().getPath();
            Intent it = new Intent();

            Bitmap bMap = BitmapFactory.decodeByteArray(data, 0, data.length);
            Bitmap bMapRotate;
            Matrix matrix = new Matrix();
            matrix.reset();
            matrix.postRotate(90);
            bMapRotate = Bitmap.createBitmap(bMap, 0, 0, bMap.getWidth(),
                    bMap.getHeight(), matrix, true);
            try {
//                ByteArrayOutputStream out = new ByteArrayOutputStream();
//                int n = 90;
//                bMapRotate.compress(Bitmap.CompressFormat.JPEG, n, out);//将图片压缩到流中
//                System.out.println("out尺寸:" + out.size());
//                while(out.size()>110000){
//                    n = n-10;
//                    System.out.println("out尺寸:" + out.size());
//                    out.reset();
//                    System.out.println("再次压缩:"+n);
//                    bMapRotate.compress(Bitmap.CompressFormat.JPEG, n, out);//将图片压缩到流中
//            }
            	
//            	  BitmapFactory.Options options = new BitmapFactory.Options();
//         	      options.inJustDecodeBounds = false;
//         	      options.inSampleSize = 2;
//         	      BitmapFactory.decodeFile(filePath, options);
            	
                
                
               
                
                
                
                  ByteArrayOutputStream baos = new ByteArrayOutputStream();
                  bMapRotate.compress(Bitmap.CompressFormat.JPEG, 60, baos);
	      	      byte[] b = baos.toByteArray();
	      	      String img64 =  Base64.encodeToString(b, Base64.DEFAULT);
	      	      FileUtil.printToNewFile(img64, "img.txt");
                
                
                
//                System.out.println("保存图片大小:" + out.size());
//                String img64 = Base64.encodeToString(out.toByteArray(), Base64.DEFAULT);
//                FileUtil.printToNewFile(img64, "img.txt");
                it.putExtra("img", img64);
                setResult(Activity.RESULT_OK, it);
                finish();
            } catch (Exception e) {
                e.printStackTrace();
                finish();
            } finally {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }

    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.camera_layout);

        cameraLayout = (LinearLayout) findViewById(R.id.cameraView);
        takePhotoBtn = (Button) findViewById(R.id.btn2);

        takePhotoBtn.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        cv = new CameraView(CameraActivity.this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        cameraLayout.addView(cv, params);
    }

    class CameraView extends SurfaceView {

        //
        private SurfaceHolder holder = null;

        public CameraView(Context context) {
            super(context);
            holder = this.getHolder();

            holder.addCallback(new SurfaceHolder.Callback() {

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format,
                                           int width, int height) {
                    Camera.Parameters parameters = camera.getParameters();
                    parameters.setPictureFormat(PixelFormat.JPEG);
//                    parameters.setPreviewSize(parameters.getPictureSize().width,
//                            parameters.getPictureSize().height);
                    parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                    parameters.setPictureSize(width, height);
                    camera.setParameters(parameters);
                    camera.startPreview();
                }

                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    camera = Camera.open();

                    try {
                        //设置camera预览的角度，因为默认图片是倾斜90度的
                        camera.setDisplayOrientation(90);
                        camera.setPreviewDisplay(holder);
                    } catch (IOException e) {
                        camera.release();
                        camera = null;
                        e.printStackTrace();
                    }

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    camera.stopPreview();
                    camera.release();
                    camera = null;
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        if(!isFocusing){
            isFocusing = true;
            camera.autoFocus(back);
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }
}

