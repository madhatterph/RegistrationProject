package com.example.karen.registrationproject.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.karen.registrationproject.R;
import com.example.karen.registrationproject.api.model.AttendanceItem;
import com.example.karen.registrationproject.view.activity.DashboardActivity;
import com.example.karen.registrationproject.view.activity.ListViewScreenActivity;
import com.example.karen.registrationproject.view.adapter.AttendanceAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.value;

/**
 * Created by Karen on 10/24/2016.
 */

public class DashboardFragment extends Fragment implements View.OnClickListener{
    Button button;
    ListView listView;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "";
    List<AttendanceItem> attendanceItems;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attendanceItems = new ArrayList<>();


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


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dashboard,
                container, false);

        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        listView = (ListView) view.findViewById(R.id.dashboard_listview);
        AttendanceAdapter myAdapter=new AttendanceAdapter(getActivity(), attendanceItems);
        listView.setAdapter(myAdapter);

       // getActivity().getActionBar().setTitle("Dashboard");
        button = (Button) view.findViewById(R.id.dashboard_btn_login);
        button.setOnClickListener(this);
        // button.setOnClickListener(new View.OnClickListener() {
         //   @Override
         //   public void onClick(View v) {

        //    }
        //});
        return view;
    }


    public void onClick(View v) {
        Fragment fragment = null;
        switch(v.getId()){
            case R.id.dashboard_btn_login:
                fragment = new DashboardLoginFragment();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean("TimeIn", true);
                editor.apply();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .commit();
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        getActivity().setTitle("Dashboard");
    }
}

