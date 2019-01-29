package com.tieutech.android.timeaway.Controllers.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tieutech.android.timeaway.R;

public class TimeFragment extends Fragment{

    private final String TAG = "TimeFragment"; //Tag for Logcat

    //==== SWIPE TABS VARIABLES ====
    private TabLayout mTabLayout; //TabLayout view - component of the Swipe Tabs layout
    private ViewPager mViewPager; //ViewPager - Allows swiping between multiple TabLayouts, making them into Swipe Tabs


    //Constructor
    public TimeFragment (){
    }


    //Override onCreateView(..) fragment lifecycle callback method
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState){
        super.onCreateView(layoutInflater, viewGroup, savedInstanceState);

        Log.i(TAG, "onCreateView(..) called"); //Log to Logcat

        View view = layoutInflater.inflate(R.layout.fragment_time, viewGroup, false); //Instantiate View of the fragment

        mTabLayout = (TabLayout) view.findViewById(R.id.search_tabs); //Create TabLayout
        mViewPager = (ViewPager) view.findViewById(R.id.search_view_pager); //Create ViewPager

        mViewPager.setAdapter(new TimeFragmentPagerAdapter(getChildFragmentManager())); //Link ViewPager to TimeFragmentPagerAdapter
        mTabLayout.setupWithViewPager(mViewPager); //Link TabLayotu to ViewPager

        getActivity().setTitle("Time Tabs"); //Set title for Fragment

        return view; //Return View
    }

}
