

package com.guanghui.car ;


import android.app.Activity ;
import android.content.Context ;
import android.content.Intent ;
import android.os.Bundle ;
import android.view.View ;
import android.view.View.OnClickListener ;
import android.widget.Button ;

/**
 * 检测方式选择页面
 * @author 田大魁
 * */
public class CheckActivity extends Activity
{
    private Context mContext = CheckActivity.this;
    private Button check_fast_btn,check_precision_btn;
    @ Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_check);
        new BuildTab(mContext,1) ;
        init();
        initBottom();
    }
    private void init(){
        check_fast_btn = ( Button ) findViewById(R.id.check_fast_btn);
        check_fast_btn.setOnClickListener(clickListener);
        check_precision_btn = ( Button ) findViewById(R.id.check_precision_btn);
        check_precision_btn.setOnClickListener(clickListener);
    }
    OnClickListener clickListener = new OnClickListener()
    {
        
        @ Override
        public void onClick(View view)
        {
            switch ( view.getId() )
            {
                case R.id.check_fast_btn :
                    //快速检测按钮
                    Intent intent = new Intent(mContext,ShouXuDengJiAcitivity.class);
                    mContext.startActivity(intent);
                    break ;
                case R.id.check_precision_btn:
                    //精密检测按钮
                	Intent intent2 = new Intent(mContext,ShouXuDengJiAcitivity.class);
                    mContext.startActivity(intent2);
                    break;
                default :
                    break ;
            }
            
        }
    };
    
    private void initBottom()
    {
    	Button back = (( Button ) findViewById(R.id.stop_save));
    	back.setText("返回");
    	back.setOnClickListener(new View.OnClickListener()
        {
            @ Override
            public void onClick(View v)
            {
            	finish();
            }
        }) ;
        (( Button ) findViewById(R.id.comfirm_communit)).setVisibility(View.GONE);
    }
}
