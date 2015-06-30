package com.example.memenbercradid;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author SJ
 *  
 *  单张卡片显示的布局
 */
public class Card extends LinearLayout {
    
    LinearLayout my_ll_show;
    TextView tv_botton_show;
    TextView tv_name;
    
    int[] drawT;
    int[] drawB;

    public Card(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.card, this);
        
        initLayout();
    }
    
    private void initLayout(){
        my_ll_show = (LinearLayout)findViewById(R.id.my_ll_show);
        tv_botton_show = (TextView)findViewById(R.id.tv_botton_show);
        tv_name = (TextView)findViewById(R.id.tv_name);
        drawT = new int[]{R.drawable.preferential_1_t,R.drawable.preferential_2_t,R.drawable.preferential_3_t,
                R.drawable.preferential_4_t,R.drawable.preferential_5_t};
        
        drawB =  new int[]{R.drawable.preferential_1_b,R.drawable.preferential_2_b,R.drawable.preferential_3_b,
                R.drawable.preferential_4_b,R.drawable.preferential_5_b};
   
    }
    
    public void setData(int posation,String name){
        my_ll_show.setBackgroundResource(drawT[posation%5]);
        tv_botton_show.setBackgroundResource(drawB[posation%5]);
        tv_name.setText(name);
    }

}
