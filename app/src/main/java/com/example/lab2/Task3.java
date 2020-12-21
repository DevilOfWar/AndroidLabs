package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.BaseStream;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static com.example.lab2.Abiturient.backupList;
import static com.example.lab2.Abiturient.list;

public class Task3 extends AppCompatActivity {
    int selectedId = -1;
    ArrayAdapter<Abiturient> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task3);
        if (savedInstanceState != null && savedInstanceState.containsKey("list"))
        {
            list = (ArrayList<Abiturient>)savedInstanceState.getSerializable("list");
        }
        if (savedInstanceState != null && savedInstanceState.containsKey("backup"))
        {
            backupList = (ArrayList<Abiturient>)savedInstanceState.getSerializable("backup");
        }
        adapter = new ArrayAdapter<>(this, R.layout.list_element, R.id.TextView, list);
        ListView listView = findViewById(R.id.List);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> selectedId = position);
        RadioButton badRadio = (RadioButton) findViewById(R.id.BadObject);
        RadioButton averageRadio = (RadioButton) findViewById(R.id.AverageObject);
        RadioButton bestRadio = (RadioButton) findViewById(R.id.BestObject);
        badRadio.setOnClickListener(RadioListener);
        averageRadio.setOnClickListener(RadioListener);
        bestRadio.setOnClickListener(RadioListener);
    }

    View.OnClickListener RadioListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RadioButton rb = (RadioButton)view;
            switch (rb.getId()) {
                case R.id.BadObject: Bad(view);
                    break;
                case R.id.AverageObject: Average(view);
                    break;
                case R.id.BestObject: Top(view);
                    break;
                default:
                    break;
                }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 1:
                adapter.add((Abiturient) data.getSerializableExtra("abiturient"));
                adapter.notifyDataSetChanged();
                break;
            case 2:
                Abiturient deleteAbiturient = (Abiturient) data.getSerializableExtra("abiturient");
                Abiturient.Delete(deleteAbiturient);
                adapter.notifyDataSetChanged();
                break;
            case 3:
                list.set(selectedId, (Abiturient) data.getSerializableExtra("abiturient"));
                adapter.notifyDataSetChanged();
                selectedId = -1;
                break;
            default:
                break;
        }
        backupList.clear();
        backupList.addAll(list);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putSerializable("list", (Serializable) list);
        outState.putSerializable("backup", (Serializable) backupList);
    }

    public void Add(View view)
    {
        Intent intent = new Intent(this, AddDialog.class);
        startActivityForResult(intent, 1);
    }

    public void Delete(View view)
    {
        Intent intent = new Intent(this, DeleteDialog.class);
        startActivityForResult(intent, 2);
    }

    public void Edit(View view)
    {
        Abiturient abiturientEdit = list.get(selectedId);
        Intent intent = new Intent(this, EditDialog.class);
        intent.putExtra("abiturientEdit", abiturientEdit);
        startActivityForResult(intent, 3);
    }

    public void Bad(View view)
    {
        ResetFilter(view);
        backupList.clear();
        backupList.addAll(list);
        try {
            double badMarkFilter = Double.parseDouble(((EditText) findViewById(R.id.MarkFilter)).getText().toString());
            List<Abiturient> badList = Abiturient.Bad(badMarkFilter);
            adapter.clear();
            adapter.addAll(badList);
            adapter.notifyDataSetChanged();
        }
        catch (NumberFormatException e)
        {
            EditText errorText = (EditText) findViewById(R.id.MarkFilter);
            errorText.setError("Введите необходимое кол-во лучших абитуриентов");
        }
    }

    public void Average(View view)
    {
        ResetFilter(view);
        backupList.clear();
        backupList.addAll(list);
        try {
            double badMarkFilter = Double.parseDouble(((EditText) findViewById(R.id.MarkFilter)).getText().toString());
            List<Abiturient> averageList = Abiturient.Average(badMarkFilter);
            adapter.clear();
            adapter.addAll(averageList);
            adapter.notifyDataSetChanged();
        }
        catch (NumberFormatException e)
        {
            EditText errorText = (EditText) findViewById(R.id.MarkFilter);
            errorText.setError("Введите необходимое кол-во лучших абитуриентов");
        }
    }

    public void Top(View view)
    {
        ResetFilter(view);
        backupList.clear();
        backupList.addAll(list);
        try {
            int abiturientCount = Integer.parseInt(((EditText) findViewById(R.id.abiturientCount)).getText().toString());
            List<Abiturient> topList = Abiturient.Top(abiturientCount);
            adapter.clear();
            adapter.addAll(topList);
            adapter.notifyDataSetChanged();
        }
        catch (NumberFormatException e)
        {
            EditText errorText = (EditText) findViewById(R.id.abiturientCount);
            errorText.setError("Введите необходимое кол-во лучших абитуриентов");
        }
    }

    public void ResetFilter(View view)
    {
        if (view == findViewById(R.id.Reset)) {
            RadioGroup radio = (RadioGroup) findViewById(R.id.radioGroup);
            radio.clearCheck();
        }
        adapter.clear();
        adapter.addAll(backupList);
        adapter.notifyDataSetChanged();
    }
}