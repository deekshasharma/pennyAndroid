package com.example.deekshasharma.pennyapp;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class AddTransactionFragment extends Fragment {

    private TextView date;
    private Calendar myCalender ;
    private DatePickerDialog datePickerDialog;
    private String[] allMonths = {
            "JAN","FEB", "MAR","APR", "MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"
    };
    private TextView category;
    private EditText transactionName;


    public AddTransactionFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_add_transaction,container,false);
        onDateClickListener(rootView);
        setDate();
        onCategoryClickListener(rootView);
        return rootView;
    }

    /*
    Listens to the date EditText
     */
    public void onDateClickListener(View view)
    {
        date = (TextView) view.findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
    }


    /*
    Allows user to select the date in a date picker and set the value in date field.
     */
    private void setDate()
    {
        myCalender = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                    {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year,monthOfYear,dayOfMonth);
                        String month = allMonths[selectedDate.get(Calendar.MONTH)];
                        date.setText(new StringBuilder()
                                .append(selectedDate.get(Calendar.DAY_OF_MONTH)).append("-")
                                .append(month).append("-")
                                .append(selectedDate.get(Calendar.YEAR)));
                    }
                }
                ,myCalender.get(Calendar.YEAR),myCalender.get(Calendar.MONTH),myCalender.get(Calendar.DAY_OF_MONTH));
    }


    /*
    Listens to the category TextView
     */
    private void onCategoryClickListener(View view)
    {
        category = (TextView) view.findViewById(R.id.category);
        category.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment groupFragment = new GroupFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container,groupFragment).commit();
                setTitle("Choose Group");
//                setTitle(R.string.group_fragment_title);
            }
        });
    }

    public void setTitle(CharSequence title) {
        android.support.v7.app.ActionBar actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle(title);
    }

}
