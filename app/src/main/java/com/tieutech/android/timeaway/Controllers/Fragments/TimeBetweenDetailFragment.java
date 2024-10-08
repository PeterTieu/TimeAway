package com.tieutech.android.timeaway.Controllers.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tieutech.android.timeaway.Databases.TimeBetween.TimeBetweensManager;
import com.tieutech.android.timeaway.Models.TimeBetween;
import com.tieutech.android.timeaway.R;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


//Fragment for the DETAIL VIEW of a TimeBetween - invoked by TimeBetweenListFragment -> TimeBetweenViewPagerActivity
public class TimeBetweenDetailFragment extends Fragment{

    //==================================== INSTANCE VARIABLES ============================================================================

    private final String TAG = "TBDetailFragment"; //Tag for Logcat
    private final static String ARGUMENT_TIME_BETWEEN_ID = "ARGUMENT_TIME_BETWEEN_ID"; //Argument for retrieving the ID of the TimeBetween

    private DateFormat mDateFormat;
    private Date mTime;


    private TimeBetween mTimeBetween;               //TimeBetween retrieved
    private TextView mTimeBetweenIDTextView;        //TextView to display ID of the TimeBetween
    private EditText mTimeBetweenTitle;             //Title of TimeBetween
    private Button mTimeBetweenFirstDateButton;     //Button to change FIRST DATE
    private Button mTimeBetweenFirstTimeButton;     //Button to change FIRST TIME
    private Button mTimeBetweenSecondDateButton;    //Button to change SECOND DATE
    private Button mTimeBetweenSecondTimeButton;    //Button to change SECOND TIME


    private TextView mAheadOrBehindTimeTextView;
    private TextView mTimeBetweenDayDifferenceTextView;
    private TextView mTimeBetweenHourDifferenceTextView;
    private TextView mTimeBetweenMinuteDifferenceTextView;
    private TextView mTimeBetweenSecondDifferenceTextView;






    private static final String IDENTIFIER_DIALOG_FRAGMENT_FIRST_DATE = "IdentifierDialogFragmentFirstDate";
    private static final String IDENTIFIER_DIALOG_FRAGMENT_SECOND_DATE = "IdentifierDialogFragmentSecondDate";
    private static final String IDENTIFIER_DIALOG_FRAGMENT_FIRST_TIME = "IdentifierDialogFragmentFirstTime";
    private static final String IDENTIFIER_DIALOG_FRAGMENT_SECOND_TIME = "IdentifierDialogFragmentSecondTime";

    private static final int REQUEST_CODE_TIME_BETWEEN_DELETE_CONFIRMATION_DIALOG_FRAGMENT = 0;
    private static final String TAG_TIME_BETWEEN_DELETE_CONFIRMATION_DIALOG_FRAGMENT = "TiagTimeBetweenDeleteConfirmationDialogFragment";

    private static final int REQUEST_CODE_DIALOG_FRAGMENT_FIRST_DATE = 1;
    private static final int REQUEST_CODE_DIALOG_FRAGMENT_SECOND_DATE = 2;
    private static final int REQUEST_CODE_DIALOG_FRAGMENT_FIRST_TIME = 3;
    private static final int REQUEST_CODE_DIALOG_FRAGMENT_SECOND_TIME = 4;



    //'Constructor' - invoked by TimeBetweenViewPagerActivity
    public static TimeBetweenDetailFragment newInstance(UUID timeBetweenID){

        Bundle argumentBundle = new Bundle(); //Argument-bundle to store information passed from hosting activity

        argumentBundle.putSerializable(ARGUMENT_TIME_BETWEEN_ID, timeBetweenID); //Add data (TimeBetween ID) to the argument-bundle

        TimeBetweenDetailFragment timeBetweenDetailFragment = new TimeBetweenDetailFragment(); //Instantiate the TimeBetweenDetailFragment fragment

        timeBetweenDetailFragment.setArguments(argumentBundle); //Set the argument-bundle to the fragment

        return timeBetweenDetailFragment; //Return the fragment
    }



    //Override onAttach() fragment lifecycle callback method
    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        //Log in Logcat
        Log.i(TAG, "onAttach() called");
    }



    //Override onCreate(..) fragment lifecycle callback method
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Log.i(TAG, "onCreate(..) called"); //Log to Logcat

        UUID timeBetweenID = (UUID) getArguments().getSerializable(ARGUMENT_TIME_BETWEEN_ID); //Obtain TimeBetween ID from TimeBetweenViewPagerActivity

        mTimeBetween = new TimeBetween(); //Create TimeBetween object

        mTimeBetween = TimeBetweensManager.get(getActivity()).getTimeBetween(timeBetweenID); //Populate the newly created TimeBetween object with variables from the SQLiteDatabase, based on the fed TimeBetween ID


        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); //Disable the soft-keyboard from automatically showing, since that mTimeBetweenTitle is the first View that is focussed

        setHasOptionsMenu(true); //Declare that the fragment has an options menu


    }



    //Override onCreateView(..) fragment lifecycle callback method
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle){


        Log.i(TAG, "onCreateView(..) called"); //Log to Logcat

        View view = layoutInflater.inflate(R.layout.fragment_time_between_detail, viewGroup, false); //Obtain layout View of the fragment



        //Link member variables to the associated View objects
        mTimeBetweenIDTextView = (TextView) view.findViewById(R.id.time_between_detail_id);
        mTimeBetweenTitle = (EditText) view.findViewById(R.id.time_between_detail_title);
        mTimeBetweenFirstDateButton = (Button) view.findViewById(R.id.time_between_first_date_button);
        mTimeBetweenSecondDateButton = (Button) view.findViewById(R.id.time_between_second_date_button);
        mTimeBetweenFirstTimeButton = (Button) view.findViewById(R.id.time_between_first_time_button);
        mTimeBetweenSecondTimeButton = (Button) view.findViewById(R.id.time_between_second_time_button);


        mAheadOrBehindTimeTextView = (TextView) view.findViewById(R.id.time_between_ahead_or_behind_time_text);
        mTimeBetweenDayDifferenceTextView = (TextView) view.findViewById(R.id.time_between_day_difference);
        mTimeBetweenHourDifferenceTextView = (TextView) view.findViewById(R.id.time_between_hour_difference);
        mTimeBetweenMinuteDifferenceTextView = (TextView) view.findViewById(R.id.time_between_minute_difference);
        mTimeBetweenSecondDifferenceTextView = (TextView) view.findViewById(R.id.time_between_second_difference);


        mTimeBetweenIDTextView.setText(mTimeBetween.getID().toString()); //Set the TimeBetween ID to mTimeBetweenID TextView

        mTimeBetweenTitle.setText(mTimeBetween.getTitle());
        mTimeBetweenTitle.setCursorVisible(false); //Make the cursor invisible, otherwise it would show up because mTimeBetweenTitle is automatically focussed since it is the first View










        mTimeBetweenTitle.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTimeBetween.setTitle(charSequence.toString());
                updateTimeBetween();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Do nothing
            }
        });


        mTimeBetweenTitle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                mTimeBetweenTitle.setCursorVisible(true); //Show the cursor
                return false; //Don't hide the soft-keyboard
            }
        });




        if (mTimeBetween.getDateFirst() != null){
            mTimeBetweenFirstDateButton.setText(mDateFormat.format("EEE d MMMM yyyy", mTimeBetween.getDateFirst()));
            mTimeBetweenFirstTimeButton.setText(mDateFormat.format("h:mm a", mTimeBetween.getDateFirst()));
        }


        mTimeBetweenFirstDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager(); //Create FragmentManager

                DateDialogFragment dateDialogFragment = DateDialogFragment.newInstance(mTimeBetween.getDateFirst());


                dateDialogFragment.setTargetFragment(TimeBetweenDetailFragment.this, REQUEST_CODE_DIALOG_FRAGMENT_FIRST_DATE); //Start the dialog fragment


                dateDialogFragment.show(fragmentManager, IDENTIFIER_DIALOG_FRAGMENT_FIRST_DATE); //Show dialog
            }
        });



        if (mTimeBetween.getDateSecond() != null){
            mTimeBetweenSecondDateButton.setText(mDateFormat.format("EEE d MMMM yyyy", mTimeBetween.getDateSecond()));
            mTimeBetweenSecondTimeButton.setText(mDateFormat.format("h:mm a", mTimeBetween.getDateSecond()));
        }


        mTimeBetweenSecondDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager(); //Create FragmentManager

                DateDialogFragment dateDialogFragment = DateDialogFragment.newInstance(mTimeBetween.getDateSecond());


                dateDialogFragment.setTargetFragment(TimeBetweenDetailFragment.this, REQUEST_CODE_DIALOG_FRAGMENT_SECOND_DATE); //Start the dialog fragment


                dateDialogFragment.show(fragmentManager, IDENTIFIER_DIALOG_FRAGMENT_SECOND_DATE); //Show dialog
            }
        });








        mTimeBetweenFirstTimeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FragmentManager fragmentManager = getFragmentManager();
                TimePickerDialogFragment timePickerDialogFragment = TimePickerDialogFragment.newInstance(mTimeBetween.getDateFirst());
                timePickerDialogFragment.setTargetFragment(TimeBetweenDetailFragment.this, REQUEST_CODE_DIALOG_FRAGMENT_FIRST_TIME);
                timePickerDialogFragment.show(fragmentManager, IDENTIFIER_DIALOG_FRAGMENT_FIRST_TIME);
            }
        });



        mTimeBetweenSecondTimeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FragmentManager fragmentManager = getFragmentManager();
                TimePickerDialogFragment timePickerDialogFragment = TimePickerDialogFragment.newInstance(mTimeBetween.getDateSecond());
                timePickerDialogFragment.setTargetFragment(TimeBetweenDetailFragment.this, REQUEST_CODE_DIALOG_FRAGMENT_SECOND_TIME);
                timePickerDialogFragment.show(fragmentManager, IDENTIFIER_DIALOG_FRAGMENT_SECOND_TIME);
            }
        });







        updateAheadOrBehindTimeTextView();


        displayTimeDifference(mTimeBetween.getDateFirst(), mTimeBetween.getDateSecond());


        return view; //Return the View

    }




    //Calculate and display the time difference between the FIRST Time and SECOND Time
    public void displayTimeDifference(Date startDate, Date endDate) {

        long timeDifferenceInMilli = endDate.getTime() - startDate.getTime(); //Difference in milliseconds

        //Define constant variables
        final long secondsInMilli = 1000;                 //How many milliseconds in a SECOND
        final long minutesInMilli = secondsInMilli * 60;  //How many milliseconds in a MINUTE
        final long hoursInMilli = minutesInMilli * 60;    //How many milliseconds in an HOUR
        final long daysInMilli = hoursInMilli * 24;       //How many milliseconds in a DAY


        //Work out the time elapsed - in DAYS, HOURS, MINUTES, SECONDS
            //DAYS
        long elapsedDays = timeDifferenceInMilli / daysInMilli;         //Elapsed DAYS
        timeDifferenceInMilli = timeDifferenceInMilli % daysInMilli;    //Get remainder time difference in DAYS - used for working out HOURS difference (next calculation)
            //HOURS
        long elapsedHours = timeDifferenceInMilli / hoursInMilli;       //Elapsed HOURS
        timeDifferenceInMilli = timeDifferenceInMilli % hoursInMilli;   //Get remainder time difference in HOURS - used for working  out MINUTES difference (next calculation)
            //MINUTES
        long elapsedMinutes = timeDifferenceInMilli / minutesInMilli;   //Elapsed MINUTES
        timeDifferenceInMilli = timeDifferenceInMilli % minutesInMilli; //Get remainder time difference in MINUTES - used for working  out SECONDS difference (next calculation)
            //SECONDS
        long elapsedSeconds = timeDifferenceInMilli / secondsInMilli;   //Elapsed SECONDS


        String difference = elapsedDays + "days, " + elapsedHours + "hours, " + elapsedMinutes + "minutes, " + elapsedSeconds + "seconds";

        mTimeBetweenDayDifferenceTextView.setText(Long.toString(elapsedDays));
        mTimeBetweenHourDifferenceTextView.setText(Long.toString(elapsedHours));
        mTimeBetweenMinuteDifferenceTextView.setText(Long.toString(elapsedMinutes));
        mTimeBetweenSecondDifferenceTextView.setText(Long.toString(elapsedSeconds));

    }













    //Update the TimeBetween in the TimeBetweens SQLite Database
    private void updateTimeBetween(){

        TimeBetweensManager.get(getActivity()).updateTimeBetweensDatabase(mTimeBetween);
        displayTimeDifference(mTimeBetween.getDateFirst(), mTimeBetween.getDateSecond());

        updateAheadOrBehindTimeTextView();
    }





    private void updateAheadOrBehindTimeTextView(){
        long timeDifference = mTimeBetween.getDateSecond().getTime() - mTimeBetween.getDateFirst().getTime();


        if (timeDifference == 0){
            mAheadOrBehindTimeTextView.setText(R.string.there_is_no_time_difference);
        }
        else if (timeDifference > 0){
            mAheadOrBehindTimeTextView.setText(R.string.ahead_of_time_by);
        }
        else{
            mAheadOrBehindTimeTextView.setText(R.string.behind_time_by);
        }
    }





    //Override onCreateOptionsMenu(..) fragment lifecycle callback method
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
        super.onCreateOptionsMenu(menu, menuInflater);

        Log.i(TAG, "onCreateOptionsMenu(..) called");

        menuInflater.inflate(R.menu.fragment_time_between_detail, menu);

    }


    //Override onOptionsItemSelected(..) fragment lifecycle callback method
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){

        Log.i(TAG, "onOptionsItemSelected(..) called");

        switch(menuItem.getItemId()){

            //
            case (R.id.delete_time_between):

                deleteTimeBetweenConfirmationDialog();
                TimeBetweensManager.get(getActivity()).deleteTimeBetween(mTimeBetween);

                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }

    }




    private void deleteTimeBetweenConfirmationDialog(){

        FragmentManager fragmentManager = getFragmentManager();

        TimeBetweenDeleteConfirmationDialogFragment timeBetweenDeleteConfirmationDialogFragment = TimeBetweenDeleteConfirmationDialogFragment.newInstance(mTimeBetween.getID().toString());

        timeBetweenDeleteConfirmationDialogFragment.setTargetFragment(TimeBetweenDetailFragment.this, REQUEST_CODE_TIME_BETWEEN_DELETE_CONFIRMATION_DIALOG_FRAGMENT);

        timeBetweenDeleteConfirmationDialogFragment.show(fragmentManager, TAG_TIME_BETWEEN_DELETE_CONFIRMATION_DIALOG_FRAGMENT);
    }



    //Override onActivityResult(..) callback method
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){

        Log.i(TAG, "onActivityResult(..) called");

        //If the resultCode DOES NOT equal Activity.RESULT_OK
        if (resultCode != Activity.RESULT_OK){
            return;
        }

        //
        if (requestCode == REQUEST_CODE_TIME_BETWEEN_DELETE_CONFIRMATION_DIALOG_FRAGMENT){

            boolean confirmDelete = intent.getBooleanExtra(TimeBetweenDeleteConfirmationDialogFragment.EXTRA_TIME_BETWEEN_DELETE_CONFIRMATION, false);

            if (confirmDelete == true){
                TimeBetweensManager.get(getActivity()).deleteTimeBetween(mTimeBetween);
            }


            getActivity().finish();


            updateTimeBetween();

            //======= Hide soft keyboard ========
            //Get InputMethodManager object
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

            //Request to hide soft keyboard. Argument 1 (IBinder): Any view visible on screen (e.g. mTitle)
            inputMethodManager.hideSoftInputFromWindow(mTimeBetweenIDTextView.getWindowToken(), 0);


            //======= Display Toast on Pix delete ========
            //If no Pix title exists or is empty
            if (mTimeBetween.getTitle() == null || mTimeBetween.getTitle().isEmpty()) {
                Toast.makeText(getContext(), Html.fromHtml("<i>" + "*Untitled*" + "</i>" + " Pix Deleted"), Toast.LENGTH_LONG).show();
            }
            //If Pix title exists or is not empty
            else {
                Toast.makeText(getContext(), Html.fromHtml("Pix Deleted: " + "<b>" + mTimeBetween.getTitle() + "</b>"), Toast.LENGTH_LONG).show();
            }

        }





        if (requestCode == REQUEST_CODE_DIALOG_FRAGMENT_FIRST_DATE){
            Date dateSet = (Date) intent.getSerializableExtra(DateDialogFragment.EXTRA_DATE);

            mTimeBetween.setDateFirst(dateSet);



            //Obtain Year, Month and Day Of Month
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(mTimeBetween.getDateFirst());

            int firstYear = calendar.get(Calendar.YEAR);
            int firstMonth = calendar.get(Calendar.MONTH);
            int firstDayOfMonth = calendar.get(Calendar.DATE);

            Log.i(TAG, "First YEAR: " + firstYear);
            Log.i(TAG, "First MONTH: " + firstMonth);
            Log.i(TAG, "First DAY OF MONTH: " + firstDayOfMonth);




            mTimeBetweenFirstDateButton.setText(mDateFormat.format("EEE d MMMM yyyy", mTimeBetween.getDateFirst()));
            mTimeBetweenFirstTimeButton.setText(mDateFormat.format("h:mm a", mTimeBetween.getDateFirst()));

            updateTimeBetween();
        }



        if (requestCode == REQUEST_CODE_DIALOG_FRAGMENT_SECOND_DATE){

            Date dateSet = (Date) intent.getSerializableExtra(DateDialogFragment.EXTRA_DATE);

            mTimeBetween.setDateSecond(dateSet);



            //Obtain Year, Month and Day Of Month
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(mTimeBetween.getDateFirst());

            int secondYear = calendar.get(Calendar.YEAR);
            int secondMonth = calendar.get(Calendar.MONTH);
            int secondDayOfMonth = calendar.get(Calendar.DATE);


            Log.i(TAG, "Second YEAR: " + secondYear);
            Log.i(TAG, "Second MONTH: " + secondMonth);
            Log.i(TAG, "Second DAY OF MONTH: " + secondDayOfMonth);






            mTimeBetweenSecondDateButton.setText(mDateFormat.format("EEE d MMMM yyyy", mTimeBetween.getDateSecond()));
            mTimeBetweenSecondTimeButton.setText(mDateFormat.format("h:mm a", mTimeBetween.getDateSecond()));

            updateTimeBetween();
        }




        if (requestCode == REQUEST_CODE_DIALOG_FRAGMENT_FIRST_TIME){

            Date timeSet = (Date) intent.getSerializableExtra(TimePickerDialogFragment.EXTRA_TIME);


            mTimeBetween.setDateFirst(timeSet);

            mTimeBetweenFirstTimeButton.setText(mDateFormat.format("h:mm a", mTimeBetween.getDateFirst()));

            updateTimeBetween();
        }



        if (requestCode == REQUEST_CODE_DIALOG_FRAGMENT_SECOND_TIME){

            Date timeSet = (Date) intent.getSerializableExtra(TimePickerDialogFragment.EXTRA_TIME);

            mTimeBetween.setDateSecond(timeSet);

            mTimeBetweenSecondTimeButton.setText(mDateFormat.format("h:mm a", mTimeBetween.getDateSecond()));

            updateTimeBetween();
        }












    }





}
