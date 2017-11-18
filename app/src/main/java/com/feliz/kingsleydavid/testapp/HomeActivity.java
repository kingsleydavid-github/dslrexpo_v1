package com.feliz.kingsleydavid.testapp;

import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    public static final String utf_8_char = "UTF-8";
    private TextView mTextMessage;
    public Spinner spExpoList;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_auto);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_expo_finder);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTextMessage = (TextView) findViewById(R.id.message);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        spExpoList = (Spinner) findViewById(R.id.expo_spinner);

        List<String> spExpoList = new ArrayList<String>();
        try
        {
            JSONArray expo_json = new JSONArray(getExposureJSON());
        }
        catch(JSONException e)
        {
            //TODO:
        }
        ArrayAdapter spAdapter_home = new ArrayAdapter(this, R.layout.activity_home, spExpoList);

    }

    private String getExposureJSON()
    {
        JSONArray expoArray = new JSONArray();
        String json = null;
        try
        {
            InputStream in = getAssets().open("evCalc.json");
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            json = new String(buffer, utf_8_char);
            Log.i("JSON : ", json);
        }
        catch(IOException e)
        {
            //TODO
        }
        return json;
    }

}
