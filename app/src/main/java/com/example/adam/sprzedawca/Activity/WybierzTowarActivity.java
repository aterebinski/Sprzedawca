package com.example.adam.sprzedawca.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.adam.sprzedawca.Adapter.TowaryRowAdapter;
import com.example.adam.sprzedawca.Model.Towar;
import com.example.adam.sprzedawca.R;

import java.util.List;

public class WybierzTowarActivity extends AppCompatActivity {

    List<Towar> towary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybierz_towar);

        ListView listView = (ListView)findViewById(R.id.lV_wybierz_towar);
        towary = Towar.dajWszystkie(getApplicationContext());

        if(towary!=null) {
            TowaryRowAdapter adapter = new TowaryRowAdapter(getApplicationContext(), R.layout.row_towary, towary);
            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Towar towar = towary.get(position);
                Intent intent = new Intent();
                intent.putExtra("towar",towar);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }


}
