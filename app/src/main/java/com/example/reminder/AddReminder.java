package com.example.reminder;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddReminder extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Intent intent;

    EditText nameEditText;
    EditText dateEditText;
    Spinner typeSpinner;

    Calendar calendar;

    DBHandler dbHandler;

    String type;
    Date dateCompare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialize EditTexts
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        dateEditText = (EditText) findViewById(R.id.dateEditText);

        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.quantities, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        typeSpinner.setAdapter(adapter);

        typeSpinner.setOnItemSelectedListener(this);

        dbHandler = new DBHandler(this, null);

        //initialize calendar
        calendar = Calendar.getInstance();

        // initialize a DatePickerDialog and register and OnDateListener to it
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            // this method gets called when a date is set in the DatePickerDialog
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                // set the Calendar year, month, and day to year, month and day
                // selected in DatePickerDialog
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                // call method that updates date EditText with date set in DatePickerDialog
                updateDueDate();
            }
        };


        // registered an OnClickListener on the dateEditText
        dateEditText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // display DatePickerDialog with current date selected
                new DatePickerDialog(AddReminder.this,
                        date,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        dbHandler = new DBHandler(this, null);

    }

    public void updateDueDate(){

        // create a SimpleDateFormat
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        // apply SimpleDateFormat to date in calendar and set it in the date EditText
        dateEditText.setText(simpleDateFormat.format(calendar.getTime()));

        calendar.setTime(dateCompare);
        simpleDateFormat.format(dateCompare);

        if(dateCompare.compareTo(){

        }


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_reminder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // get the id of the item that was selected
        switch (item.getItemId()){
            case R.id.action_home :
                intent = new Intent(this, MainActivity.class);
                // initializing an intent for the main activity, starting it
                // and returning true
                startActivity(intent);
                return true;
            case R.id.action_add_reminder :
                // initializing an intent for the create list activity, starting it
                // and returning true
                intent = new Intent(this, AddReminder.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void createReminder(MenuItem menuItem){
        // get data input to EditTexts and store it in Strings
        String title = nameEditText.getText().toString();
        String date = dateEditText.getText().toString();

        if (title.trim().equals("") || type.equals("") || date.trim().equals("")){
            Toast.makeText(this, "Please enter a title, date and type!", Toast.LENGTH_LONG).show();
        } else {
            dbHandler.addShoppingList(title, date, type);
            // if none of the strings are empty, display reminder list Added Toast
            Toast.makeText(this, "Reminder Added!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        type = adapterView.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
