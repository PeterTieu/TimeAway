package com.tieutech.android.timeaway.Controllers.Activities;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tieutech.android.timeaway.Controllers.Fragments.TimeFragment;
import com.tieutech.android.timeaway.R;

//Main Actity - contains the fragments of the app
public class MainActivity extends AppCompatActivity {


    //Override onCreate(..) fragment lifecycle callback method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (fragment == null){
            fragment = new TimeFragment();

            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }

    }


}
