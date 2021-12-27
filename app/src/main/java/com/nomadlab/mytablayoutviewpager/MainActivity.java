package com.nomadlab.mytablayoutviewpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

/*
*   pager
*   - 화면 (종이 넘기듯이 화면을 넘겨 주는 역할)
*   - Adapter 가 필요하다.
*   TabLayout
*   - tab 을 담당하는 역할
*
*   = 보통 같이 작성하지만 따로 따로 만들어도 작동한다.
*
*   리스너
*  - pager 와 TabLayout 을 연결해 주기 위해 필요하다.
* */
public class MainActivity extends AppCompatActivity {

    static final int TAB_COUNT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("One"));
        tabLayout.addTab(tabLayout.newTab().setText("Two"));
        tabLayout.addTab(tabLayout.newTab().setText("Three"));



        ViewPager viewPager = findViewById(R.id.viewPager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), TAB_COUNT);
        viewPager.setAdapter(pagerAdapter);

        // tabLayout 이벤트시 viewPager 연동
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("TAG", "position : " + position);
                viewPager.setCurrentItem(position);
                // switch(position) {
                //  fragment = new FragmentOne()
                // }
                // getSupportFragmentManager().beginTransaction().replace(fragment).commit();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        // viewPager 이벤트시 TabLayout 연동
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}

class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    /* 리턴 타입이 프래그 먼트 */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0 :
                Log.d("TAG", "fragment 0");
                fragment = new FragmentOne();
                break;
            case 1 :
                Log.d("TAG", "fragment 1");
                fragment = new FragmentTwo();
                break;
            case 2 :
                Log.d("TAG", "fragment 2");
                fragment = new FragmentThree();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}