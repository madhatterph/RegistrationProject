package com.example.karen.registrationproject.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.karen.registrationproject.R;
import com.example.karen.registrationproject.api.model.AttendanceItem;
import com.example.karen.registrationproject.view.adapter.AttendanceAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.value;

public class ListViewScreenActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_logout, save;
    EditText multilineET;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "";
    Button db_btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviewscreen);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        Intent intent = getIntent();
        String value = intent.getStringExtra("login");

        db_btn_logout = (Button) findViewById(R.id.listviewscreen_btn_logout);
        db_btn_logout.setOnClickListener(this);

        List<AttendanceItem> attendanceItems = new ArrayList<>();

        AttendanceItem attendanceItem = new AttendanceItem();
        attendanceItem.date = "Oct. 20, 2016";
        attendanceItem.timeIn = "10:00 AM";
        attendanceItem.timeOut = "7:10 PM";
        attendanceItem.totalHours = "8 hrs";
        attendanceItem.task = "Database";

        attendanceItems.add(attendanceItem);

        attendanceItem = new AttendanceItem();
        attendanceItem.date = "Oct. 21, 2016";
        attendanceItem.timeIn = "11:15 AM";
        attendanceItem.timeOut = "8:00 PM";

        attendanceItems.add(attendanceItem);

        attendanceItem = new AttendanceItem();
        attendanceItem.date = "Oct. 22, 2016";
        attendanceItem.timeIn = "07:15 AM";

        attendanceItems.add(attendanceItem);

        attendanceItem = new AttendanceItem();
        attendanceItem.date = "Oct. 23, 2016";
        attendanceItem.timeIn = "10:00 AM";
        attendanceItem.timeOut = "7:10 PM";
        attendanceItem.totalHours = "8 hrs";
        attendanceItem.task = "Database";

        attendanceItems.add(attendanceItem);

        AttendanceAdapter myAdapter = new AttendanceAdapter(this, attendanceItems);
        ListView listView = (ListView) findViewById(R.id.listviewscreen_listview);
        listView.setAdapter(myAdapter);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.listviewscreen_btn_logout:
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean("TimeIn", false);
                editor.apply();

                Intent intent = new Intent(this, DashboardActivity.class);
                intent.putExtra("login", value); //Optional parameters
                ListViewScreenActivity.this.startActivity(intent);
                break;
        }    }
}
