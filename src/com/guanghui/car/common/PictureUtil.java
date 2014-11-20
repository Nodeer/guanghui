package com.guanghui.car.common;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

/**
 * @author zhangyun
 *         TODO 澶勭悊鍥剧墖鍘嬬缉鐨勫伐鍏风被
 */
public class PictureUtil {
    private final static int compressVal = 75;

    public static String bitmapToString(String filePath, boolean yasuo) {
        Bitmap bm;
        if (yasuo) {
            bm = getSmallBitmap(filePath, 2);
        } else {
            bm = BitmapFactory.decodeFile(filePath);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 60, baos);
        byte[] b = baos.toByteArray();
        String str = Base64.encodeToString(b, Base64.DEFAULT);

        baos = null;
        b = null;

        bm.recycle();
        bm = null;

        return str;
    }


    /**
     * 鏍规嵁璺緞鑾峰緱绐佺牬骞跺帇缂╄繑鍥瀊itmap鐢ㄤ簬鏄剧ず
     *
     * @param filePath     鏂囦欢璺緞
     * @param inSampleSize 鏄剧ず鍑烘潵鍘嬬缉鐨勬瘮渚?
     * @return
     */
    public static Bitmap getSmallBitmap(String filePath, int inSampleSize) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        return BitmapFactory.decodeFile(filePath, options);
    }


    /**
     * 淇濆瓨鍒版湰鍦?
     *
     * @param bitmap   bitmap瀵硅薄
     * @param savePath 淇濆瓨鏂囦欢鐨勮矾寰?
     */
    public static void saveBitmap(Bitmap bitmap, String savePath) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        File file = new File(savePath);
        try {
            file.createNewFile();
            BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));

            bitmap.compress(Bitmap.CompressFormat.JPEG, compressVal, baos);
            os.write(baos.toByteArray());

            os.flush();
            os.close();
            os = null;

        } catch (IOException e) {
            Log.d("saveBitmap error:", e.getMessage());
        } finally {
            if (bitmap != null) {
                bitmap.recycle();
            }
        }
    }


    /**
     * 灏哠tring杞崲鎴怋itmap
     *
     * @param string
     * @return
     */
    public static Bitmap stringtoBitmap(String string) {
        //灏嗗瓧绗︿覆杞崲鎴怋itmap绫诲瀷
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
            bitmapArray = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


}
