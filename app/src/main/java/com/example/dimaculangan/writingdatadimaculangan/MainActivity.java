package com.example.dimaculangan.writingdatadimaculangan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText etData, etFilename;
    SharedPreferences preferences;
    FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etData = (EditText) findViewById(R.id.et_Data);
        etFilename = (EditText) findViewById(R.id.et_filename);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public void saveSharedPreferences(View view){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("data",etData.getText().toString());
        editor.putString("filename",etFilename.getText().toString());
        editor.commit();
        Toast.makeText(this, "Saved in Shared Preferences!", Toast.LENGTH_LONG).show();
    }

    public void saveInternalStorage (View view){
        String file = etFilename.getText().toString();
        String data = etData.getText().toString();
        String message = data;
        try {
            fos = openFileOutput(file, Context.MODE_PRIVATE);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Toast.makeText(this, "Saved in Internal Storage!", Toast.LENGTH_LONG).show();
    }

    public void saveInternalCache(View view){
        String path = etFilename.getText().toString();
        File folder = getCacheDir();
        File file = new File(folder, path);
        String message = etData.getText().toString();
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(file);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Saved in Internal Cache!", Toast.LENGTH_LONG).show();
    }

    public void saveExternalCache(View view){
        String path = etFilename.getText().toString();
        File folder = getExternalCacheDir();
        File file = new File(folder, path);
        String message = etData.getText().toString();
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(file);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Saved in External Cache!", Toast.LENGTH_LONG).show();
    }

    public void saveExternalStorage(View view){
        String path = etFilename.getText().toString();
        File folder = getExternalFilesDir("temp");
        File file = new File(folder, path);
        String message = etData.getText().toString();
        writeData(file,message);
        Toast.makeText(this, "Saved in External Storage!", Toast.LENGTH_LONG).show();
    }

    public void saveExternalPublicStorage(View view){
        String path = etFilename.getText().toString();
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder, path);
        String message = etData.getText().toString();
        writeData(file,message);
        Toast.makeText(this, "Saved in External Public Storage!", Toast.LENGTH_LONG).show();
    }

    public void writeData(File file, String message) {
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(file);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void Next(View view){
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
}
