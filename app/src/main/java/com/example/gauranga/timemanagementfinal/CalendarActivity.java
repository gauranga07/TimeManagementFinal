package com.example.gauranga.timemanagementfinal;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity{
    Button add;
    String s = "";
    Intent i;
    DatabaseHelper myDb;
    CalendarView calendar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        add = (Button)findViewById(R.id.add_task_button);
        myDb = new DatabaseHelper(getApplicationContext());
        myDb.getAllData(); myDb = new DatabaseHelper(getApplicationContext());
        myDb.getAllData();
        initializeCalendar();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == add) {
                    i = new Intent(getApplicationContext(), AddTask.class);
                    startActivity(i);

                } }});
    }
    public void initializeCalendar() {
        calendar = (CalendarView) findViewById(R.id.calendarView);

        // sets whether to show the week number.
        calendar.setShowWeekNumber(false);

        // sets the first day of week according to Calendar.
        // here we set Monday as the first day of the Calendar
        calendar.setFirstDayOfWeek(2);

        //The background color for the selected week.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            calendar.setSelectedWeekBackgroundColor(getResources().getColor(R.color.green));
        }

        //sets the color for the dates of an unfocused month.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            calendar.setUnfocusedMonthDateColor(getResources().getColor(R.color.transparent));
        }

        //sets the color for the separator line between weeks.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            calendar.setWeekSeparatorLineColor(getResources().getColor(R.color.transparent));
        }

        //sets the color for the vertical bar shown at the beginning and at the end of the selected date.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            calendar.setSelectedDateVerticalBar(R.color.darkgreen);
        }

        //sets the listener to be notified upon selected date change.
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            //show the selected date as a toast
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                Toast.makeText(getApplicationContext(), day + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
            }
        });
    }



}