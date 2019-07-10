package com.brandongogetap.stickyheaders.demo;
import android.app.ActionBar.LayoutParams;
import android.content.Context;

import android.text.method.ReplacementTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ViewPagerAdapter extends PagerAdapter {
    int[] imgArray = {R.drawable.one,R.drawable.two,R.drawable.one,R.drawable.two};;
    LayoutInflater inflater;
    Context context;
    int position = 3;

    public ViewPagerAdapter(MainActivity mainActivity) {
        this.context = mainActivity;

    }

    @Override
    public int getCount() {
        return imgArray.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView img;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemview = inflater.inflate(R.layout.item_viewpager, container, false);
        img = (ImageView) itemview.findViewById(R.id.ima1);
        img.setBackgroundResource(imgArray[position]);

        //add item.xml to viewpager
        ((ViewPager) container).addView(itemview);
        return itemview;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);
    }

   /* @Override
    public float getPageWidth(int position) {
        return .20f;   //it is used for set page widht of view pager
    }*/
}