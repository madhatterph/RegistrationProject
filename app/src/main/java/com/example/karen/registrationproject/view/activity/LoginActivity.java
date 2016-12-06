package com.example.karen.registrationproject.view.activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.karen.registrationproject.HttpHandler;
import com.example.karen.registrationproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.value;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private int i;
    private String username,password,user,pass;
    private EditText userET;
    private EditText passET;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "";
    private String TAG = LoginActivity.class.getSimpleName();
    private static String urlz = "http://system.highlysucceed.com/api/auth/login.json";
    private static String api_key = "base64:ujaByZRnnlmnqKpbJuJ7aEWoXlE+3NiJTXpQ+xPaD50";
    int responsecode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen);
        userET = (EditText)findViewById(R.id.loginscreen_input_email);
        passET = (EditText)findViewById(R.id.loginscreen_input_password);
        Button btnLogin = (Button) findViewById(R.id.loginscreen_btn_login);
        btnLogin.setOnClickListener(this);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.loginscreen_btn_login:
                Login();
                break;
        }

    }
    @Override
    public void onBackPressed() {
        i++;
        if (i == 1) {
            Toast.makeText(LoginActivity.this, "Press back once more to exit.",
                    Toast.LENGTH_SHORT).show();
        } else if(i>1) {
            finish();
            super.onBackPressed();
        }
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        i = 0;
    }
    private void Login(){
        username = userET.getText().toString();
        password = passET.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlz,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        intent.putExtra("login", value); //Optional parameters
                        LoginActivity.this.startActivity(intent);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> map = new HashMap<String,String>();
                String creds = String.format("%s",api_key);
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                map.put("api_token",auth);
                map.put("username",username);
                map.put("password",password);
                return map;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

