package com.example.karen.registrationproject.view.fragment;



import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.karen.registrationproject.Helper;
import com.example.karen.registrationproject.R;
import com.example.karen.registrationproject.api.model.AttendanceItem;
import com.example.karen.registrationproject.view.adapter.AttendanceAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardLoginFragment extends Fragment implements View.OnClickListener, View.OnFocusChangeListener{
    Button btn_logout, save;
    EditText multilineET;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "";
    Button db_btn_logout;
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

        attendanceItem = new AttendanceItem();
        attendanceItem.date = "Oct. 23, 2016";
        attendanceItem.timeIn = "10:00 AM";
        attendanceItem.timeOut = "7:10 PM";
        attendanceItem.totalHours = "8 hrs";
        attendanceItem.task = "Database";

        attendanceItems.add(attendanceItem);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_listviewscreen,
                container, false);

        multilineET = (EditText) view.findViewById(R.id.listviewscreen_multiline_text);
        multilineET.setOnFocusChangeListener(this);

        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        AttendanceAdapter myAdapter = new AttendanceAdapter(getActivity(), attendanceItems);
        ListView listView = (ListView) view.findViewById(R.id.listviewscreen_listview);
        listView.setAdapter(myAdapter);

        db_btn_logout = (Button) view.findViewById(R.id.listviewscreen_btn_logout);
        db_btn_logout.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.listviewscreen_btn_logout:
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean("TimeIn", false);
                editor.apply();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new DashboardFragment())
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

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.listviewscreen_multiline_text:
                if (!hasFocus) {
                    Helper.hideKeyboardFrom(getActivity(),v);
                }
        }

    }
}
