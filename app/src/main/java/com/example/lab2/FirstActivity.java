package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        TextView text = findViewById(R.id.textView2);
        String extra = getIntent().getExtras().get("text").toString();
        String putText = "";
        for (int index = 0; index < extra.length(); index++)
        {
            putText = putText + extra.substring(index, index + 1) + " ";
        }
        text.setText(putText);
    }
}