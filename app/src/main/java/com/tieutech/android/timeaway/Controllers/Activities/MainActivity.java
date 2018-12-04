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

        setContentView(R.layout.activity_main); //Set layout for the MainActivity
        FragmentManager fragmentManager = getSupportFragmentManager(); //Create the FragmentManager for interacting with fragments associated with MainActivity
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container); //Instantiate Fragment and assign it to associated resource ID

        //If the Fragment object DOES NOT exist
        if (fragment == null){
            fragment = new TimeFragment(); //Create a TimeFragment object
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit(); //Begin the hosting of the TimeFragment fragment by the MainActivity activity (via the FragmentManager)
        }

    }

}
