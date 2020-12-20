package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DeleteDialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_dialog);
    }

    public void DeleteAbiturient(View view)
    {
        EditText secondName = (EditText) findViewById(R.id.secondNameDelete);
        EditText firstName = (EditText) findViewById(R.id.firstNameDelete);
        Abiturient abiturient = new Abiturient();
        abiturient.SecondName = secondName.getText().toString();
        abiturient.FirstName = firstName.getText().toString();
        abiturient.Adress = "";
        abiturient.Phone = "";
        abiturient.marks = new int[1];
        Intent answer = new Intent();
        answer.putExtra("abiturient", abiturient);
        setResult(RESULT_OK, answer);
        finish();
    }
}