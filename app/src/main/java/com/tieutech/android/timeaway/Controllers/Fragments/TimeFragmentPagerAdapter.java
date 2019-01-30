package com.tieutech.android.timeaway.Controllers.Fragments;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


//Adapter called by TimeFragment - to create the swipe tabs
    //Holds the following fragments:
        //1: TimeSinceRemainingListFragment
        //2: TimeBetweenListFragment
public class TimeFragmentPagerAdapter extends FragmentPagerAdapter{

    private static final int FRAGMENT_COUNT = 2; //How many framgents/swipe tabs to display


    //Constructor - called by TimeFragment
    public TimeFragmentPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }



    //Override getItem(..) method from FragmentPagerAdapter
    @Override
    public Fragment getItem(int position){

        switch (position){
            case 0:
                return new TimeSinceRemainingListFragment(); //FIRST tab - TimeSinceRemainingListFragment

            case 1:
                return new TimeBetweenListFragment(); //SECOND tab - TimeBetweenListFragment
        }

        return null;
    }



    //Overrride getCount() from FragmentPagerAdapter
    @Override
    public int getCount(){
        return FRAGMENT_COUNT;
    }



    //Override getPageTitle(..) from FragmentPagerAdapter
    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Time Since/Remaining"; //Title of FIRST tab
            case 1:
                return "Time Between"; //Title of SECOND tab
        }

        return null;
    }

}
