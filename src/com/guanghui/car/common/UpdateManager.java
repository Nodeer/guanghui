package com.guanghui.car.common;

/**
 * 更新下载文件的类
 * @author zhangyun
 *
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.guanghui.car.R;
import com.guanghui.car.communications.MineAlert;
import com.guanghui.car.config.AppConfig;

public class UpdateManager
{
    /* 下载中 */
    private static final int DOWNLOAD = 1;
    /* 下载结束 */
    private static final int DOWNLOAD_FINISH = 2;
    /* 没插入SD卡 */
    private static final int NO_SDCARD = 3;
    /* 保存解析的XML信息 */
    HashMap<String, String> mHashMap;
    /* 下载保存路径 */
    private String mSavePath;
    /* 记录进度条数量 */
    private int progress;
    /* 是否取消更新 */
    private boolean cancelUpdate = false;

    private Context mContext;
    /* 更新进度条 */
    private ProgressBar mProgress;
    private AlertDialog dialog;
    
    private Handler handler;

    private Handler mHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
            // 正在下载
            case DOWNLOAD:
                // 设置进度条位置
                mProgress.setProgress(progress);
                break;
            case DOWNLOAD_FINISH:
                // 安装文件
                installApk();
                break;
            case NO_SDCARD:
            	final MineAlert alert = new MineAlert(mContext);
				alert.createAlertOneButton(false,"未插入SD卡！", 
						new View.OnClickListener() {								
							@Override
							public void onClick(View v) {
								alert.dimiss();
							}
						});	
                break;
            default:
                break;
            }
        };
    };

    public UpdateManager(Context context,HashMap<String, String> mHashMap,Handler mhandler)
    {
        this.mContext = context;
        this.mHashMap = mHashMap;
        this.handler = mhandler;
    }

    /**
     * 显示软件下载对话框
     */
    public void showDownloadDialog()
    {       
        dialog = new AlertDialog.Builder(mContext).create();                    
        dialog.show();
        dialog.getWindow().setContentView(R.layout.softupdate_progress);             
        mProgress = (ProgressBar) dialog.findViewById(R.id.update_progress);
        // 取消更新 
        ((Button) dialog.findViewById(R.id.softupdate_progress_cancel)).setOnClickListener(new View.OnClickListener()
        {            
            @ Override
            public void onClick(View v)
            {
                dialog.dismiss();
                // 设置取消状态
                cancelUpdate = true;                
            }
        });
        // 下载文件
        downloadApk();
    }

    /**
     * 下载apk文件
     */
    private void downloadApk()
    {
        // 启动新线程下载软件
        new downloadApkThread().start();
    }

    /**
     * 下载文件线程    
     */
    private class downloadApkThread extends Thread
    {
        @Override
        public void run()
        {
            try
            {
                // 判断SD卡是否存在，并且是否具有读写权限
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
                {
                    // 获得存储卡的路径
                    //String sdpath = Environment.getExternalStorageDirectory() + "/";
                    mSavePath = AppConfig.CacheDir;
                    URL url = new URL(mHashMap.get("url"));
                    
                    // 创建连接
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();
                    // 获取文件大小
                    int length = conn.getContentLength();
                    // 创建输入流
                    InputStream is = conn.getInputStream();

                    File file = new File(mSavePath);
                    // 判断文件目录是否存在
                    if (!file.exists())
                    {
                        file.mkdir();
                    }
                    File apkFile = new File(mSavePath, mHashMap.get("name"));
                    FileOutputStream fos = new FileOutputStream(apkFile);
                    int count = 0;
                    // 缓存
                    byte buf[] = new byte[1024];
                    // 写入到文件中
                    do
                    {
                        int numread = is.read(buf);
                        count += numread;
                        // 计算进度条位置
                        progress = (int) (((float) count / length) * 100);
                        // 更新进度
                        mHandler.sendEmptyMessage(DOWNLOAD);
                        if (numread <= 0)
                        {
                            // 下载完成
                            mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                            break;
                        }
                        // 写入文件
                        fos.write(buf, 0, numread);
                    } while (!cancelUpdate);// 点击取消就停止下载.
                    fos.close();
                    is.close();
                }
                else
                {
                    mHandler.sendEmptyMessage(NO_SDCARD);
                    //不存在sd卡，告诉上层，继续其他操作
                    handler.sendEmptyMessage(0);
                }
            } catch (MalformedURLException e)
            {
                e.printStackTrace();
                //下载失败，告诉上层，继续其他操作
                handler.sendEmptyMessage(0);
            } catch (IOException e)
            {
                e.printStackTrace();
                //下载失败，告诉上层，继续其他操作
                handler.sendEmptyMessage(0);
            }
            // 取消下载对话框显示
            dialog.dismiss();
        }
    };

    /**
     * 安装APK文件
     */
    private void installApk()
    {
        File apkfile = new File(mSavePath + "/" + mHashMap.get("name"));
        Log.e("installApk", mSavePath + "/" + mHashMap.get("name"));
        
        if (!apkfile.exists())
        {
            return;
        }
        // 通过Intent安装APK文件
        Intent i = new Intent();
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setAction(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        mContext.startActivity(i);
    }
}