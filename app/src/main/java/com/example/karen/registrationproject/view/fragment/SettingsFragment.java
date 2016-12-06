package com.example.karen.registrationproject.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.karen.registrationproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        //getActivity().getActionBar().setTitle("Settings");


        return view;
    }
    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        getActivity().setTitle("Settings");
    }

}
