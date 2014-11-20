package com.guanghui.car.jingmicheck.widget;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.guanghui.car.R;
import com.guanghui.car.application.MyApplication;
import com.guanghui.car.jingmicheck.model.CheckItem;
import com.guanghui.car.jingmicheck.util.FileUtil;

public class CheckView extends View {

    public static Bitmap sNoDefectBitmap;
    public static Bitmap sHasDefectBitmap;

    private ArrayList<Path> m_paths = new ArrayList<Path>();
    private ArrayList<Region> m_regions = new ArrayList<Region>();
    private ArrayList<Point> mPoints = new ArrayList<Point>();
    private Paint paint;
    private boolean isAddModel = false;
    private float mMoveX, mMoveY;
    private int selectIndex = -1;

    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    private ArrayList<CheckItem> mHotItems;
    private CheckStateListener mCheckListener = null;
    private DecimalFormat df = new DecimalFormat("0.000");
    private int addPointCache  = 0;

    public interface CheckStateListener {
        void onItemSelect(CheckItem item);
    }

    public void setCheckListener(CheckStateListener listener) {
        this.mCheckListener = listener;
    }

    public CheckView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);

    }

    public CheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CheckView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        paint.setStrokeWidth(5);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Align.LEFT);
        mPaint.setStyle(Style.STROKE);
        mPaint.setColor(Color.RED);
        if (isInEditMode()) {
            return;
        }

        if (sNoDefectBitmap == null) {
            sNoDefectBitmap = BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), R.drawable.icon_nocheck);
        }
        if (sHasDefectBitmap == null) {
            sHasDefectBitmap = BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), R.drawable.icon_hasdefect);
        }
    }

    public void setItems(ArrayList<CheckItem> items) {
        mHotItems = items;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isAddModel) {
            drawPath(canvas);
            drawAddedLines(canvas);
            drawMoveLine(canvas);
            drawPointLocation(canvas);
        } else {
            drawItems(canvas);
        }

    }

    private void drawPointLocation(Canvas canvas) {
        canvas.drawLine(0, mMoveY, mWidth, mMoveY, mPaint);
        canvas.drawLine(mMoveX, 0, mMoveX, mHeight, mPaint);
        canvas.drawText(df.format((float) mMoveX / (float) mWidth) + "--" + df.format((float) mMoveY / (float) mHeight), 100, 20, mPaint);
        canvas.drawText(mMoveX + "--" + mMoveY, 100, 50, mPaint);
    }

    private void drawMoveLine(Canvas canvas) {
        paint.setColor(Color.GRAY);
        if (mPoints.size() == 0) {
            return;
        }
        if (mMoveX == 0) {
            return;
        }
        Point p = mPoints.get(mPoints.size() - 1);
        canvas.drawLine(p.x, p.y, mMoveX, mMoveY, paint);
    }

    private void drawAddedLines(Canvas canvas) {
        paint.setColor(Color.GRAY);
        int size = mPoints.size();
        for (int i = 0; i < size; i++) {
            if (i < size - 1) {
                Point p = mPoints.get(i);
                Point np = mPoints.get(i + 1);
                canvas.drawLine(p.x, p.y, np.x, np.y, paint);
            }

        }
    }

    private void drawPath(Canvas canvas) {
        paint.setColor(Color.BLUE);
        int size = m_paths.size();
        for (int i = 0; i < size; i++) {
            Path p = m_paths.get(i);
            if (i == selectIndex) {
                paint.setColor(Color.RED);
                canvas.drawPath(p, paint);
            } else {
                paint.setColor(Color.BLUE);
            }
        }
    }

    public void setAddModel() {
        isAddModel = true;
//        if (!b) {
//            addPathAndClear();
//        }
    }

    public void addPathAndClear() {
        Path path = new Path();
        int size = mPoints.size();
        FileUtil.printToFile("\n<<<<<<添加一个热区>>>>>","hotarea.txt");
        for (int i = 0; i < size; i++) {
            Point p = mPoints.get(i);
            if (i == 0) {
                path.moveTo(p.x, p.y);
                FileUtil.printToFile("p.moveTo(getGujia_width(" + df.format((float) p.x / (float) mWidth) + "f), getGujia_height(" + df.format((float) p.y / (float) mHeight) + "f));", "hotarea.txt");
            } else {
                path.lineTo(p.x, p.y);
                FileUtil.printToFile("p.lineTo(getGujia_width(" + df.format((float) p.x / (float) mWidth) + "f), getGujia_height(" + df.format((float) p.y / (float) mHeight) + "f));", "hotarea.txt");
            }
        }
        path.close();
        m_paths.add(path);
        Region region = new Region();
        region.setPath(path, new Region(0, 0, mWidth, mHeight));
        m_regions.add(region);
        startAddPoint();
    }

    private void drawItems(Canvas canvas) {
        System.out.println("drawItems");
        if (mHotItems == null) {
            return;
        }
        int size = mHotItems.size();
        if (size == 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            CheckItem item = mHotItems.get(i);
            if (item.path != null) {
//                canvas.drawPath(item.path, mPaint);
                if (item.model.hasDefect) {
                    canvas.drawBitmap(sHasDefectBitmap, item.imgX - sHasDefectBitmap.getWidth() / 2, item.imgY - sHasDefectBitmap.getHeight() / 2, mPaint);
                } else {
                    canvas.drawBitmap(sNoDefectBitmap, item.imgX - sNoDefectBitmap.getWidth() / 2, item.imgY - sNoDefectBitmap.getHeight() / 2, mPaint);
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (isAddModel) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    mMoveX = x;
                    mMoveY = y;
                    break;
                case MotionEvent.ACTION_UP:
                    if(addPointCache>0){
                        addPointCache(x,y);
                    }else{
                        mPoints.add(new Point(x, y));
                    }
                    break;
                default:
                    break;
            }

            invalidate();
        } else {
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    checkUpPosition(x, y);
                    break;
                case MotionEvent.ACTION_DOWN:
                default:
                    break;
            }
        }

        return true;
    }

    private void addPointCache(int x, int y) {
        if(addPointCache==2){
            FileUtil.printToFile("item.imgX = getGujia_width("+df.format((float) mMoveX / (float) mWidth) + "f);","hotarea.txt");
        }else{
            FileUtil.printToFile("item.imgY = getGujia_height(" + df.format((float) mMoveY / (float) mHeight)+"f);","hotarea.txt");
        }
        addPointCache--;
    }

    public void startAddPoint(){
        addPointCache = 2;
        mPoints.clear();
        mMoveX = 0;
        mMoveY = 0;
        invalidate();
    }

    // private void checkDownPosition(int x, int y) {
    // if (mHotItems == null) {
    // return;
    // }
    // int size = mHotItems.size();
    // if (size == 0) {
    // return;
    // }
    // for (int i = 0; i < size; i++) {
    // CheckItem item = mHotItems.get(i);
    // if (item.region.contains(x, y)) {
    // invalidate();
    // return;
    // }
    // }
    // }

    private void checkUpPosition(int x, int y) {
        if (mHotItems == null) {
            return;
        }
        int size = mHotItems.size();
        if (size == 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            CheckItem item = mHotItems.get(i);
            if (item.region != null) {
                if (item.region.contains(x, y)) {
                    if (mCheckListener != null) {
                        mCheckListener.onItemSelect(item);
                    }
                    return;
                }
            }
        }
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        mHeight = MeasureSpec.getSize(heightMeasureSpec);

        mWidth = MeasureSpec.getSize(widthMeasureSpec);

        System.out.println("mHeight:" + mHeight + ":mWidth:" + mWidth);
        setMeasuredDimension(mWidth, mHeight);

    }

}
