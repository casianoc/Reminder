package com.example.reminder;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Intent intent;

    DBHandler dbHandler;

    Reminders remindersAdapter;

    ListView remindersListView;
    long id;

    Bundle bundle;

    ReminderListItems reminderListItemsAdapter;

    ListView itemListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialize the dbHandler
        dbHandler = new DBHandler(this, null);

        // initialize the ListView
        remindersListView = (ListView) findViewById(R.id.remindersListView);

        // initialize the shopperList cursor adapter
        remindersAdapter = new Reminders(this, dbHandler.getReminders(), 0);

        // set shopping lists cursor adapter
        remindersListView.setAdapter(remindersAdapter);

        id = bundle.getLong("_id");

        dbHandler = new DBHandler(this, null);

        String reminderName = dbHandler.getReminderListName((int)id);

        this.setTitle(reminderListName);

        itemListView = (ListView) findViewById(R.id.itemListView);

        

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void openAddReminder(View view) {
        intent = new Intent(this, AddReminder.class);
        startActivity(intent);
    }
}
