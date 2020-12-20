package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class Task1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);
    }

    public void Find(View view)
    {
        Spinner spinner = findViewById(R.id.categories);
        String selected = spinner.getSelectedItem().toString();
        TextView textView = findViewById(R.id.info);
        switch (selected)
        {
            case "Молочные продукты":
                textView.setText(R.string.milk_info);
                break;
            case "Мясные продукты":
                textView.setText(R.string.meat_info);
                break;
            case "Хлебобулочные изделия":
                textView.setText(R.string.bread_info);
                break;
            default:
                textView.setText("");
                break;
        }
    }
}