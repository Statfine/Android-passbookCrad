package com.example.memenbercradid;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AdatperLv extends BaseAdapter {
    private Context mContxet;
    private ArrayList<String> list;
    private LayoutInflater mInflater;
    
    private int[] bac;

    public AdatperLv(Context contxet,ArrayList<String> list){
        this.mContxet = contxet;
        this.list = list;
        
        bac = new int[]{R.drawable.preferential_b1,R.drawable.preferential_b2,R.drawable.preferential_b3,
                R.drawable.preferential_b4,R.drawable.preferential_b5};
        mInflater = (LayoutInflater) mContxet.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        
    }
    
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.adapterlv, null);
        
        RelativeLayout adapter_ll = (RelativeLayout)convertView.findViewById(R.id.adapter_ll);
        adapter_ll.setBackgroundResource(bac[position%5]);
        TextView adapter_tv = (TextView)convertView.findViewById(R.id.adapter_tv);
        adapter_tv.setText(list.get(position));
        
        ImageView rl_iv_xiaoguo = (ImageView)convertView.findViewById(R.id.rl_iv_xiaoguo);
        if (list.size()-1 == position) {
            rl_iv_xiaoguo.setVisibility(View.GONE);
        }else{
            rl_iv_xiaoguo.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

}
