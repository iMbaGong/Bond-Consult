package com.example.bondconsult;



import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

public class mFragmentAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"债券", "论坛", "资讯"};
    private SparseArray<Fragment> fragments;

    public mFragmentAdapter(FragmentManager fm) {
        super(fm);
        fragments = new SparseArray<>(getCount());
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new BondFragment();
        } else if (position == 1) {
            return new PostFragment();
        } else if (position == 2) {
            return new NewsListFragment();
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

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
        fragments.remove(position);
    }

    public Fragment getFragment(int position) {
        return fragments.get(position);
    }
}
