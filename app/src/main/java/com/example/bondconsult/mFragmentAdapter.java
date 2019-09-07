package com.example.bondconsult;



import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class mFragmentAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"债券", "论坛", "资讯"};

    public mFragmentAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new BondFragment();
        } else if (position == 1) {
            return new PostFragment();
        }else if (position==2){
            return new NewsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
