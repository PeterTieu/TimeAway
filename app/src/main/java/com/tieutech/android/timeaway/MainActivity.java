package com.tieutech.android.timeaway;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//Main Actity - contains the fragments of the app
public class MainActivity extends AppCompatActivity {


    //Override onCreate(..) fragment lifecycle callback method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentById();
    }


}
