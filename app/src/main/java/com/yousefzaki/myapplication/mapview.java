package com.yousefzaki.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class mapview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapview);
    }

    public void viewmain(View view) {

        Intent a= new Intent(this,MainActivity.class);
        startActivity(a);

    }

    public void viewtime(View view) {
        Intent a= new Intent(this,MainActivity3.class);
        startActivity(a);
    }

    public void slide(View view) {
        Intent a= new Intent(this,MainActivity3.class);
        startActivity(a);
    }
}