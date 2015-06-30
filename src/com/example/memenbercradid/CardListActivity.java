package com.example.memenbercradid;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * @author SJ
 *  
 *  实现起来就是两个动画
 *  listview和自定义MyCard
 *  listview下滑消失，记录点击ItemView位置
 *  MyCard根据ItemView的位置上滑显示
 */
public class CardListActivity extends Activity {

    private RelativeLayout title;
    
    private LinearLayout ll_one;
    private ListView lv_show;
    private RelativeLayout ll_two;
    private Button button_bottom;//底部点击返回ListView
    
    private ImageView back_iv;
    
    private AdatperLv adapterLv;
    
    private Card mycard;
    
    private ArrayList<String> list = new ArrayList<String>();
    int screenWidth;
    int screenHeigh;
    int statusBarHeight;
    
    private boolean bottonShow;
    
    private View clickView ;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_list_activity);
        getData();
        initLayout();
    }

    private void getData(){
        
        
        list = new ArrayList<String>();
        list.add("巴西烤肉");
        list.add("西贝");
        list.add("海底捞");
        list.add("韩国烤肉");
        list.add("日本料理");
        list.add("东北菜");
        list.add("辣的爽");
        list.add("好再来");
        list.add("KFC");
        
    }
    
    private void initLayout(){
        title = (RelativeLayout) this.findViewById(R.id.title);
        ll_one = (LinearLayout) this.findViewById(R.id.ll_one);
        lv_show = (ListView) this.findViewById(R.id.lv_show);
        ll_two = (RelativeLayout) this.findViewById(R.id.ll_two);
        button_bottom = (Button) this.findViewById(R.id.button_bottom);
        mycard = (Card)this.findViewById(R.id.mycard);
        
        back_iv = (ImageView) this.findViewById(R.id.back_iv);
        back_iv.setOnClickListener(new backClick());
        
        adapterLv = new AdatperLv(this, list);
        lv_show.setAdapter(adapterLv);
        
        lv_show.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int postion, long arg3) {
                
            	//设置点击数据
                mycard.setData(postion,list.get(postion));
                
                System.out.println("====>屏幕宽"+screenWidth+"  屏幕高"+screenHeigh+"  控件底部"+(statusBarHeight+title.getBottom()));
                System.out.println("screenHeigh===>"+(screenHeigh));
                if (bottonShow) {
                    button_bottom.setVisibility(View.GONE);
                }
                System.out.println("!!!!!=>"+(screenHeigh-statusBarHeight-title.getBottom()));

                clickView = view;
                   
                TranslateAnimation anima = new TranslateAnimation(0, 0,0,screenHeigh- ll_one.getTop()-statusBarHeight-title.getBottom());//(screenHeigh-(iv_show.getTop()+(iv_show.getBottom()-iv_show.getTop())/2))+((iv_show.getBottom()-iv_show.getTop())/2)
               
                anima.setDuration(100);
                anima.setFillAfter(true);
                ll_one.startAnimation(anima);//List的动画效果
                
                TounchAnimo(ll_two);
                
                anima.setAnimationListener(new AnimationListener() {
                    
                    @Override
                    public void onAnimationStart(Animation animation) {
                        
                    }
                    
                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                    
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        ll_one.setVisibility(View.GONE);
                        ll_one.clearAnimation();
                    }
                });
            }
        });
    }
    
    
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeigh = dm.heightPixels;
        
        bottonShow = false;
        Rect rect = new Rect(); 
        Window win = CardListActivity.this.getWindow(); 
        win.getDecorView().getWindowVisibleDisplayFrame(rect); 
        statusBarHeight = rect.top;
        System.out.println("onWindowFocusChanged----"+statusBarHeight);
        
        
        int[] location = new int[2];
        lv_show.getLocationInWindow(location);
        
        title.getLocationInWindow(location);
        
        ll_one.getLocationInWindow(location);
        
        if (screenHeigh-lv_show.getBottom()- title.getBottom() - statusBarHeight< 20) {
            button_bottom.setVisibility(View.VISIBLE);
            bottonShow = true;
        }
    }

    public void TounchAnimo(View layout){
        TranslateAnimation anima;
        
        int[] location = new int[2];
        clickView.getLocationInWindow(location);
        System.out.println("View===>"+clickView.getTop());

        anima = new TranslateAnimation(0, 0,clickView.getTop()+statusBarHeight, 0);//从listView下面的imagView上什
        
        anima.setDuration(500);
        anima.setFillAfter(true);
        ll_two.setAnimation(anima);
        ll_two.startAnimation(anima);
        anima.setAnimationListener(new AnimationListener() {
            
            @Override
            public void onAnimationStart(Animation animation) {
                ll_two.setVisibility(View.VISIBLE);
                
            }
            
            @Override
            public void onAnimationRepeat(Animation animation) {
                
            }
            
            @Override
            public void onAnimationEnd(Animation animation) {
                ll_two.clearAnimation();
            }
        });
    }
    
    class backClick implements OnClickListener{

        @Override
        public void onClick(View view) {
            
            
            if (bottonShow) {
                button_bottom.setVisibility(View.VISIBLE);
            }
            
            ll_two.clearAnimation();
            ll_two.setVisibility(View.GONE);
            ll_one.setVisibility(View.VISIBLE);
           
            TranslateAnimation anima = new TranslateAnimation(0, 0,screenHeigh- 40-statusBarHeight, 0);
            anima.setDuration(500);
            anima.setFillAfter(true);
            ll_one.startAnimation(anima);
            anima.setAnimationListener(new AnimationListener() {
                
                @Override
                public void onAnimationStart(Animation animation) {
                    
                }
                
                @Override
                public void onAnimationRepeat(Animation animation) {
                    
                }
                
                @Override
                public void onAnimationEnd(Animation animation) {
                    ll_one.clearAnimation();
                }
            });
        }
        
    }
}
