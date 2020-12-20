package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Task2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2);
    }

    public void Process(View view)
    {
        RadioGroup radio = findViewById(R.id.RadioGroup);
        switch (radio.getCheckedRadioButtonId()){
            case R.id.FirstActivity: {
                Intent intent = new Intent(this, FirstActivity.class);
                intent.putExtra("text", ((TextView) findViewById(R.id.textView)).getText());
                startActivity(intent);
                break;
            }
            case R.id.SecondActivity:{
                Intent intent = new Intent(this, SecondActivity.class);
                intent.putExtra("text", ((TextView) findViewById(R.id.textView)).getText());
                startActivity(intent);
                break;
            }
            default:
                break;
        }
    }
}