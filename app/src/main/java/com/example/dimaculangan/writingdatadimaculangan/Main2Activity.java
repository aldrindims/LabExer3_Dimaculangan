package com.example.dimaculangan.writingdatadimaculangan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    TextView tvDisplay;
    EditText tView;
    SharedPreferences preferences;
    FileInputStream fis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvDisplay = (TextView) findViewById(R.id.tv_display);
        tView = (EditText) findViewById(R.id.et_filename);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }
    public void displaySharedPreferences(View view){
        String data = preferences.getString("data","");
        tvDisplay.setText(data);
    }

    public void displayInternalStorage (View view){
        String path = tView.getText().toString();
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try {
            fis = openFileInput(path);
            while ((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvDisplay.setText(buffer.toString());
    }

    public void displayInternalCache (View view){
        String path = tView.getText().toString();
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try {
            fis = new FileInputStream( new File(getCacheDir(), path));
            while ((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvDisplay.setText(buffer.toString());
    }

    public void displayExternalCache (View view){
        String path = tView.getText().toString();
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try {
            fis = new FileInputStream( new File(getExternalCacheDir(), path));
            while ((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvDisplay.setText(buffer.toString());
    }

    public void displayExternalStorage (View view){
        String path = tView.getText().toString();
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try {
            fis = new FileInputStream( new File(getExternalFilesDir("temp"), path));
            while ((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvDisplay.setText(buffer.toString());
    }

    public void displayExternalPublicStorage (View view){
        String path = tView.getText().toString();
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try {
            fis = new FileInputStream( new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), path));
            while ((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvDisplay.setText(buffer.toString());
    }
    public void Back(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
