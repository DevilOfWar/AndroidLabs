package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

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
    }
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
        ResetFilter(findViewById(R.id.Reset));
        double badMarkFilter = Double.parseDouble(((EditText)findViewById(R.id.MarkFilter)).getText().toString());
        List<Abiturient> badList = Abiturient.Bad(badMarkFilter);
        backupList.clear();
        backupList.addAll(list);
        adapter.clear();
        adapter.addAll(badList);
        adapter.notifyDataSetChanged();
    }

    public void Average(View view)
    {
        ResetFilter(findViewById(R.id.Reset));
        double badMarkFilter = Double.parseDouble(((EditText)findViewById(R.id.MarkFilter)).getText().toString());
        List<Abiturient> averageList = Abiturient.Average(badMarkFilter);
        backupList.clear();
        backupList.addAll(list);
        adapter.clear();
        adapter.addAll(averageList);
        adapter.notifyDataSetChanged();
    }

    public void Top(View view)
    {
        ResetFilter(findViewById(R.id.Reset));
        backupList.clear();
        backupList.addAll(list);
        int abiturientCount = Integer.parseInt(((EditText)findViewById(R.id.abiturientCount)).getText().toString());
        List<Abiturient> topList = Abiturient.Top(abiturientCount);
        adapter.clear();
        adapter.addAll(topList);
        adapter.notifyDataSetChanged();
    }

    public void ResetFilter(View view)
    {
        adapter.clear();
        adapter.addAll(backupList);
        adapter.notifyDataSetChanged();
    }
}