package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Task1(View view)
    {
        Intent intent = new Intent(this, Task1.class);
        startActivity(intent);
    }

    public void Task2(View view)
    {
        Intent intent = new Intent(this, Task2.class);
        startActivity(intent);
    }

    public void Task3(View view)
    {
        Intent intent = new Intent(this, Task3.class);
        startActivity(intent);
    }
}