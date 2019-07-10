package com.brandongogetap.stickyheaders.demo;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.brandongogetap.stickyheaders.StickyLayoutManager;
import com.brandongogetap.stickyheaders.exposed.StickyHeaderListener;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.brandongogetap.stickyheaders.demo.ViewPagerAdapter ;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    private RecyclerAdapter adapterR;

    ViewPager viewpager;
    PagerAdapter adapter;
    int[] img;

    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewpager = (ViewPager) findViewById(R.id.pager);
        adapter = new ViewPagerAdapter(MainActivity.this);
        viewpager.setAdapter(adapter);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(viewpager);
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    int pageCount = adapter.getCount();
                    if (currentPage == 0) {
                        viewpager.setCurrentItem(pageCount - 1, false);
                    } else if (currentPage == pageCount - 1) {
                        viewpager.setCurrentItem(0, false);
                    }
                }
            }
        });
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewpager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 2000, 2000);








        recyclerView = findViewById(R.id.recycler_view);

        adapterR = new RecyclerAdapter();
        adapterR.setData(ItemGenerator.demoList());
        StickyLayoutManager layoutManager = new TopSnappedStickyLayoutManager(this, adapterR);
        layoutManager.elevateHeaders(true); // Default elevation of 5dp
        // You can also specify a specific dp for elevation
//        layoutManager.elevateHeaders(10);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterR);
        layoutManager.setStickyHeaderListener(new StickyHeaderListener() {
            @Override
            public void headerAttached(View headerView, int adapterPosition) {
                Log.d("Listener", "Attached with position: " + adapterPosition);
            }

            @Override
            public void headerDetached(View headerView, int adapterPosition) {
                Log.d("Listener", "Detached with position: " + adapterPosition);
            }
        });

    }

    void setItems(List<Item> items) {
        if (adapterR != null) {
            adapterR.setData(items);
        }
    }


}
