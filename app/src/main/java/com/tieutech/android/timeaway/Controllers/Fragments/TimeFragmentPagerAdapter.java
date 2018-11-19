package com.tieutech.android.timeaway.Controllers.Fragments;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TimeFragmentPagerAdapter extends FragmentPagerAdapter{

    private static final int FRAGMENT_COUNT = 2;

    public TimeFragmentPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }


    @Override
    public Fragment getItem(int position){

        switch (position){
            case 0:
                return new TimeSinceRemainingFragment();

            case 1:
                return new TimeBetweenFragment();
        }

        return null;
    }



    @Override
    public int getCount(){
        return FRAGMENT_COUNT;
    }


    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Time Since/Remaining";
            case 1:
                return "Time Between";
        }

        return null;
    }



}
