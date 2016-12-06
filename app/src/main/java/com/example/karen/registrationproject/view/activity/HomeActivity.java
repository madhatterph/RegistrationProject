package com.example.karen.registrationproject.view.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.karen.registrationproject.Helper;
import com.example.karen.registrationproject.R;
import com.example.karen.registrationproject.view.fragment.DashboardFragment;
import com.example.karen.registrationproject.view.fragment.DashboardLoginFragment;
import com.example.karen.registrationproject.view.fragment.SettingsFragment;

import static android.R.attr.value;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String MyPREFERENCES = "";
    private int i = 0;
    private static final String TAG = "MyActivity";
    String title = "";
    EditText multitext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean("TimeIn", false)){
            switchFragment(new DashboardLoginFragment());
        }else{
            switchFragment(new DashboardFragment());
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            int backStackCount = getSupportFragmentManager().getBackStackEntryCount();
            if ( backStackCount <= 1 ) {
                i++;
                if (i == 1) {
                    Toast.makeText(HomeActivity.this, "Press back once more to exit.",
                            Toast.LENGTH_SHORT).show();
                } else if(i>1) {
                    finish();
                }
            } else {
                super.onBackPressed();
            }
        }

    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Helper.hideKeyboard(this);
        int id = item.getItemId();
        if (id == R.id.nav_Dashboard) {
            SharedPreferences sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            if(sharedPreferences.getBoolean("TimeIn", false)){
                switchFragment(new DashboardLoginFragment());

            }else{
                switchFragment(new DashboardFragment());
            }
        }else if (id == R.id.nav_Settings) {
            switchFragment(new SettingsFragment());
        } else if (id == R.id.nav_Logout) {
            new AlertDialog.Builder(HomeActivity.this)
                    .setTitle("Logout Confirmation")
                    .setMessage("Are you sure you want to logout?")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            intent.putExtra("login", value); //Optional parameters
                            HomeActivity.this.startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton(android.R.string.cancel, null) // dismisses by default
                    .create()
                    .show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


        return true;


    }

    public void switchFragment(Fragment fragment){
        switchFragment(fragment, true);
    }

    public void switchFragment(Fragment fragment, boolean addBackStack) {
        String backStateName =  fragment.getClass().getName().toString();
        String fragmentTag = backStateName;

        FragmentManager manager = getSupportFragmentManager();

        if(addBackStack){
            boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);
            if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null){
                fragmentContent(fragment, manager, fragmentTag, backStateName, true);
            }
        }else{
            fragmentContent(fragment, manager, fragmentTag, backStateName, false);
        }
    }
    private void fragmentContent(Fragment fragment, FragmentManager manager, String tag, String stateName, boolean addBackStack){
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.content_frame, fragment, tag);
        ft.setTransition(FragmentTransaction.TRANSIT_NONE);

        if(addBackStack){
            ft.addToBackStack(stateName);
        }
        ft.commit();
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        i = 0;
    }
}
