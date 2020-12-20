package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String extra = getIntent().getExtras().get("text").toString();
        TextView text = (TextView)findViewById(R.id.textView3);
        StringBuilder builder = new StringBuilder(extra);
        text.setText(builder.reverse().toString());
    }
}