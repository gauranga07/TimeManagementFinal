package com.example.gauranga.timemanagementfinal;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddTaskDetails extends AppCompatActivity implements View.OnClickListener{
    DatabaseHelper myDb;
    EditText title,startDate,endDate,startTime,endTime,note;
    Button btnAddData,btnviewAll,btnDelete,btnviewUpdate;
    Button start_T,start_D,end_T,end_D;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private long sDate,sTime,eDate,eTime;
    String s = "";
    String n = "";
    private static int titleCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_details);

        title = (EditText)findViewById(R.id.task_title);
        startDate = (EditText)findViewById(R.id.task_startDate);
        endDate = (EditText)findViewById(R.id.task_endDate);
        startTime = (EditText)findViewById(R.id.task_startTime);
        endTime = (EditText)findViewById(R.id.task_endTime);
        start_T= findViewById(R.id.Tstart);
        start_D= findViewById(R.id.Dstart);
        end_T= findViewById(R.id.Tend);
        end_D= findViewById(R.id.Dend);
        note = findViewById(R.id.task_note);
        btnAddData = (Button)findViewById(R.id.task_save);
//        btnviewAll = (Button)findViewById(R.id.button_viewAll);
//        btnviewUpdate= (Button)findViewById(R.id.button_update);
//        btnDelete= (Button)findViewById(R.id.button_delete);

//        viewAll();
//        UpdateData();
//        DeleteData();

        start_T.setOnClickListener(this);
        start_D.setOnClickListener(this);
        end_T.setOnClickListener(this);
        end_D.setOnClickListener(this);

        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDb = new DatabaseHelper(getApplicationContext());
                        s = title.getText().toString();
                        if(s.equals(null)){
                            s="Title "+Integer.toString(titleCount++);
                        }
                        if(note.getText().toString().equals(null)){
                            n = "No notes.";
                        }
                        boolean isInserted = myDb.insertData(s, sDate, eDate, sTime, eTime,n);

                        if(isInserted == true)
                            Toast.makeText(getApplicationContext(),"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(),"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
//

    }

    @Override
    public void onClick(View v) {

        if (v == start_D) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            startDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            s = Integer.toString(dayOfMonth)+"-"+Integer.toString(monthOfYear + 1)+"-"+Integer.toString(year);
                            SimpleDateFormat formatter = new SimpleDateFormat(s);
                            Date date = null;
                            try {
                                date = (Date)formatter.parse(s);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            sDate = date.getTime();

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == end_D) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            endDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            s = Integer.toString(dayOfMonth)+"-"+Integer.toString(monthOfYear + 1)+"-"+Integer.toString(year);
                            SimpleDateFormat formatter = new SimpleDateFormat(s);
                            Date date = null;
                            try {
                                date = (Date)formatter.parse(s);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            eDate = date.getTime();

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == start_T ) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            Time t = Time.valueOf(Integer.toString(hourOfDay)+":"+Integer.toString(minute)+":00");
                            sTime = t.getTime();
                            startTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == end_T ) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            Time t = Time.valueOf(Integer.toString(hourOfDay)+":"+Integer.toString(minute)+":00");
                            eTime = t.getTime();
                            endTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

//    public void DeleteData() {
//        btnDelete.setOnClickListener(
}

//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
//                        if(deletedRows > 0)
//                            Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
//                        else
//                            Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
//                    }
//                }
//        );
//    }
//    public void UpdateData() {
//        btnviewUpdate.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        boolean isUpdate = myDb.updateData(editTextId.getText().toString(),
//                                editName.getText().toString(),
//                                editSurname.getText().toString(),editMarks.getText().toString());
//                        if(isUpdate == true)
//                            Toast.makeText(MainActivity.this,"Data Update",Toast.LENGTH_LONG).show();
//                        else
//                            Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
//                    }
//                }
//        );
//    }
//
//    public void viewAll() {
//        btnviewAll.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Cursor res = myDb.getAllData();
//                        if(res.getCount() == 0) {
//                            // show message
//                            showMessage("Error","Nothing found");
//                            return;
//                        }
//
//                        StringBuffer buffer = new StringBuffer();
//                        while (res.moveToNext()) {
//                            buffer.append("Id :"+ res.getString(0)+"\n");
//                            buffer.append("Name :"+ res.getString(1)+"\n");
//                            buffer.append("Surname :"+ res.getString(2)+"\n");
//                            buffer.append("Marks :"+ res.getString(3)+"\n\n");
//                        }
//
//                        // Show all data
//                        showMessage("Data",buffer.toString());
//                    }
//                }
//        );
//    }
//
//    public void showMessage(String title,String Message){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(true);
//        builder.setTitle(title);
//        builder.setMessage(Message);
//        builder.show();
//    }
//
//
//}