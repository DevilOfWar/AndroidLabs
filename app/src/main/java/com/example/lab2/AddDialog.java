package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddDialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dialog);
    }

    public void AddNewAbiturient(View view)
    {
        EditText secondName = (EditText) findViewById(R.id.SecondName);
        EditText firstName = (EditText) findViewById(R.id.FirstName);
        EditText adress = (EditText) findViewById(R.id.Adress);
        EditText phone = (EditText) findViewById(R.id.Phone);
        EditText marks = (EditText) findViewById(R.id.marks);
        if (phone.getText().toString().length() == 9)
        {
            try {
                Abiturient newAbiturient = new Abiturient();
                newAbiturient.SecondName = secondName.getText().toString();
                newAbiturient.FirstName = firstName.getText().toString();
                newAbiturient.Adress = adress.getText().toString();
                newAbiturient.Phone = phone.getText().toString();
                String marksString = marks.getText().toString();
                String[] marksList = marksString.split(", ");
                newAbiturient.marks = new int[marksList.length];
                boolean flag = true;
                for (int index = 0; index < marksList.length && flag; index++) {
                    newAbiturient.marks[index] = Integer.parseInt(marksList[index]);
                }
                Intent answer = new Intent();
                answer.putExtra("abiturient", newAbiturient);
                setResult(RESULT_OK, answer);
                finish();
            }
            catch (NumberFormatException e)
            {
                marks.setError("Введите оценки через запятую");
            }
        }
        else
        {
            phone.setError("Введите телефон в формате 9 цифр");
        }
    }
}