// Anthony Galczak - agalczak@cnm.edu
// CIS 2237 - Program 6 - Reminders

package com.cis2237.reminders.galczakp6;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private RemindersDbAdapter dbAdapter;
    private RemindersSimpleCursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbAdapter = new RemindersDbAdapter(this);
        dbAdapter.open();

        Cursor cursor = dbAdapter.fetchAllReminders();

        if(cursor.getCount() == 0)
        {
            dbAdapter.createReminder("Breakfast", false);
            dbAdapter.createReminder("Lunch", false);
            dbAdapter.createReminder("Dinner", true);
        }

        listView = (ListView)findViewById(R.id.listView);

        String[] from = new String[]{RemindersDbAdapter.COL_CONTENT};

        int[] to = new int[]{R.id.txt_row};

        cursor = dbAdapter.fetchAllReminders();

        cursorAdapter = new RemindersSimpleCursorAdapter(MainActivity.this,
                R.layout.reminders_row, cursor, from, to, 0);

        listView.setAdapter(cursorAdapter);

        // Click listener for clicking an item in the list, should display edit/delete
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int masterListPosition,
                long id)
            {
                AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this, R.style.AppTheme);
                ListView modelListView = new ListView(getApplicationContext());
                String[] modes = new String[]{"Edit Reminder", "Delete Reminder"};
                ArrayAdapter<String> modeAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,
                        android.R.id.text1, modes);
                modelListView.setAdapter(modeAdapter);
                build.setView(modelListView);
                final Dialog dialog = build.create();
                dialog.show();


                modelListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        final int nId = getIdFromPosition(masterListPosition);

                        // Clicked Edit...
                        if (position == 0) {
                            Reminder reminder = dbAdapter.fetchReminderById(nId);

                            fireCustomDialog(reminder);
                        }
                        // Clicked delete...
                        else {
                            // http://stackoverflow.com/questions/2257963/how-to-show-a-dialog-to-confirm-that-the-user-wishes-to-exit-an-android-activity
                            new AlertDialog.Builder(MainActivity.this).setMessage("Are you sure you want to delete?").
                            setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int id){
                                    dbAdapter.deleteReminderById(nId);
                                    cursorAdapter.changeCursor(dbAdapter.fetchAllReminders());
                                }
                            }).setNegativeButton("No", null).show();

                        }

                        cursorAdapter.changeCursor(dbAdapter.fetchAllReminders());
                        dialog.dismiss();
                    }
                });

            }
        });

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Fab
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fireCustomDialog(new Reminder());
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Snackbar.make(getCurrentFocus(), "Settings selected", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    // -- Begin custom methods --
    private int getIdFromPosition(int nC)
    {
        return (int)cursorAdapter.getItemId(nC);
    }



    private void fireCustomDialog(final Reminder reminder)
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);

        final Button btnSubmit = (Button)dialog.findViewById(R.id.btnSubmit);
        final Button btnCancel = (Button)dialog.findViewById(R.id.btnCancel);
        TextView editTitle = (TextView)dialog.findViewById(R.id.editTitle);
        final EditText editAppointment = (EditText)dialog.findViewById(R.id.editAppointment);
        final CheckBox chkImportant = (CheckBox)dialog.findViewById(R.id.chkImportant);

        // Setting text for either create or edit reminder
        if(reminder.getId() == -1)
        {
            editTitle.setText("Create Reminder");
        }
        else
        {
            // Modifying the dialog based on what reminder you selected
            editAppointment.setText(reminder.getContent());
            if(reminder.getImportant() == 1)
            {
                chkImportant.setChecked(true);
            }
            else
            {
                chkImportant.setChecked(false);
            }
            editTitle.setText("Edit Reminder");
        }

        dialog.show();



        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Reminder myReminder = new Reminder(reminder.getId(), editAppointment.getText().toString(), chkImportant.isChecked() ? 1 : 0);
                // Creating a new reminder
                if(reminder.getId() == -1)
                {
                    dbAdapter.createReminder(myReminder);
                }
                // Editing a reminder
                else
                {
                    dbAdapter.updateReminder(myReminder);
                }
                cursorAdapter.changeCursor(dbAdapter.fetchAllReminders());
                dialog.dismiss();
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


    }

}
