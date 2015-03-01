package com.example.deekshasharma.pennyapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.deekshasharma.pennyapp.model.Categories;
import com.example.deekshasharma.pennyapp.model.CategoryItem;

import java.util.Calendar;


public class AddActivity extends ActionBarActivity {


    private TextView date;
    private Calendar myCalender;
    private DatePickerDialog datePickerDialog;
    private String[] allMonths = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
    private TextView categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        onDateClickListener();
        setDate();

        Intent receiveFromCategoryIntent = getIntent();
        Bundle extras = receiveFromCategoryIntent.getExtras();
        if(extras != null)
        {
            int position = receiveFromCategoryIntent.getIntExtra("position", 0);
            CategoryItem item = Categories.allCategories.get(position);
            categoryName = (TextView) findViewById(R.id.category);
            categoryName.setText(item.getName());
        }
        onCategoryClickListener();
    }


    /*
    Allows user to select the date in a date picker and set the value in date field.
     */
    private void setDate() {
        myCalender = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, monthOfYear, dayOfMonth);
                        String month = allMonths[selectedDate.get(Calendar.MONTH)];
                        date.setText(new StringBuilder()
                                .append(selectedDate.get(Calendar.DAY_OF_MONTH)).append("-")
                                .append(month).append("-")
                                .append(selectedDate.get(Calendar.YEAR)));
                    }
                }
                , myCalender.get(Calendar.YEAR), myCalender.get(Calendar.MONTH), myCalender.get(Calendar.DAY_OF_MONTH));
    }

    /*
    Listens to the date EditText
     */
    public void onDateClickListener() {
        date = (TextView) findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
    }



    /*
    Listens to the categoryName TextView
    */
    private void onCategoryClickListener()
    {
        categoryName = (TextView) findViewById(R.id.category);
        categoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), GroupActivity.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
